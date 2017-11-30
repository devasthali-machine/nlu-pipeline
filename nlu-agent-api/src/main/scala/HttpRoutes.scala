

import java.time.LocalDateTime
import java.util.UUID

import Models._
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

  import Models.ServiceJsonProtoocol._

  val route =
    path("auth") {
      post {
        entity(as[AuthRequest]) {
          event =>
            complete {
              AuthResponse(
                conversationID = UUID.randomUUID().toString,
                correlationID = Some(event.correlationID),
                msgTimestamp = Some(LocalDateTime.now()),
                passthrough = Some(Metadata[String](
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
      path("message") {
        get {
          complete {
            "hello"
          }
        }
      }
}
