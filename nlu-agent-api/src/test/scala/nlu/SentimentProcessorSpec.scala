package nlu

import akka.actor.{ActorRef, ActorSystem, Props}
import akka.testkit.{TestKit, TestProbe}
import org.scalatest.{FunSuiteLike, Matchers}

class SentimentProcessorSpec extends TestKit(ActorSystem("nlu-event-system")) with FunSuiteLike with Matchers {

  val nlu: ActorRef = system.actorOf(Props[SentimentProcessor], "sentiment-processor")
  val verifyMailBox = TestProbe()

  test("processes nlu query") {

    nlu.tell("I did not like the product.", verifyMailBox.ref)
    verifyMailBox.awaitAssert {
      verifyMailBox.expectMsg(Seq("Negative"))
    }

    nlu.tell("Product was amazing.", verifyMailBox.ref)

    verifyMailBox.awaitAssert {
      verifyMailBox.expectMsg(Seq("Positive"))
    }

    nlu.tell("Seattle store is an amazing place", verifyMailBox.ref)
    verifyMailBox.awaitAssert {
      verifyMailBox.expectMsg(Seq("Very positive"))
    }

    nlu.tell("Seattle store is an amazing place. I like shirts", verifyMailBox.ref)
    verifyMailBox.awaitAssert {
      verifyMailBox.expectMsg(Seq("Very positive", "Neutral"))
    }
  }

  test("should i buy a product") {

    val reviews = Seq("The product is terrible", "The product is pretty good", "The product is awesome")

    nlu.tell(reviews.head, verifyMailBox.ref)

    verifyMailBox.awaitAssert {
      verifyMailBox.expectMsg(Seq("Negative"))
    }

  }

}
