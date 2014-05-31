import play.api.GlobalSettings
import play.api.mvc._
import controllers._
import services._

object Global extends GlobalSettings {

  val services = new ApplicationServices
  val applicationController = new Application(services)
  val instanceMaps: Map[Class[_], AnyRef] = Map(classOf[Application] -> applicationController)

  override def getControllerInstance[A](controllerClass: Class[A]) = instanceMaps(controllerClass).asInstanceOf[A]

}