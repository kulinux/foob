package game.infraestructure.app

import game.core.{Game, Play, QuestionRepository, UserId, UserScreen, GameImpl}
import game.infraestructure.question.TriviaApiQuestionRepository

case class Command(command: String, arguments: String)
trait App {

  val userSreen: UserScreen = WebUserScreen()
  val questions: QuestionRepository = TriviaApiQuestionRepository()

  val game: Game = Game(questions, userSreen)
  val appGame = AppGame(game)

  def app(cm: Command): Command = appGame.app(cm)
}

class AppGame(val game: Game) {
  def app(cm: Command): Command = {
    if(cm.command == "hello") {
      game.showChallenge()
      return Command("responseCommand", "ok")
    }
    ???
  }

}


class WebUserScreen extends UserScreen {
    override def showAll(question: String): Unit = ???
    override def winner(userId: UserId): Unit = ???
    override def wrong(): Unit = ???
    override def answer(user: UserId, answer: Play): Unit = ???
}