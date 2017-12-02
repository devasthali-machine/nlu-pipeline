
import akka.actor.ActorSystem
import akka.http.scaladsl.Http
import akka.stream.ActorMaterializer

import scala.concurrent.Future

/**
  * Created by prayagupd
  * on 12/27/16.
  */

class NluServer(implicit val system: ActorSystem, implicit val materializer: ActorMaterializer) extends HttpRoutes {

  def startServer(address: String, port: Int): Future[Http.ServerBinding] =
    Http().bindAndHandle(route, address, port)

}

object NluServer {

  def main(args: Array[String]) {

    implicit val nluSystem = ActorSystem("nlu-api-system")
    implicit val actorExecutor = ActorMaterializer()

    new NluServer().startServer("localhost", 9191)
  }
}
