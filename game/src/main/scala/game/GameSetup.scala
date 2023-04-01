package game

trait GameSetup {
    def join(user: UserId): Unit
    def start(): Game
}

object GameSetup {
    def apply(): GameSetup = ???
}
