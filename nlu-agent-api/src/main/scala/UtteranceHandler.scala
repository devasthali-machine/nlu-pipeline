package events

import java.util.concurrent.TimeUnit

import akka.actor.{ActorSystem, Props}
import akka.http.scaladsl.model.ws.{Message, TextMessage}
import akka.stream.scaladsl.Flow
import nlu.SentimentProcessor
import akka.pattern.ask
import akka.util.Timeout
import nlu.Nlu.ActionResult

object UtteranceHandler {

  val nluEventSystem = ActorSystem("nlu-system")
  val sentimentProcessor = nluEventSystem.actorOf(Props[SentimentProcessor], "sentiment-processor")
  implicit val timeout = Timeout(2, TimeUnit.SECONDS)

  val handle: Flow[Message, Message, _] = Flow[Message].map {
    case TextMessage.Strict(event) =>
      sentimentProcessor ? event match {
        case emitted: Seq[ActionResult] =>
          TextMessage(emitted.head)
      }
    case _ => TextMessage("Event type unsupported")
  }

}
