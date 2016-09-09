package http

import akka.actor.ActorSystem
import akka.event.Logging
import akka.http.scaladsl.Http
import akka.stream.ActorMaterializer
import com.typesafe.config.ConfigFactory

object Main extends App with HttpService {
  implicit val system = ActorSystem()
  implicit val executor = system.dispatcher
  implicit val materializer = ActorMaterializer()

  val config = ConfigFactory.load()
  val logger = Logging(system, getClass)

  private val host: String = config.getString("http.interface")
  private val port: Int = config.getInt("http.port")

  Http().bindAndHandle(handler = routes, interface = host, port = port) map {
    binding => println(s"REST interface bound to ${binding.localAddress}")
  } recover {
    case ex => println(s"REST interface could not bind to $host:$port ${ex.getMessage}")
  }
}
