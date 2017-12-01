

import java.time.LocalDateTime
import java.util.UUID

import Data._
import akka.actor.ActorSystem
import akka.http.scaladsl.marshallers.sprayjson.SprayJsonSupport._
import akka.http.scaladsl.server.Directives._
import akka.stream.ActorMaterializer

/**
  * Created by prayagupd
  * on 12/27/16.
  */

trait HttpRoutes {

  implicit val system: ActorSystem
  implicit val materializer: ActorMaterializer

  import Data.ServiceJsonProtoocol._

  val route =
    path("auth") {
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
    } ~
      path("conversation") {
        post {
          entity(as[Conversation.Request[String]]) {
            event =>
              complete {
                "hello"
              }
          }
        }
      }
}
