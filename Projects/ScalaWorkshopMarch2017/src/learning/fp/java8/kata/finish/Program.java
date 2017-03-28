package learning.fp.java8.kata.finish;

import learning.fp.java8.kata.shared.*;
import static learning.fp.java8.kata.shared.CourseType.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Stream;

import static java.util.stream.Collectors.*;

public class Program {

	public static void main(String[] args) {
		List<Course> courses = Builder.buildData();

		titlesOfCourses(courses.stream());
	    titlesOfCoursesWithoutATrainer(courses.stream());
	    namesAndRatesOfTrainers(courses.stream());
        numberOfAdvancedCourses(courses.stream());
	    totalDurationsOfBeginnerAndNonBeginnerCoursesV1(courses);
	    totalDurationsOfBeginnerAndNonBeginnerCoursesV2(courses.stream());
	    totalDurationsOfBeginnerAndNonBeginnerCoursesV3(courses);
	    everyPairOfTrainersThatCouldDeliverJava(courses);
	    possibleCostsOfJeeWebDevelopment(courses);
	    coursesIdsAndTitlesGroupedByType(courses.stream());
	}
	private static void titlesOfCourses(Stream<Course> courses) {
		System.out.println("The titles of all the courses are:");
		courses.map(Course::getTitle)
			   .forEach(Program::printTabbed);
	}
	private static void titlesOfCoursesWithoutATrainer(Stream<Course> courses) {
		System.out.println("The titles of all the courses without a trainer are:");
		courses.filter(c -> c.getInstructors().isEmpty())
			   .map(Course::getTitle)
			   .forEach(Program::printTabbed);			
	}
	private static void namesAndRatesOfTrainers(Stream<Course> courses) {
		System.out.println("The names and rates of all trainers are:");
		courses.flatMap(c -> c.getInstructors().stream())
			   .distinct()
			   .map(t -> new Pair<>(t.getName(),t.getRate()))
			   .forEach(Program::printTabbed);
	}
    private static void numberOfAdvancedCourses(Stream<Course> courses) {
        System.out.println("The number of advanced courses:");
        int result = courses.collect(summingInt(c -> c.getType() == ADVANCED ? 1 : 0));
        System.out.println("\t" + result);
    }
	private static void totalDurationsOfBeginnerAndNonBeginnerCoursesV1(List<Course> courses) {
		System.out.println("Total course durations are:");
		totalDurationOfBeginnerCourses(courses.stream());
		totalDurationOfNonBeginnerCourses(courses.stream());
	}
	private static void totalDurationOfBeginnerCourses(Stream<Course> courses) {
		int result = courses.filter(c -> c.getType() == BEGINNER)
					 		.mapToInt(Course::getDuration)
					 		.sum();
		System.out.println("\tTotal for beginners is: " + result);
	}
	private static void totalDurationOfNonBeginnerCourses(Stream<Course> courses) {
		int result = courses.filter(c -> c.getType() != BEGINNER)
					 		.mapToInt(Course::getDuration)
					 		.sum();
		System.out.println("\tTotal for non-beginners is: " + result);
	}
	private static void totalDurationsOfBeginnerAndNonBeginnerCoursesV2(Stream<Course> courses) {
		Pair<Integer,Integer> result = courses.reduce(new Pair<>(0,0),Program::totalDurations,(a,b) -> null);
		System.out.println("Total course durations are:");
		System.out.println("\tTotal for beginners is: " + result.getFirst());
		System.out.println("\tTotal for non-beginners is: " + result.getSecond());
	}
	private static Pair<Integer,Integer> totalDurations(Pair<Integer,Integer> total, Course course) {
		int totalBeginners = total.getFirst();
		int totalNonBeginners = total.getSecond();
		int duration = course.getDuration();
		if(course.getType() == BEGINNER) {
			return new Pair<>(totalBeginners + duration, totalNonBeginners);
		} else {
			return new Pair<>(totalBeginners, totalNonBeginners + duration);
		}
	}
	private static void totalDurationsOfBeginnerAndNonBeginnerCoursesV3(List<Course> courses) {
		int durationForBeginner = courses.stream()
										 .filter(c -> c.getType() == BEGINNER)
										 .collect(summingInt(Course::getDuration));
		int durationForNonBeginner = courses.stream()
											.filter(c -> c.getType() != BEGINNER)
				 							.collect(summingInt(Course::getDuration));								 
		
		System.out.println("Total course durations are:");
		System.out.println("\tTotal for beginners is: " + durationForBeginner);
		System.out.println("\tTotal for non-beginners is: " + durationForNonBeginner);
	}
	private static void everyPairOfTrainersThatCouldDeliverJava(List<Course> courses) {
		System.out.println("Every pair of trainers that could deliver Java:");
		javaTrainers(courses).flatMap(t1 -> javaTrainers(courses).filter(t2 -> t1 != t2)
			   							   					    .map(t2 -> new Pair<>(t1,t2)))
			   				.map(p -> new Pair<>(p.getFirst().getName(),p.getSecond().getName()))
			   				.distinct()
			   				.forEach(Program::printTabbed);
	}
	private static Stream<Trainer> javaTrainers(List<Course> courses) {
		return courses.stream()
					  .flatMap(c -> c.getInstructors().stream())
			   	   	  .distinct()
			   	   	  .filter(t -> t.getSkills().contains("Java"));
	}
	private static void possibleCostsOfJeeWebDevelopment(List<Course> courses) {
		System.out.println("Possible costs of 'JEE Web Development'");
		Optional<Course> selected = courses.stream()
				 				   			 .filter(c -> c.getTitle().equals("JEE Web Development"))
				 				   			 .findFirst();
		
		String msg = "\t%s at a cost of %.2f\n";
		int duration = selected.map(c -> c.getDuration())
							   .orElse(0);
		selected.ifPresent(c -> c.getInstructors()
								 .stream()
								 .forEach(t -> System.out.printf(msg, t.getName(), t.getRate() * duration)));
	}
	private static void coursesIdsAndTitlesGroupedByType(Stream<Course> courses) {
		System.out.println("Course id's and titles grouped by type are:");
		Map<CourseType, List<String>> coursesByType = courses.collect(groupingBy(Course::getType, mapping(Course::getTitle,toList())));
		coursesByType.forEach(Program::printGroup);
	}
    private static void printGroup(CourseType key, List<String> values) {
        String msg = "\tCourses of type %s are:\n";
        System.out.printf(msg, key);
        values.stream().forEach(s -> System.out.println("\t\t" + s));
    }
	private static <T> void printTabbed(T input) {
		System.out.print("\t");
		System.out.println(input.toString());
	}
}
