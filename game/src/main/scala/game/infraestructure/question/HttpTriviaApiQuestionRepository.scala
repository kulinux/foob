package game.infraestructure.question

import game.core.{Question, QuestionRepository}
import sttp.client3.upicklejson.*
import sttp.client3.{SimpleHttpClient, UriContext, basicRequest}
import upickle.default.*

case class TriviaApiQuestion(question: String, correctAnswer: String)

class HttpTriviaApiQuestionRepository {

  private val Url = "https://the-trivia-api.com/api/questions/"
  implicit val responseRW: ReadWriter[TriviaApiQuestion] =
    macroRW[TriviaApiQuestion]

  def questions(): Seq[TriviaApiQuestion] = {

    val request = basicRequest
      .get(uri"${Url}")
      .response(asJson[Seq[TriviaApiQuestion]])

    SimpleHttpClient()
      .send(request)
      .body
      .getOrElse(Seq())
  }
}
