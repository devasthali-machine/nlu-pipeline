package nlu

import org.scalatest.Matchers

class NluProcessorSpec extends org.scalatest.FunSuite with Matchers {

  val nlu = new NluProcessor

  test("processes nlu query") {
    val sentiment = nlu.process("I did not like the product.")
    sentiment shouldBe Seq("Negative")

    val sentiment1 = nlu.process("Product was amazing.")
    sentiment1 shouldBe Seq("Positive")

    val sentiment2 = nlu.process("Seattle store is an amazing place")
    sentiment2 shouldBe Seq("Very positive")

    val sentiment3 = nlu.process("Seattle store is an amazing place. I like shirts")
    sentiment3 shouldBe Seq("Very positive", "Neutral")
  }

  test("should i buy a product") {

    val reviews = Seq("The product is terrible", "The product is pretty good", "The product is awesome")

    nlu.process(reviews.head) shouldBe Seq("Negative")

  }

}
