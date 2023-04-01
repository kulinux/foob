package game

trait GameSetup {
    def join(user: UserId): GameSetup
    def start(): Game
}

object GameSetup {
    def apply(): GameSetup = GameSetupImpl(Seq())
}

class GameSetupImpl(players: Seq[UserId]) extends GameSetup {

  override def join(user: UserId): GameSetup = GameSetupImpl(players :+ user)

  override def start(): Game = Game(players)


}
