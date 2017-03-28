package learning.fp.scala.kata.shared

object Course {
  def apply(id: String,
            title: String,
            courseType: CourseType.Value,
            duration: Int,
            instructors: List[Trainer]) = {
    new Course(id,title,courseType,duration, instructors)
  }
}

class Course(val id: String,
             val title: String,
             val courseType: CourseType.Value,
             val duration: Int,
             val instructors: List[Trainer]) {

}
