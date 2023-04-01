package game

import org.scalatest.freespec.AnyFreeSpec
import org.scalatest.matchers.should.Matchers

class GameSetupSpec extends AnyFreeSpec with Matchers {
    "Game should be initiated with joined players" in {
        val juan: UserId = ???
        val marcos: UserId = ???
        val gameSetup = GameSetup()

        gameSetup.join(juan)
        gameSetup.join(marcos)

        gameSetup.start()

        verify(Game.apply(Seq(juan, marcos)))
    }
  
}
