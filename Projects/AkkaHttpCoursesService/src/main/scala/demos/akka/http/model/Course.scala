package demos.akka.http.model

final case class Course(val id: String,
                        val title: String,
                        val courseType: CourseType.Value,
                        val duration: Int,
                        val instructors: List[Trainer]) {

}
