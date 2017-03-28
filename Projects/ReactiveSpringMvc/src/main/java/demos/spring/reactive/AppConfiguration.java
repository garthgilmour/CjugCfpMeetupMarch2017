package demos.spring.reactive;

import static java.util.Arrays.*;
import java.util.ArrayList;
import java.util.List;

import org.springframework.context.annotation.*;

import static demos.spring.reactive.model.CourseType.*;
import demos.spring.reactive.model.*;

@Configuration
public class AppConfiguration {
	@Bean(name="portfolio")
	public List<Course> buildPortfolio() {
		List<Course> courses = new ArrayList<Course>();
		Trainer jane = new Trainer("Jane Smith", 750.0, asList("SQL", "Java", "JEE"));
		Trainer pete = new Trainer("Pete Hughes", 1000.0, asList("Java", "JEE", "C#", "Scala"));
		Trainer mary = new Trainer("Mary Donaghy", 1250.0, asList("Java", "JEE", "C#", "C++"));
		courses.add(new Course("AB12", "Intro to Scala", BEGINNER, 4, asList(pete)));
		courses.add(new Course("CD34", "JEE Web Development", INTERMEDIATE, 3, asList(pete, mary, jane)));
		courses.add(new Course("EF56", "Meta-Programming in Ruby", ADVANCED, 2, asList()));
		courses.add(new Course("GH78", "OO Design with UML", BEGINNER, 3, asList(jane, pete, mary)));
		courses.add(new Course("IJ90", "Database Access with JPA", INTERMEDIATE, 3, asList(jane)));
		courses.add(new Course("KL12", "Design Patterns in C#", ADVANCED, 2, asList(pete, mary)));
		courses.add(new Course("MN34", "Relational Database Design", BEGINNER, 4, asList(jane)));
		courses.add(new Course("OP56", "Writing MySql Stored Procedures", INTERMEDIATE, 1, asList(jane)));
		courses.add(new Course("QR78", "Patterns of Parallel Programming", ADVANCED, 2, asList(pete, mary)));
		courses.add(new Course("ST90", "C++ Programming for Beginners", BEGINNER, 5, asList(mary)));
		courses.add(new Course("UV12", "UNIX Threading with PThreads", INTERMEDIATE, 2, asList()));
		courses.add(new Course("WX34", "Writing Linux Device Drivers", ADVANCED, 3, asList(mary)));
	
		return courses;
	}
}
