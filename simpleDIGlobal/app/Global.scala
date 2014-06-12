import play.api.GlobalSettings
import play.api.mvc._
import controllers._
import services._

object Global extends GlobalSettings {

  val services = new ServicesComponent
  val instanceMaps: Map[Class[_], AnyRef] = Map(classOf[Application] -> new Application(services))

  override def getControllerInstance[A](controllerClass: Class[A]) = instanceMaps(controllerClass).asInstanceOf[A]

}