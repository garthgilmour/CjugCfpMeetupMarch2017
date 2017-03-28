package demos.akka.http.service

import akka.actor.ActorSystem
import akka.http.scaladsl.Http
import akka.http.scaladsl.Http.ServerBinding
import akka.http.scaladsl.model.{HttpResponse, StatusCodes}
import akka.http.scaladsl.server.Directives
import akka.stream.ActorMaterializer
import demos.akka.http.model.Builder.buildData
import demos.akka.http.model._

import scala.concurrent.Future
import scala.io.StdIn

//JSON conversion code lives here...
import demos.akka.http.service.CourseTypeProtocol._

object CoursesService extends Directives {
  def main(args: Array[String]) {
    //AKKA Infrastructure - injected as implicits
    implicit val system = ActorSystem("course-booking-system")
    implicit val materializer = ActorMaterializer()

    var courses = buildData()

    val routes = {
      path("courses") {
        get {
          complete(courses)
        }
      } ~
      pathPrefix("courses" / Segment) { id =>
        get {
          complete(courses.find(_.id == id))
        }~
        delete {
          courses = courses.filterNot(_.id == id)
          complete(HttpResponse(StatusCodes.NoContent))
        }~
        put {
          entity(as[Course]) { c =>
            courses = c :: courses
            complete(HttpResponse(StatusCodes.NoContent))
          }
        }
      }
    }

    val bindingFuture = Http().bindAndHandle(routes, "localhost", 8080)
    pauseTillDone
    shutdownServer(bindingFuture, system)
  }

  def shutdownServer(bindingFuture: Future[ServerBinding], system: ActorSystem) = {
    implicit val executionContext = system.dispatcher
    bindingFuture
      .flatMap(_.unbind())                 // unbind from the port
      .onComplete(_ => system.terminate()) // shutdown when done
  }

  private def pauseTillDone = {
    println("AKKA HTTP running on 'http://localhost:8080'")
    println("Hit RETURN to quit")
    StdIn.readLine()
  }
}

