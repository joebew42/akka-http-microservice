package domain

import scala.concurrent.{ExecutionContext, Future}

class IpService()(implicit executionContext: ExecutionContext) {
  def fetchIpInfo(ip: String): Future[IpInfo] = {
    Future.successful(IpInfo("8.8.8.8", Option("United States"), Option("Mountain View"), Option(37.386), Option(-122.0838)))
  }
}
