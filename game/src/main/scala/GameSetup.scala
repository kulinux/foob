trait GameSetup {
    def join(user: UserId): Unit
    def start(): Game
}
