package services
import play.api.Play
import scala.concurrent.Future
import scala.concurrent.ExecutionContext.Implicits.global
import play.api.Play.current

class LogService{
  import play.api.Logger

  def log(message: String) {
    Logger.info(message)
  }
}

class LinkService(logService: LogService){
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

trait ServicesComponent{
  val logService = new LogService
  val linkService = new LinkService(logService)
}