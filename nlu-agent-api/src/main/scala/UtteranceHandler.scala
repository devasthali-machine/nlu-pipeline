package events

import akka.http.scaladsl.model.ws.{Message, TextMessage}
import akka.stream.scaladsl.Flow
import nlu.NluProcessor

object UtteranceHandler {

  val nlu = new NluProcessor

  val handle: Flow[Message, Message, _] = Flow[Message].map {
    case TextMessage.Strict(event) => TextMessage(nlu.process(event).head)
    case _ => TextMessage("Event type unsupported")
  }

}
