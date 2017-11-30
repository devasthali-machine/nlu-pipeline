

import java.time.LocalDateTime
import java.util.UUID

import spray.json.{DefaultJsonProtocol, DeserializationException, JsString, JsValue, JsonFormat}

/**
  * Created by prayagupd
  * on 12/27/16.
  */

object Models {

  implicit object UUIDFormat extends JsonFormat[UUID] {
    def write(uuid: UUID) = JsString(uuid.toString)

    def read(value: JsValue) = {
      value match {
        case JsString(uuid) => UUID.fromString(uuid)
        case _ => throw new DeserializationException("Expected hexadecimal UUID string")
      }
    }
  }

  implicit object LocalDateFormat extends JsonFormat[LocalDateTime] {
    def write(ld: LocalDateTime) = JsString(ld.toString)

    def read(value: JsValue) = {
      value match {
        case JsString(time) => LocalDateTime.parse(time)
        case _ => throw new DeserializationException("wrong local date")
      }
    }
  }

  case class AuthRequest(authToken: String,
                         correlationID: UUID,
                         msgTimestamp: LocalDateTime = LocalDateTime.now(),
                         name: String,
                         passthrough: Option[Metadata[String]],
                         userID: String,
                         loyalty: Int = 0)

  case class AuthResponse(conversationID: String,
                          correlationID: Option[UUID],
                          msgTimestamp: Option[LocalDateTime],
                          passthrough: Option[Metadata[String]],
                          response: Option[String])

  case class Metadata[T](contents: T,
                         chatbotVersion: String,
                         clientChannel: String,
                         userToken: Option[String],
                         authToken: Option[String])

  case class Fulfillment(contents: String)

  case class ApplicationStarted(name: String)

  case class Acknowledge(status: String)

  object ServiceJsonProtoocol extends DefaultJsonProtocol {
    implicit val applicationStarted = jsonFormat1(ApplicationStarted)
    implicit val metadata1 = jsonFormat5(Metadata[String])
    implicit val authRequest = jsonFormat7(AuthRequest)
    implicit val authResponse = jsonFormat5(AuthResponse)
    implicit val ack = jsonFormat1(Acknowledge)
  }

}
