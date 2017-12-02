package events

import akka.http.scaladsl.model.ws.{Message, TextMessage}
import akka.stream.scaladsl.Flow

object EventHandler {

  val handle: Flow[Message, Message, _] = Flow[Message].map {
    case TextMessage.Strict(event) => TextMessage("emit event: " + event)
    case _ => TextMessage("Event type unsupported")
  }

}
