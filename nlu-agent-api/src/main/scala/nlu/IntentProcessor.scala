package nlu

import java.util.Properties

import edu.stanford.nlp.ling.CoreAnnotations._
import edu.stanford.nlp.pipeline.StanfordCoreNLP
import nlu.Nlu.Utterance

import scala.collection.JavaConverters._

/**
  * https://stanfordnlp.github.io/CoreNLP/api.html#interpreting-the-output
  */
class IntentProcessor extends Nlu {

  val nlp = new StanfordCoreNLP(new Properties() {
    {
      setProperty("annotators", "tokenize, ssplit, pos, lemma, parse")
    }
  })

  override def receive: PartialFunction[Any, Unit] = {
    case event: Utterance =>

      println(event)
      val pos = nlp.process(event).get(classOf[SentencesAnnotation]).asScala.map { sentence =>
        sentence.get(classOf[TokensAnnotation]).asScala.map { token =>
          token.get(classOf[TextAnnotation]) -> token.get(classOf[PartOfSpeechAnnotation])
        }
      }

      sender() ! pos
  }
}
