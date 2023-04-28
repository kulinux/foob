package game.infraestructure.app

case class Command(command: String, arguments: String)
trait App {
  def app(cm: Command): Command = {
    println("command " + cm)
    Command("received " + cm.command, cm.arguments)
  }
}