package events

import java.util.concurrent.TimeUnit

import akka.actor.{ActorRef, ActorSystem, Props}
import akka.http.scaladsl.model.ws.{Message, TextMessage}
import akka.pattern.ask
import akka.stream.scaladsl.Flow
import akka.util.Timeout
import nlu.Nlu.ActionResult
import nlu.SentimentProcessor

object UtteranceHandler {

  val nluEventSystem = ActorSystem("nlu-system")
  val sentimentProcessor: ActorRef = nluEventSystem.actorOf(Props[SentimentProcessor], "sentiment-processor")
  implicit val timeout: Timeout = Timeout(2, TimeUnit.SECONDS)

  val handle: Flow[Message, Message, _] = Flow[Message].map {

    case TextMessage.Strict(event) =>
      sentimentProcessor ? event match {
        case emitted: Seq[ActionResult] =>
          TextMessage(emitted.head)
      }
    case _ => TextMessage("Event type unsupported")

  }

}
