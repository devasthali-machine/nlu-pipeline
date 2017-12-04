package nlu

import akka.actor.{ActorSystem, Props}
import akka.testkit.{TestKit, TestProbe}
import org.scalatest.{FunSuiteLike, Matchers}

import scala.concurrent.duration._
import scala.language.postfixOps

class IntentProcessorSpec extends TestKit(ActorSystem("nlu-event-system")) with FunSuiteLike with Matchers {

  val intentCreator = system.actorOf(Props[IntentProcessor], "intent-creator")
  val mailbox = TestProbe()

  test("creates tags") {

    intentCreator.tell("I'm having headache.", mailbox.ref)

    within(5 seconds) {
      mailbox.expectMsg(Seq(Seq(
        "I" -> "PRP",
        "'m" -> "VBP",
        "having" -> "VBG",
        "headache" -> "NN",
        "." -> ".")))
    }
  }
}
