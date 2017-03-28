package learning.fp.scala.kata.finish

import learning.fp.scala.kata.shared.Course
import learning.fp.scala.kata.shared.Builder.buildData
import learning.fp.scala.kata.shared.CourseType._

object Program {

  def main(args: Array[String]) = {
    val courses = buildData()

    def titlesOfCourses() {
      println("Titles of courses:")
      courses.map(_.title)
             .foreach(printTabbed)
      println("---")
    }
    def titlesOfCoursesWithoutATrainer() {
      println("Titles of courses without a trainer:")
      courses.filter(_.instructors.isEmpty)
             .map(_.title)
             .foreach(printTabbed)
      println("---")
    }
    def namesAndRatesOfTrainers() {
      println("Names and rates of trainers:")
      courses.flatMap(_.instructors)
             .distinct
             .map(t => (t.name,t.rate))
             .foreach(printTabbed)
      println("---")
    }
    def theNumberOfAdvancedCourses() {
      println("The number of advanced courses")
      println(courses.count(_.courseType == ADVANCED))
      println("---")
    }
    def totalDurationsOfBeginnerAndNonBeginnerCourses() {
      println("Total days for both beginner and non-beginner courses")
      val splitCourses = courses.partition(_.courseType == BEGINNER)
      val beginnerDuration = splitCourses._1.map(_.duration).sum
      val nonBeginnerDuration = splitCourses._2.map(_.duration).sum
      println((beginnerDuration,nonBeginnerDuration))
      println("---")
    }
    def everyPairOfTrainersThatCouldDeliverJava() {
      println("Pairs of trainers that could deliver Java")
      courses.flatMap(_.instructors)
             .distinct
             .filter(_.skills.contains("Java"))
             .map(_.name)
             .combinations(2)
             .foreach(list => printf("%s and %s\n",list(0),list(1)))
      println("---")
    }
    def possibleCostsOfJeeWebDevelopment() {
      println("Possible costs of 'JEE Web Development'")
      val course = courses.find(_.title == "JEE Web Development")
      val duration = course.map(_.duration)
                           .getOrElse(0)
      course.foreach(_.instructors
                      .map(p => (p.name, p.rate * duration))
                      .foreach(printTabbed))
      println("---")
    }
    def coursesIdsAndTitlesGroupedByType() {
      println("Course ID's and titles grouped by type")
      def process(row: (Value, List[Course])) {
        printTabbed(row._1)
        row._2.foreach(c => printf("\t\t%s %s\n", c.id, c.title))
      }
      courses.groupBy(_.courseType)
             .foreach(process)
    }
    titlesOfCourses
    titlesOfCoursesWithoutATrainer
    namesAndRatesOfTrainers
    theNumberOfAdvancedCourses
    totalDurationsOfBeginnerAndNonBeginnerCourses
    everyPairOfTrainersThatCouldDeliverJava
    possibleCostsOfJeeWebDevelopment
    coursesIdsAndTitlesGroupedByType
  }
  def printTabbed(input: Any): Unit = {
    print("\t")
    println(input)
  }
}
