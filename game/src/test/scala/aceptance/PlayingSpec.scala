package game

import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.*
import flatspec.*
import matchers.*
import org.scalatest.freespec.AnyFreeSpec
import org.scalatestplus.easymock.EasyMockSugar
import game.core.{Game, GameSetup, Play, QuestionRepository, UserId, UserScreen, GameImpl}

import game.infraestructure.question.TriviaApiQuestionRepository
class PlayingSpec extends AnyFreeSpec with should.Matchers with EasyMockSugar {
  "A user should join and start a game" in {
    val gameDef: GameSetup = GameSetup()
    val juan: UserId = mock[UserId]
    val marcos: UserId = mock[UserId]
    val play: Play = mock[Play]

    val game: Game = gameDef.start(() => 
      GameImpl(TriviaApiQuestionRepository(), ConsoleUserScreen())
    )

    game.showChallenge()

    game.answer(juan, play)
    game.answer(marcos, play)

  }
}

class ConsoleUserScreen extends UserScreen {

  override def winner(userId: UserId): Unit = println(s"Winner $userId")

  override def showAll(question: String): Unit = println(s"Question $question")
}
