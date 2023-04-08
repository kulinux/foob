package game.core

trait UserScreen {
    def showAll(question: String): Unit
    def winner(userId: UserId): Unit
}
