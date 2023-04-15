package game.infraestructure.http

import cats.effect._, org.http4s._, org.http4s.dsl.io._

import cats.syntax.all._
import com.comcast.ip4s._
import org.http4s.ember.server._
import org.http4s.implicits._
import org.http4s.server.Router
import scala.concurrent.duration._
import cats.effect.{ExitCode, IO, IOApp, Resource}
import fs2.{Pipe, Stream}
import org.http4s.HttpRoutes
import org.http4s.dsl.io._
import org.http4s.ember.server.EmberServerBuilder
import org.http4s.server.Server
import org.http4s.server.websocket.WebSocketBuilder2
import org.http4s.websocket.WebSocketFrame
import java.time.Instant
import scala.concurrent.duration._

import cats.effect.unsafe.IORuntime
import org.http4s.ember.server.EmberServerBuilder
import game.core.GameImpl
import game.core.UserScreen
implicit val runtime: IORuntime = cats.effect.unsafe.IORuntime.global

trait App {
  def buildApp(): (Pipe[IO, String, Unit], Stream[IO, WebSocketFrame])
}

trait WebSocketRoutes {
  def routes(ws: WebSocketBuilder2[IO]): HttpRoutes[IO] =
    HttpRoutes.of[IO] { case GET -> Root / "ws" =>
      val send: Stream[IO, WebSocketFrame] =
        Stream
          .awakeEvery[IO](1.second)
          .evalMap(_ => IO(WebSocketFrame.Text("ok")))

      val receive: Pipe[IO, WebSocketFrame, Unit] =
        in =>
          in.evalMap(frameIn => IO(println("in " + frameIn)))

      //val (in, out) = buildApp()

      ws.build(send, receive)
    }

}

object Server extends IOApp.Simple with WebSocketRoutes {
  val run = EmberServerBuilder
    .default[IO]
    .withHost(ipv4"0.0.0.0")
    .withPort(port"8080")
    .withHttpWebSocketApp(ws => routes(ws).orNotFound)
    .build
    .useForever
}
