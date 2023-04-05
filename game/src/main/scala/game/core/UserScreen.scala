package game.core

trait UserScreen {
    def showAll(question: String): Unit
}

object UserScreen {
    def apply(): UserScreen = UserScreenImpl()
}

class UserScreenImpl extends UserScreen {
  override def showAll(question: String): Unit = ???
}
