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

import io.circe._, io.circe.generic.semiauto._, io.circe.parser._
import java.net.http.WebSocket

import game.infraestructure.app.{Command, App}


given jsonEncode: Encoder[Command] = deriveEncoder[Command]
given jsonDecode: Decoder[Command] = deriveDecoder[Command]


implicit val runtime: IORuntime = cats.effect.unsafe.IORuntime.global


trait WebSocketRoutes extends App {

  def inOutWsFunc(in: Stream[IO, WebSocketFrame]): Stream[IO, WebSocketFrame] = {
     in
        .map(_.asInstanceOf[Text].str)
        .map(str => parse(str).right.get)
        .map(json => jsonDecode.decodeJson(json).right.get)
        .map(app(_))
        .recover(err => Command("error", s"error on command $err"))
        .map(str => WebSocketFrame.Text(jsonEncode(str).toString))
  }

  def routes(ws: WebSocketBuilder2[IO]): HttpRoutes[IO] =
    HttpRoutes.of[IO] { case GET -> Root / "ws" =>
      ws.build(inOutWsFunc)
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
