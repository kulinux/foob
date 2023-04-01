package game

import org.scalatest.freespec.AnyFreeSpec
import org.scalatest.matchers.should.Matchers
import org.scalatestplus.easymock.EasyMockSugar

class GameSetupSpec extends AnyFreeSpec with Matchers with EasyMockSugar {
    "Game should be initiated with joined players" in {
        val juan: UserId = mock[UserId]
        val marcos: UserId = mock[UserId]
        val gameSetup = GameSetup()

        val game = gameSetup
            .join(juan)
            .join(marcos)
            .start()

        game.players() should contain only (juan, marcos)
    }
}
