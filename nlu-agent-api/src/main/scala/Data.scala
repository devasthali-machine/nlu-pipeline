

import java.time.LocalDateTime
import java.util.UUID

import Data.Auth.Metadata
import Data.Conversation.Fulfillment
import spray.json.{DefaultJsonProtocol, DeserializationException, JsString, JsValue, JsonFormat, RootJsonFormat}

/**
  * Created by prayagupd
  * on 12/27/16.
  */

object Data {

  implicit object UUIDFormat extends JsonFormat[UUID] {
    def write(uuid: UUID) = JsString(uuid.toString)

    def read(value: JsValue): UUID = {
      value match {
        case JsString(uuid) => UUID.fromString(uuid)
        case _ => throw DeserializationException("Expected hexadecimal UUID string")
      }
    }
  }

  implicit object LocalDateFormat extends JsonFormat[LocalDateTime] {
    def write(ld: LocalDateTime) = JsString(ld.toString)

    def read(value: JsValue): LocalDateTime = {
      value match {
        case JsString(time) => LocalDateTime.parse(time)
        case _ => throw DeserializationException("wrong local date")
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
    implicit val applicationStarted: RootJsonFormat[ApplicationStarted] = jsonFormat1(ApplicationStarted)
    implicit val metadata1: RootJsonFormat[Metadata[String]] = jsonFormat5(Auth.Metadata[String])
    implicit val authRequest: RootJsonFormat[Auth.Request] = jsonFormat6(Auth.Request)
    implicit val authResponse: RootJsonFormat[Auth.Response] = jsonFormat5(Auth.Response)

    implicit val ship: RootJsonFormat[Conversation.ShippingAddress] = jsonFormat1(Conversation.ShippingAddress)
    implicit val detail: RootJsonFormat[Conversation.OrderItemDetail] = jsonFormat1(Conversation.OrderItemDetail)
    implicit val fulfillment: RootJsonFormat[Fulfillment] = jsonFormat6(Conversation.Fulfillment)
    implicit val convRequest: RootJsonFormat[Conversation.Request[String]] = jsonFormat11(Conversation.Request[String])
    implicit val ack: RootJsonFormat[Acknowledge] = jsonFormat1(Acknowledge)
  }

}
