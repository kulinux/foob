package game.core

import game.core.{GameSetup, UserId}
import org.easymock.EasyMock.{replay, verify}
import org.scalatest.freespec.AnyFreeSpec
import org.scalatest.matchers.should.Matchers
import org.scalatestplus.easymock.EasyMockSugar
class GameSetupSpec extends AnyFreeSpec with Matchers with EasyMockSugar {
  "Game should be initiated with joined players" in {
    val gameSetup = GameSetup()
    val game = mock[Game]
    val gameBuilder: () => Game = mock[() => Game]

    expecting {
      gameBuilder() andReturn game
    }

    replay(gameBuilder)
    gameSetup
      .start(gameBuilder)
    verify(gameBuilder)

  }
}
