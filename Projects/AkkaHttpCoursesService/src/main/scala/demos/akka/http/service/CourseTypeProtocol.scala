package demos.akka.http.service

import akka.http.scaladsl.marshallers.sprayjson.SprayJsonSupport
import demos.akka.http.model.{Course, CourseType, Trainer}
import spray.json.{DefaultJsonProtocol, JsString, JsValue, RootJsonFormat, deserializationError}


object CourseTypeProtocol extends SprayJsonSupport with DefaultJsonProtocol {
        implicit object CourseTypeFormat extends RootJsonFormat[CourseType.Value] {
                def write(obj: CourseType.Value) = JsString(obj.toString)
                def read(json: JsValue) = {
                        json match {
                                case JsString(txt) => CourseType.withName(txt)
                                case _ => deserializationError("Expected a value from enum CourseType")
                        }
                }
        }
        implicit val trainerFormat = jsonFormat3(Trainer)
        implicit val courseFormat = jsonFormat5(Course)
}
