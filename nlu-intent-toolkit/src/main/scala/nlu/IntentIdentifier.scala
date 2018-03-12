package nlu

import java.io.{BufferedInputStream, FileInputStream}
import java.util.UUID

import com.google.api.gax.core.FixedCredentialsProvider
import com.google.api.gax.grpc.InstantiatingGrpcChannelProvider
import com.google.auth.oauth2.GoogleCredentials
import com.google.cloud.dialogflow.v2beta1._

import scala.util.{Failure, Success, Try}

object IntentIdentifier {

  def intentRequest(data: Map[String, String]): Either[Throwable, Option[String]] = {

    val credsStream = new BufferedInputStream(new FileInputStream(data("creds")))

    val credentials = FixedCredentialsProvider.create(GoogleCredentials.fromStream(credsStream))

    val sessionsClient = Some(SessionsClient.create(SessionsSettings.newBuilder()
      .setEndpoint("dialogflow.googleapis.com:443")
      .setTransportChannelProvider(InstantiatingGrpcChannelProvider.newBuilder().build())
      .setCredentialsProvider(credentials)
      .build()))

    Try {
      val resp = sessionsClient.map { session =>
        val intentRequest = DetectIntentRequest.newBuilder()
          .setSession(SessionName.of(data("nlu-agent"), UUID.randomUUID().toString).toString)
          .setQueryInput(QueryInput.newBuilder.setText(
            TextInput.newBuilder.setText(data("utterance")).setLanguageCode("en-US")))
          .build()

        val intentResp = session.detectIntent(intentRequest)

        intentResp.getQueryResult.getIntent.getDisplayName
      }

      resp
    } match {
      case Success(e) => Right(e)
      case Failure(e) => Left(e)
    }
  }
}
