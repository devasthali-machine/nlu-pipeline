package nlu

import java.util.Properties

import edu.stanford.nlp.ling.CoreAnnotations
import edu.stanford.nlp.pipeline._
import edu.stanford.nlp.sentiment.SentimentCoreAnnotations

import scala.collection.JavaConverters._

class NluProcessor {

  def process(query: String): Seq[String] = {

    // creates a StanfordCoreNLP object, with POS tagging, lemmatization, NER, parsing, and coreference resolution
    val nlp = new StanfordCoreNLP(new Properties() {{
        setProperty("annotators", "tokenize, ssplit, pos, lemma, parse, sentiment")
      }})

    val annotation = nlp.process(query)
    val sentences = annotation.get(classOf[CoreAnnotations.SentencesAnnotation])

    sentences.asScala.map { sentence =>
      val sentiment = sentence.get(classOf[SentimentCoreAnnotations.SentimentClass])
      sentiment
    }

  }
}
