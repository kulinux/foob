
import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest._
import flatspec._
import matchers._
import org.scalatest.freespec.AnyFreeSpec
//import org.scalatestplus.easymock.EasyMockSugar

class GameSpec extends AnyFreeSpec with should.Matchers /*with EasyMockSugar*/ {
  "A user should join and start a game" in {
    val gameDef: GameSetup = ???
    val juan: UserId = ???
    val marcos: UserId = ???
    val play: Play = ???

    gameDef.join(juan)
    gameDef.join(marcos)

    val game: Game = gameDef.start()

    game.showChallenge()

    game.answer(juan, play)
    game.answer(marcos, play)

    game.winner()
  }

}
