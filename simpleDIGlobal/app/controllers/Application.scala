package controllers

import play.api._
import play.api.mvc._
import services._
import scala.concurrent.ExecutionContext.Implicits.global

class Application(services: ServicesComponent) extends Controller {
  def findLinks(query: String) = Action.async {
    val links = services.linkService.findLinks(query)
    links.map(response => Ok(views.html.index(response)))
  }

}