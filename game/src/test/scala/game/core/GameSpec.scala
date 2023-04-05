package game.core

import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.freespec.AnyFreeSpec
import org.scalatestplus.easymock.EasyMockSugar
import org.easymock.EasyMock._

import game.core.GameImpl
import game.core.Question
import game.core.QuestionRepository
import game.core.UserScreen
class GameSpec extends AnyFreeSpec with EasyMockSugar {
  val SampleQuestion = "Which is the highest mountain in Japan?"

  "game should show challenge" in {

    val questions: QuestionRepository = mock[QuestionRepository]
    val usersScreen: UserScreen = mock[UserScreen]
    val aQuestion: Question = Question(SampleQuestion, SampleQuestion)

    expecting {
      questions.nextQuestion() andReturn aQuestion
    }
    expecting {
      usersScreen.showAll(SampleQuestion)
    }

    replay(questions)
    replay(usersScreen)

    val game = GameImpl(Seq(), questions, usersScreen)
    game.showChallenge()

    verify(questions)
    verify(usersScreen)
  }
}
