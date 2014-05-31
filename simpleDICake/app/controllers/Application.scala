package controllers

import play.api._
import play.api.mvc._
import services._
import scala.concurrent.ExecutionContext.Implicits.global

trait Application extends Controller {
  this : ServicesComponent =>

  def findLinks(query: String) = Action.async {
    val links = linkService.findLinks(query)
    links.map(response => Ok(views.html.index(response)))
  }

}

object Application extends Application with ApplicationServices