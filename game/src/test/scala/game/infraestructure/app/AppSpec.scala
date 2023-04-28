package game.infraestructure.app

import game.core.Question
import org.easymock.EasyMock.{replay, reset, verify}
import org.scalatest.freespec.AnyFreeSpec
import org.scalatest.matchers.should.Matchers
import org.scalatestplus.easymock.EasyMockSugar
import game.core.Game

class AppSpec extends AnyFreeSpec with EasyMockSugar with Matchers{
  "Should show challenge when say hello" in {
    val game = mock[Game]
    val app = new AppGame(game)

    expecting {
     game.showChallenge()
    }
    replay(game)
    app.app(Command("hello", ""))
    verify(game)

  }

}
