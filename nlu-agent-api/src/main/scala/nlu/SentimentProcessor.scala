package nlu

import java.util.Properties

import edu.stanford.nlp.ling.CoreAnnotations
import edu.stanford.nlp.pipeline._
import edu.stanford.nlp.sentiment.SentimentCoreAnnotations
import nlu.Nlu.{ActionResult, Utterance}

import scala.collection.JavaConverters._
import scala.collection.mutable

class SentimentProcessor extends Nlu {

  // creates a StanfordCoreNLP object, with POS tagging, lemmatization, NER, parsing, and coreference resolution
  val nlp = new StanfordCoreNLP(new Properties() {
    {
      setProperty("annotators", "tokenize, ssplit, pos, lemma, parse, sentiment")
    }
  })

  override def receive: PartialFunction[Any, Unit] = {

    case event: Utterance =>
      val annotation = nlp.process(event)
      val sentences = annotation.get(classOf[CoreAnnotations.SentencesAnnotation])

      val sentiments: mutable.Seq[ActionResult] = sentences.asScala.map { sentence =>
        val sentiment: ActionResult = sentence.get(classOf[SentimentCoreAnnotations.SentimentClass])
        sentiment
      }

      sender() ! sentiments
  }
}
