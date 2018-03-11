package nlu

object App extends App {

  println("##################################################")
  println("#  usage:: *.jar nlu-agent utterance creds-file  #")
  println("##################################################")

  val req = Map(
    "nlu-agent" -> args(0),
    "utterance" -> args(1),
    "creds" -> args(2)
  )

  IntentIdentifier.intentRequest(req) match {
    case Right(i) => println(i.getOrElse("empty-intent"))
    case Left(e) => e.printStackTrace()
  }
}
