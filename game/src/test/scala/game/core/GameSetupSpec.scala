package game.core

import game.core.{GameSetup, UserId}
import org.easymock.EasyMock.{replay, verify}
import org.scalatest.freespec.AnyFreeSpec
import org.scalatest.matchers.should.Matchers
import org.scalatestplus.easymock.EasyMockSugar
class GameSetupSpec extends AnyFreeSpec with Matchers with EasyMockSugar {
  "Game should be initiated with joined players" in {
    val juan: UserId = mock[UserId]
    val marcos: UserId = mock[UserId]
    val gameSetup = GameSetup()
    val game = mock[Game]
    val gameBuilder = mock[Function1[Seq[UserId], Game]]

    expecting {
      gameBuilder.apply(Seq(juan, marcos)) andReturn game
    }

    replay(gameBuilder)
    gameSetup
      .join(juan)
      .join(marcos)
      .start(gameBuilder)
    verify(gameBuilder)

  }
}
