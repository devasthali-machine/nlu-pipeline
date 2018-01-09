import java.time.LocalDateTime
import java.util.UUID

import akka.http.scaladsl.model.{HttpEntity, HttpRequest, MediaTypes}
import akka.http.scaladsl.testkit.{RouteTestTimeout, ScalatestRouteTest}
import org.scalatest.{FunSuite, Matchers}
import akka.http.scaladsl.model.HttpMethods.POST

import scala.concurrent.duration._
import akka.testkit._

import scala.language.postfixOps

class HttpRoutesSpec extends FunSuite with Matchers with ScalatestRouteTest {

  implicit val timeout: RouteTestTimeout = RouteTestTimeout(1.second dilated)

  test("returns an intent based on query") {

    val json =
      s"""{
         |    "correlationID": "${UUID.randomUUID}",
         |    "conversationID": "convo-id",
         |    "location": "madison street",
         |    "userId": "upd",
         |    "msgTimestamp": "${LocalDateTime.now().toString}",
         |    "metadata": {
         |       "chatbotVersion": "1.0",
         |       "contents": "when will new Porcupine Tree album release?",
         |       "clientChannel": "android"
         |    },
         |    "request": "when will new Porcupine Tree album release?",
         |    "deviceType": 1,
         |    "turnID": 1,
         |    "clientVersion": "1.0",
         |    "applicationID": "nlu-client-001"
         |    }
      """.stripMargin

    Post("/chat2") ~> ChatRoute.chatRoute ~> check {
      HttpRequest(POST, uri = "/chat2",
        entity = HttpEntity(MediaTypes.`application/json`, json)) ~> ChatRoute.chatRoute ~> check {
        responseAs.status.intValue() shouldBe 200
      }
    }

  }
}
