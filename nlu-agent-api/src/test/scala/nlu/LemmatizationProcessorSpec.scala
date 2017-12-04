package nlu

import akka.actor.{ActorRef, ActorSystem, Props}
import akka.testkit.{TestKit, TestProbe}
import org.scalatest.{FunSuiteLike, Matchers}

class LemmatizationProcessorSpec extends TestKit(ActorSystem("nlu-event-system")) with FunSuiteLike with Matchers {

  val nlu: ActorRef = system.actorOf(Props[LemmatizationProcessor], "lemmatization")

  val verifyMailbox = TestProbe()

  test("lemmatizes") {

    nlu.tell("I was driving yesterday", verifyMailbox.ref)

    verifyMailbox.awaitAssert{
      verifyMailbox.expectMsg(Seq("I", "be", "drive", "yesterday"))
    }
  }

}
