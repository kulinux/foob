package game.infraestructure.app

import org.easymock.EasyMock.{replay, reset, verify}
import org.scalatest.freespec.AnyFreeSpec
import org.scalatest.matchers.should.Matchers
import org.scalatestplus.easymock.EasyMockSugar
import game.core.{Game, Question, UserScreen, UserId, Play}
import game.ConsoleUserScreen
import org.scalatest.BeforeAndAfterEach

class AppSpec
    extends AnyFreeSpec
    with EasyMockSugar
    with Matchers
    with BeforeAndAfterEach {
  var game: Game = mock[Game]
  var userScren: WebUserScreen = mock[WebUserScreen]
  var app: AppGame = new AppGame(game, userScren)

  override def beforeEach() = {
    game = mock[Game]
    userScren = mock[WebUserScreen]
    app = new AppGame(game, userScren)
  }

  "Should show challenge when say hello" in {
    expecting {
      game.showChallenge()
    }
    replay(game)
    app.app(Command("hello", ""))
    verify(game)
  }

  "Should show send answer" in {
    expecting {
      game.answer(UserId(""), Play("question"))
    }

    replay(game)
    app.app(Command("answer", "question"))
    verify(game)
  }

}
