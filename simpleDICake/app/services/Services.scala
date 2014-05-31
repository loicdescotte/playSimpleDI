package services
import play.api.Play
import scala.concurrent.Future
import scala.concurrent.ExecutionContext.Implicits.global
import play.api.Play.current

trait ServicesComponent {
  val linkService: LinkService
  val logService: LogService
}

trait LinkService {
  def findLinks(query: String): Future[String]
}

trait LogService {
  def log(message: String): Unit
}


class DuckDuckLinksService(logService: LogService) extends LinkService {
  import play.api.libs.ws.WS

  def findLinks(query: String) = {
    val duckDuckUrl = Play.current.configuration.getString("duckduck.url").getOrElse("http://api.duckduckgo.com/?format=json&q=") + query
    WS.url(duckDuckUrl).get.map{ response =>
      val results = response.json \\ "FirstURL"
      //take first result if exists
      val result =results.mkString(", ")
      logService.log("found links : " + result)
      result
    }
  }
}

class SimpleLogService extends LogService{
  import play.api.Logger

  def log(message: String) {
    Logger.info(message)
  }
}

trait ApplicationServices extends ServicesComponent {
  val logService = new SimpleLogService
  val linkService = new DuckDuckLinksService(logService)
}