trait UserId

trait GameSetup {
    def join(user: UserId): Unit
    def start(): Unit
}
