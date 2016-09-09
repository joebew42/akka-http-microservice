package http

import akka.actor.ActorSystem
import akka.event.LoggingAdapter
import akka.http.scaladsl.server.Directives._
import akka.stream.Materializer
import com.typesafe.config.Config
import domain.IpService
import http.routes.IpRoutes

import scala.concurrent.ExecutionContextExecutor

trait HttpService {
  implicit val system: ActorSystem

  implicit def executor: ExecutionContextExecutor

  implicit val materializer: Materializer

  def config: Config

  val logger: LoggingAdapter
  val ipService = new IpService()

  val ipRoutes: IpRoutes = new IpRoutes(ipService)

  val routes = {
    logRequestResult("akka-http-microservice") {
      ipRoutes.routes
    }
  }
}
