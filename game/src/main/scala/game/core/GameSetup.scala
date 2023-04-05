package game.core

import game.core.Game
import game.core.UserId
trait GameSetup {
    def join(user: UserId): GameSetup
    def start(gameBuilder: (Seq[UserId]) => Game): Game
}

object GameSetup {
    def apply(): GameSetup = GameSetupImpl(Seq())
}

class GameSetupImpl(players: Seq[UserId]) extends GameSetup {

  override def join(user: UserId): GameSetup = GameSetupImpl(players :+ user)

  override def start(gameBuilder: (Seq[UserId]) => Game): Game = gameBuilder(players)
}
