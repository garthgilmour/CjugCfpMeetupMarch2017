package learning.fp.java8.kata.shared;

import java.util.ArrayList;
import java.util.List;

import static java.util.Arrays.asList;

public class Builder {
    public static List<Course> buildData() {
        List<Course> courses = new ArrayList<Course>();
        Trainer dave = new Trainer("Dave Jones", 500.0, asList("SQL","Perl","PHP"));
        Trainer jane = new Trainer("Jane Smith", 750.0, asList("SQL", "Java", "JEE"));
        Trainer pete = new Trainer("Pete Hughes", 1000.0, asList("Java", "JEE", "C#", "Scala"));
        Trainer mary = new Trainer("Mary Donaghy", 1250.0, asList("Java", "JEE", "C#", "C++"));
        courses.add(new Course("AB12", "Intro to Scala", CourseType.BEGINNER, 4, asList(pete)));
        courses.add(new Course("CD34", "JEE Web Development", CourseType.INTERMEDIATE, 3, asList(pete, mary, jane)));
        courses.add(new Course("EF56", "Meta-Programming in Ruby", CourseType.ADVANCED, 2, asList()));
        courses.add(new Course("GH78", "OO Design with UML", CourseType.BEGINNER, 3, asList(jane, pete, mary)));
        courses.add(new Course("IJ90", "Database Access with JPA", CourseType.INTERMEDIATE, 3, asList(jane)));
        courses.add(new Course("KL12", "Design Patterns in C#", CourseType.ADVANCED, 2, asList(pete, mary)));
        courses.add(new Course("MN34", "Relational Database Design", CourseType.BEGINNER, 4, asList(jane, dave)));
        courses.add(new Course("OP56", "Writing MySql Stored Procedures", CourseType.INTERMEDIATE, 1, asList(jane, dave)));
        courses.add(new Course("QR78", "Patterns of Parallel Programming", CourseType.ADVANCED, 2, asList(pete, mary)));
        courses.add(new Course("ST90", "C++ Programming for Beginners", CourseType.BEGINNER, 5, asList(mary)));
        courses.add(new Course("UV12", "UNIX Threading with PThreads", CourseType.INTERMEDIATE, 2, asList()));
        courses.add(new Course("WX34", "Writing Linux Device Drivers", CourseType.ADVANCED, 3, asList(mary)));

        return courses;
    }
}
