import org.scalatest.freespec.AnyFreeSpec
import org.scalatest.matchers.should.Matchers
import game.infraestructure.question.TriviaApiQuestionRepository

class QuestionSpec extends AnyFreeSpec {
    "test should work" in {
        val question = TriviaApiQuestionRepository()
        question.nextQuestion()
    }
  
}
