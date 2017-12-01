

import java.time.LocalDateTime
import java.util.UUID

import Data.Auth.Metadata
import Data.Conversation.Fulfillment
import spray.json.{DefaultJsonProtocol, DeserializationException, JsString, JsValue, JsonFormat}

/**
  * Created by prayagupd
  * on 12/27/16.
  */

object Data {

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

  object Auth {

    case class Request(authToken: String,
                       correlationID: UUID,
                       msgTimestamp: LocalDateTime = LocalDateTime.now(),
                       name: String,
                       metadata: Option[Metadata[String]],
                       userID: String)

    case class Response(conversationID: String,
                        correlationID: Option[UUID],
                        msgTimestamp: Option[LocalDateTime],
                        passthrough: Option[Metadata[String]],
                        response: Option[String])

    case class Metadata[T](contents: T,
                           chatbotVersion: String,
                           clientChannel: String,
                           userToken: Option[String],
                           authToken: Option[String])

  }

  object Conversation {

    case class Request[T](correlationID: UUID,
                          conversationID: String,
                          location: Option[String],
                          userId: Option[String],
                          msgTimestamp: LocalDateTime,
                          metadata: Metadata[T],
                          request: String,
                          deviceType: Int,
                          turnID: Int,
                          clientVersion: Option[String],
                          applicationID: String = "nlu-client-001")

    case class Fulfillment(orderType: String,
                           orderID: String,
                           orderStatus: String,
                           orderCreatedDate: Option[String],
                           orderDeliveryLocation: Option[ShippingAddress],
                           orderItemDetail: Option[OrderItemDetail])

    case class ShippingAddress(firstName: String)

    case class OrderItemDetail(productName: String)

  }

  case class ApplicationStarted(name: String)

  case class Acknowledge(status: String)

  object ServiceJsonProtoocol extends DefaultJsonProtocol {
    implicit val applicationStarted = jsonFormat1(ApplicationStarted)
    implicit val metadata1 = jsonFormat5(Auth.Metadata[String])
    implicit val authRequest = jsonFormat6(Auth.Request)
    implicit val authResponse = jsonFormat5(Auth.Response)

    implicit val ship = jsonFormat1(Conversation.ShippingAddress)
    implicit val detail = jsonFormat1(Conversation.OrderItemDetail)
    implicit val fulfillment = jsonFormat6(Conversation.Fulfillment)
    implicit val convRequest = jsonFormat11(Conversation.Request[String])
    implicit val ack = jsonFormat1(Acknowledge)
  }

}
