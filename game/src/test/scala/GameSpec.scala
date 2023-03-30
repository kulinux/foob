
import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest._
import flatspec._
import matchers._
import org.scalatest.freespec.AnyFreeSpec
import org.scalatestplus.easymock.EasyMockSugar

class GameSpec extends AnyFreeSpec with should.Matchers with EasyMockSugar{
  "A user should join and start a game" in {
    val gameDef = mock[GameSetup]
    val juan = mock[UserId]
    val marcos = mock[UserId]

    gameDef.join(juan)
    gameDef.join(marcos)

    gameDef.start()
  }

}
