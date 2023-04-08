package game.core

import game.core.Game
import game.core.UserId
trait GameSetup {
    def start(gameBuilder: () => Game): Game
}

object GameSetup {
    def apply(): GameSetup = GameSetupImpl(Seq())
}

class GameSetupImpl(players: Seq[UserId]) extends GameSetup {

  override def start(gameBuilder: () => Game): Game = gameBuilder()
}
