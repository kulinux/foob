package game

import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest._
import flatspec._
import matchers._
import org.scalatest.freespec.AnyFreeSpec
import org.scalatestplus.easymock.EasyMockSugar

class PlayingSpec extends AnyFreeSpec with should.Matchers with EasyMockSugar {
  "A user should join and start a game" in {
    val gameDef: GameSetup = GameSetup()
    val juan: UserId = mock[UserId]
    val marcos: UserId = mock[UserId]
    val play: Play = mock[Play]

    gameDef.join(juan)
    gameDef.join(marcos)

    val game: Game = gameDef.start()

    game.showChallenge()

    game.answer(juan, play)
    game.answer(marcos, play)

    game.winner()
  }

}
