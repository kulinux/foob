package game

import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.*
import flatspec.*
import matchers.*
import org.scalatest.freespec.AnyFreeSpec
import org.scalatestplus.easymock.EasyMockSugar
import game.core.{Game, Play, QuestionRepository, UserId, UserScreen, GameImpl}
import org.easymock.EasyMock._

import game.infraestructure.question.TriviaApiQuestionRepository
import game.infraestructure.question.TriviaApiQuestion
class PlayingSpec extends AnyFreeSpec with should.Matchers with EasyMockSugar {
  "A user should join and start a game" in {
    val juan: UserId = mock[UserId]
    val marcos: UserId = mock[UserId]
    val play: Play = Play("my answer")

    val userSreen: UserScreen = ConsoleUserScreen()
    val questions: QuestionRepository = TriviaApiQuestionRepository()

    val game: Game = 
      Game(questions, userSreen)


    game.showChallenge()

    userSreen.answer(juan, play)
    userSreen.answer(marcos, play)
  }

}

class ConsoleUserScreen extends UserScreen {

  var publisher: (UserId, Play) => Unit = (_, _) => {}

  override def answer(user: UserId, answer: Play): Unit = publisher

  override def winner(userId: UserId): Unit = println(s"Winner $userId")

  override def showAll(question: String): Unit = println(s"Question $question")

  override def wrong(): Unit = println(s"Answer is wrong")


}
