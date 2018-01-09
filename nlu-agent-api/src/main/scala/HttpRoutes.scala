

import java.time.LocalDateTime
import java.util.UUID

import Data._
import akka.actor.{ActorRef, ActorSystem, Props}
import akka.http.scaladsl.marshallers.sprayjson.SprayJsonSupport._
import akka.http.scaladsl.server.Directives._
import akka.http.scaladsl.server.Route
import akka.stream.ActorMaterializer
import events.UtteranceHandler
import nlu.SentimentProcessor
import akka.pattern.ask
import akka.util.Timeout
import nlu.Nlu.ActionResult

import scala.concurrent.{Await, Future}
import scala.concurrent.duration._
import scala.language.postfixOps
import scala.util.Success

/**
  * Created by prayagupd
  * on 12/27/16.
  */

trait HttpRoutes {

  implicit val system: ActorSystem
  implicit val materializer: ActorMaterializer

  import Data.ServiceJsonProtoocol._

  private val auth: Route = path("auth") {
    post {
      entity(as[Auth.Request]) {
        event =>
          complete {
            Auth.Response(
              conversationID = UUID.randomUUID().toString,
              correlationID = Some(event.correlationID),
              msgTimestamp = Some(LocalDateTime.now()),
              passthrough = Some(Auth.Metadata[String](
                contents = "welcome to nlu",
                chatbotVersion = "1.0",
                clientChannel = "channel",
                userToken = Some("user-token"),
                authToken = Some("auth")
              )),
              response = Some("response")
            )
          }
      }
    }
  }

  val route: Route =
    auth ~
      path("conversation") {
        post {
          entity(as[Conversation.Request[String]]) {
            event =>
              complete {
                "hello"
              }
          }
        }
      } ~
      path("chat") {
        get {
          handleWebSocketMessages(UtteranceHandler.handle)
        }
      } ~
      ChatRoute.chatRoute
}

object ChatRoute {

  import Data.ServiceJsonProtoocol._

  //TODO use same actorSystem from NluServer
  val nluEventSystem1 = ActorSystem("nlu-system")

  val nlu: ActorRef = nluEventSystem1.actorOf(Props[SentimentProcessor], "sentiment")
  implicit val timeout: Timeout = Timeout(2 seconds)

  val chatRoute: Route = path("chat2") {
    post {
      entity(as[Conversation.Request[String]]) { event =>

        val result: Future[Seq[ActionResult]] = (nlu ? event.metadata.contents).mapTo[Seq[ActionResult]]

        onComplete(result) {
          case Success(x) =>
            println(x)
            complete(x)
          case _ =>
            println("failed")
            complete(Seq.empty[ActionResult])
        }

      }
    }
  }

}
