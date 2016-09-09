package domain

case class IpInfo(query: String, country: Option[String], city: Option[String], lat: Option[Double], lon: Option[Double])
