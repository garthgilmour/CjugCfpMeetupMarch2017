package learning.fp.scala.kata.start

import learning.fp.scala.kata.shared.{Trainer, Course, Builder, CourseType}
import Builder.buildData
import CourseType._

import scala.collection.mutable.HashSet
import scala.collection.mutable.HashMap
import scala.collection.mutable.ListBuffer

object Program {

  def main(args: Array[String]) = {
    val courses = buildData()

    def titlesOfCourses() {
      println("Titles of courses:")
      for(course <- courses) {
        printTabbed(course.title)
      }
      println("---")
    }
    def titlesOfCoursesWithoutATrainer() {
      println("Titles of courses without a trainer:")
      for(course <- courses) {
        if(course.instructors.isEmpty) {
          printTabbed(course.title)
        }
      }
      println("---")
    }
    def namesAndRatesOfTrainers() {
      println("Names and rates of trainers:")
      var trainers = HashSet[Trainer]()
      for(course <- courses) {
        for(trainer <- course.instructors) {
           trainers += trainer
        }
      }
      for(t <- trainers) {
        val tuple = (t.name,t.rate)
        printTabbed(tuple)
      }
      println("---")
    }
    def theNumberOfAdvancedCourses() {
      println("The number of advanced courses")
      var count = 0
      for(course <- courses) {
        if(course.courseType == ADVANCED) {
          count += 1
        }
      }
      println(count)
      println("---")
    }
    def totalDurationsOfBeginnerAndNonBeginnerCourses() {
      println("Total days for both beginner and non-beginner courses")
      var beginnerDuration = 0
      var nonBeginnerDuration = 0
      for(course <- courses) {
        if(course.courseType == BEGINNER) {
          beginnerDuration += course.duration
        } else {
          nonBeginnerDuration += course.duration
        }
      }
      println((beginnerDuration,nonBeginnerDuration))
      println("---")
    }
    def everyPairOfTrainersThatCouldDeliverJava() {
      println("Pairs of trainers that could deliver Java")
      var trainers = HashSet[Trainer]()
      for(course <- courses) {
        for(trainer <- course.instructors) {
          if(trainer.skills.contains("Java")) {
            trainers += trainer
          }
        }
      }
      var namePairs = ListBuffer[(String,String)]()
      for(trainer1 <- trainers) {
        for(trainer2 <- trainers) {
          if(trainer1 != trainer2) {
            val tuple = (trainer1.name, trainer2.name)
            namePairs += tuple
          }
        }
      }
      for(pair <- namePairs) {
        if(namePairs.contains((pair._2,pair._1))) {
          namePairs.remove(namePairs.indexOf(pair))
        }
      }
      for(pair <- namePairs) {
        printf("%s and %s\n",pair._1,pair._2)
      }

      println("---")
    }
    def possibleCostsOfJeeWebDevelopment() {
      println("Possible costs of 'JEE Web Development'")
      var selected: Course = null
      for(course <- courses) {
        if(course.title == "JEE Web Development") {
          selected = course
        }
      }
      if(selected != null) {
        val duration = selected.duration
        for(instructor <- selected.instructors) {
          val tuple = (instructor.name, instructor.rate * duration)
          printTabbed(tuple)
        }
      }
      println("---")
    }
    def coursesIdsAndTitlesGroupedByType() {
      println("Course ID's and titles grouped by type")
      val coursesByType = HashMap[CourseType.Value, ListBuffer[Course]]()
      for(course <- courses) {
        if(!coursesByType.contains(course.courseType)) {
          coursesByType.put(course.courseType, new ListBuffer[Course]())
        }
        coursesByType(course.courseType) += course
      }
      for(row <- coursesByType) {
        printTabbed(row._1)
        for(course <- row._2) {
          printf("\t\t%s %s\n", course.id, course.title)
        }
      }
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
