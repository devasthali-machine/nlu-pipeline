package nlu

import java.util.Properties

import akka.actor.Actor
import edu.stanford.nlp.ling.CoreAnnotations.{LemmaAnnotation, SentencesAnnotation, TextAnnotation, TokensAnnotation}
import edu.stanford.nlp.pipeline.StanfordCoreNLP
import nlu.Nlu.Utterance

import scala.collection.JavaConverters._

object Nlu {
  type Utterance = String
  type ActionResult = String
}

trait Nlu extends Actor

/**
  * https://nlp.stanford.edu/IR-book/html/htmledition/stemming-and-lemmatization-1.html
  *
  * The goal of both stemming and lemmatization is to reduce inflectional forms and sometimes derivationally
  * related forms of a word to a common base form. For instance:
  *
  * am, are, is -> be
  * car, cars, car's, cars' -> car
  */

class LemmatizationProcessor extends Nlu {

  val props = new Properties()
  props.put("annotators", "tokenize, ssplit, pos, lemma")
  val pipeline = new StanfordCoreNLP(props, false)

  override def receive: PartialFunction[Any, Unit] = {
    case event: Utterance =>
      val document = pipeline.process(event)

      val lemmas = document.get(classOf[SentencesAnnotation]).asScala.flatMap { sentence =>
        sentence.get(classOf[TokensAnnotation]).asScala.map { token =>
          val word = token.get(classOf[TextAnnotation])
          val lemma = token.get(classOf[LemmaAnnotation])
          println("lemmatized version :" + lemma)
          lemma
        }
      }

      sender() ! lemmas

    case noops => println("no ops")
  }
}
