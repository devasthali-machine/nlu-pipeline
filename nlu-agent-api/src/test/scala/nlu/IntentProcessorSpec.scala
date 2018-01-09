package nlu

import akka.actor.{ActorRef, ActorSystem, Props}
import akka.testkit.{TestKit, TestProbe}
import org.scalatest.{FunSuiteLike, Matchers}

import scala.concurrent.duration._
import scala.language.postfixOps

class IntentProcessorSpec extends TestKit(ActorSystem("nlu-event-system")) with FunSuiteLike with Matchers {

  val intentCreator: ActorRef = system.actorOf(Props[IntentProcessor], "intent-creator")
  val mailbox = TestProbe()

  test("creates tags") {

    intentCreator.tell("I'm having headache.", mailbox.ref)

    within(5 seconds) {
      mailbox.expectMsg(Seq(Seq(
        "I" -> "PRP", //Personal ProNoun
        "'m" -> "VBP", // Verb, non-3rd person singular present
        "having" -> "VBG", // Verb, gerund or present participle
        "headache" -> "NN", // Noun, singular or mass
        "." -> ".")))
    }
  }
}
