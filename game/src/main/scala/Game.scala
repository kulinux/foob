trait Game {
    def showChallenge(): Unit
    def answer(userId: UserId, play: Play): Unit
    def winner(): UserId
}
