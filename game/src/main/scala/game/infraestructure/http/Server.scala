package game.infraestructure.http

import cats.effect._, org.http4s._, org.http4s.dsl.io._

import cats.syntax.all._

import cats.effect.{ExitCode, IO, IOApp, Resource}
import cats.effect.unsafe.IORuntime
import fs2.{Pipe, Stream}

import com.comcast.ip4s._
import org.http4s.implicits._
import org.http4s.server.Router
import org.http4s.ember.server.EmberServerBuilder
import org.http4s.websocket.WebSocketFrame
import org.http4s.websocket.WebSocketFrame.Text
import org.http4s.server.Server
import org.http4s.server.websocket.WebSocketBuilder2
import java.time.Instant

import scala.concurrent.duration._

import io.circe.generic.auto._
import io.circe.syntax._
import org.http4s.circe._


implicit val runtime: IORuntime = cats.effect.unsafe.IORuntime.global

trait App {
  def buildApp(): (Pipe[IO, String, Unit], Stream[IO, String]) = {
      val out: Stream[IO, String] =
        Stream
          .awakeEvery[IO](1.second)
          .evalMap(_ => IO("ok"))

      val in: Pipe[IO, String, Unit] =
        in =>
          in.evalMap(frameIn => IO(println("in " + frameIn)))

      return (in, out)
  }
}

trait WebSocketRoutes extends App {
  def routes(ws: WebSocketBuilder2[IO]): HttpRoutes[IO] =
    HttpRoutes.of[IO] { case GET -> Root / "ws" =>
      val (in, out) = buildApp()

      val send = out.map(str => WebSocketFrame.Text(str))

      val receive: Pipe[IO, WebSocketFrame, String] = in => in.map(_.asInstanceOf[Text].str)

      ws.build(send, receive andThen in)
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
