package learning.fp.scala.kata.shared

import CourseType._

object Builder {
  def buildData() = {
    val dave = Trainer("Dave Jones", 500.0, List("SQL","Perl","PHP"))
    val jane = Trainer("Jane Smith", 750.0, List("SQL","Java","JEE"))
    val pete = Trainer("Pete Hughes", 1000.0, List("Java", "JEE", "C#", "Scala"))
    val mary = Trainer("Mary Donaghy", 1250.0, List("Java", "JEE", "C#", "C++"))

    List(Course("AB12","Intro to Scala",BEGINNER,4,List(pete)),
         Course("CD34","JEE Web Development",INTERMEDIATE,3,List(pete, mary, jane)),
         Course("EF56","Meta-Programming in Ruby",ADVANCED,2,List()),
         Course("GH78","OO Design with UML",BEGINNER,3,List(jane,pete,mary)),
         Course("IJ90","Database Access with JPA",INTERMEDIATE,3, List(jane)),
         Course("KL12","Design Patterns in C#",ADVANCED,2, List(pete,mary)),
         Course("MN34","Relational Database Design",BEGINNER,4, List(jane, dave)),
         Course("OP56","Writing MySql Stored Procedures",INTERMEDIATE,1, List(jane, dave)),
         Course("QR78","Patterns of Parallel Programming",ADVANCED,2, List(pete,mary)),
         Course("ST90","C++ Programming for Beginners",BEGINNER,5,List(mary)),
         Course("UV12","UNIX Threading with PThreads",INTERMEDIATE,2,List()),
         Course("WX34","Writing Linux Device Drivers",ADVANCED,3,List(mary)))
  }
}
