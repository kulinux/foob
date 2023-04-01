package game

trait Game {
  def showChallenge(): Unit
  def answer(userId: UserId, play: Play): Unit
  def winner(): UserId
  def players(): Seq[UserId]
}

object Game {
  def apply(initPlayers: Seq[UserId]): Game = GameImpl(initPlayers, QuestionRepository(), UserScreen())
}

class GameImpl(
    initPlayers: Seq[UserId],
    questions: QuestionRepository,
    userScreen: UserScreen
) extends Game {
  override def showChallenge(): Unit = {
    val question = questions.nextQuestion()
    userScreen.showAll(question.question)
  }
  override def answer(userId: UserId, play: Play): Unit = ???
  override def winner(): UserId = ???
  override def players(): Seq[UserId] = initPlayers
}
