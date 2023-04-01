package game

trait Game {
    def showChallenge(): Unit
    def answer(userId: UserId, play: Play): Unit
    def winner(): UserId
}

object Game {
    def apply(players: Seq[UserId]): Game = GameImpl(players)
}

class GameImpl(players: Seq[UserId]) extends Game {
  override def showChallenge(): Unit = ???
  override def answer(userId: UserId, play: Play): Unit = ???
  override def winner(): UserId = ???
}
