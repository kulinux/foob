package game.core

trait UserScreen {
    def showAll(question: String): Unit
    def winner(userId: UserId): Unit
    def wrong(): Unit
    def answer(user: UserId, answer: Play): Unit
}
