package game.core

import game.core.{UserId, Play}
import game.core.QuestionRepository
import game.core.UserScreen

import cats.syntax.all._

trait Game {
  def showChallenge(): Unit
  def answer(userId: UserId, play: Play): Unit
}

class GameImpl(
    questions: QuestionRepository,
    userScreen: UserScreen
) extends Game {

  var currentQuestion: Option[Question] = Option.empty
  override def showChallenge(): Unit = {
    currentQuestion = questions.nextQuestion().some
    userScreen.showAll(currentQuestion.get.question)
  }
  override def answer(userId: UserId, play: Play): Unit = {
    if (currentQuestion.exists(_.answer == play.answer)) {
      userScreen.winner(userId)
    } else {
      userScreen.wrong()
    }
  }
}
