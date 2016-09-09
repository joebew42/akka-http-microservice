package http.routes

import akka.http.scaladsl.marshalling.ToResponseMarshallable
import akka.http.scaladsl.model.StatusCodes._
import akka.http.scaladsl.server
import akka.http.scaladsl.server.Directives._
import akka.http.scaladsl.server.PathMatchers.Segment
import domain.IpService
import http.Protocols

import scala.concurrent.ExecutionContext

class IpRoutes(val ipService: IpService)(implicit executionContext: ExecutionContext) extends Protocols {
  def routes: server.Route = {
    pathPrefix("ip") {
      (get & path(Segment)) { ip =>
        complete {
          ipService.fetchIpInfo(ip).map(_ => "ciao")
        }
      }
    }
  }
}
