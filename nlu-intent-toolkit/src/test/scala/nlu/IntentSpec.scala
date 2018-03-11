package nlu

import org.scalatest.FunSuite

class IntentSpec extends FunSuite {

  test("intent req") {
    val args = Map(
      "nlu-agent" -> "test",
      "utterance" -> "where is porcupine tree playing?",
      "creds" -> "src/test/resources/creds.json"
    )

    IntentIdentifier.intentRequest(args) match {
      case Right(i) => println(i.getOrElse("empty-intent"))
      case Left(e) => e.printStackTrace()
    }

  }

}
