package game.infraestructure.question

import game.core.Question
import org.easymock.EasyMock.{replay, reset, verify}
import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.freespec.AnyFreeSpec
import org.scalatest.matchers.should.Matchers
import org.scalatestplus.easymock.EasyMockSugar

class TriviaApiQuestionRepositorySpec extends AnyFreeSpec with EasyMockSugar with Matchers{
  "Should call http when no question" in {
    val http = mock[HttpTriviaApiQuestionRepository]
    val question1 = TriviaApiQuestion("question1", "answer1")
    val question2 = TriviaApiQuestion("question2", "answer2")

    expecting {
     http.questions() andReturn Seq(question1, question2)
    }

    val apiRepository = TriviaApiQuestionRepository(http)

    replay(http)
    apiRepository.nextQuestion() shouldBe( Question(question1.question, question1.correctAnswer))
    verify(http)

    reset(http)
    replay(http)
    apiRepository.nextQuestion() shouldBe( Question(question2.question, question2.correctAnswer))
    verify(http)
  }

}
