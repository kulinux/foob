package game.infraestructure.app

import game.core.{Game, Play, QuestionRepository, UserId, UserScreen, GameImpl}
import game.infraestructure.question.TriviaApiQuestionRepository
import cats.effect.IO
import fs2._
import cats.effect.Async
import cats.effect.std.{Dispatcher, Queue}


case class Command(command: String, arguments: String)
trait App {

  val userSreen: WebUserScreen = WebUserScreen()
  val questions: QuestionRepository = TriviaApiQuestionRepository()

  val game: Game = Game(questions, userSreen)
  val appGame = AppGame(game, userSreen)

  def app(cm: Command): Command = appGame.app(cm)

  def asyncStream(): Stream[IO, Command] = appGame.asyncStream()
}

class AppGame(val game: Game, userSreen: WebUserScreen) {
  def app(cm: Command): Command = {
    if (cm.command == "hello") {
      game.showChallenge()
      return Command("responseCommand", "ok")
    }
     if (cm.command == "answer") {
      game.answer(UserId(""), Play(cm.arguments))
      return Command("responseCommand", "ok")
    }
    ???
  }

  def comandSender(onSuccess: Either[Throwable, Command] => Unit): Unit = {
    userSreen.callback = onSuccess
  }

  def asyncStream(): Stream[IO, Command] = {
    Stream.eval(IO.async_[Command] { cb => comandSender(cb) })
  }

}

class WebUserScreen() extends UserScreen {

  var callback: Either[Throwable, Command] => Unit = _ => ()

  override def showAll(question: String): Unit = callback(Right(Command("question", question)))
  override def winner(userId: UserId): Unit = callback(Right(Command("winner", userId.userId)))
  override def wrong(): Unit = callback(Right(Command("wrong", "")))
  override def answer(user: UserId, answer: Play): Unit = callback(Right(Command("answer", s"${answer.answer} by ${user.userId}")))
}
