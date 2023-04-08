package game.core

import game.core.{UserId, Play}
import game.core.QuestionRepository
import game.core.UserScreen

trait Game {
  def showChallenge(): Unit
  def answer(userId: UserId, play: Play): Unit
}

class GameImpl(
    questions: QuestionRepository,
    userScreen: UserScreen
) extends Game {
  override def showChallenge(): Unit = {
    val question = questions.nextQuestion()
    userScreen.showAll(question.question)
  }
  override def answer(userId: UserId, play: Play): Unit = {
    if(questions.verify(play)) {
      userScreen.winner(userId)
    }
  }
}
