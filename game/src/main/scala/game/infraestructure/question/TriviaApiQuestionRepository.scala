package game.infraestructure.question

import game.core.{Question, Play, QuestionRepository}
import sttp.client3.upicklejson.*
import sttp.client3.{SimpleHttpClient, UriContext, basicRequest}
import upickle.default.*

class TriviaApiQuestionRepository(
    http: HttpTriviaApiQuestionRepository = HttpTriviaApiQuestionRepository()
) extends QuestionRepository {

  private var buffer: Seq[Question] = Seq()

  override def nextQuestion(): Question = {
    if (buffer.isEmpty) {
      val httpQuestions = http.questions()
      buffer = httpQuestions.map(question =>
        Question(question.question, question.correctAnswer)
      )
    }
    val res = buffer.head
    buffer = buffer.tail
    res
  }

  override def verify(play: Play): Boolean = ???

  
}
