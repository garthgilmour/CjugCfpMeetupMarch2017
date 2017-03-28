package learning.fp.java8.kata.start;

import learning.fp.java8.kata.shared.*;
import static learning.fp.java8.kata.shared.CourseType.*;

import java.util.*;

public class Program {

	public static void main(String[] args) {
		List<Course> courses = Builder.buildData();

		titlesOfCourses(courses);
	    titlesOfCoursesWithoutATrainer(courses);
	    namesAndRatesOfTrainers(courses);
        numberOfAdvancedCourses(courses);
	    totalDurationsOfBeginnerAndNonBeginnerCourses(courses);
	    everyPairOfTrainersThatCouldDeliverJava(courses);
	    possibleCostsOfJeeWebDevelopment(courses);
	    coursesIdsAndTitlesGroupedByType(courses);
	}
	private static void titlesOfCourses(List<Course> courses) {
		System.out.println("The titles of all the courses are:");
        for(Course c : courses) {
            printTabbed(c.getTitle());
        }
	}
	private static void titlesOfCoursesWithoutATrainer(List<Course> courses) {
		System.out.println("The titles of all the courses without a trainer are:");
        for(Course c : courses) {
            if(c.getInstructors().isEmpty()) {
                printTabbed(c.getTitle());
            }
        }
	}
	private static void namesAndRatesOfTrainers(List<Course> courses) {
		System.out.println("The names and rates of all trainers are:");
        Set<Trainer> trainers = new HashSet<>();
        for(Course c : courses) {
            for(Trainer t : c.getInstructors()) {
                trainers.add(t);
            }
        }
        for(Trainer t : trainers) {
            String msg = String.format("%s at %.2f",t.getName(),t.getRate());
            printTabbed(msg);
        }
	}
    private static void numberOfAdvancedCourses(List<Course> courses) {
        System.out.println("The number of advanced courses:");
        int count = 0;
        for(Course c : courses) {
            if (c.getType() == ADVANCED) {
                count++;
            }
        }
        System.out.println("\t" + count);
    }
	private static void totalDurationsOfBeginnerAndNonBeginnerCourses(List<Course> courses) {
		System.out.println("Total course durations are:");
        int durationForBeginners = 0;
        int durationForNonBeginners = 0;
        for(Course c : courses) {
            if(c.getType() == BEGINNER) {
                durationForBeginners += c.getDuration();
            } else {
                durationForNonBeginners += c.getDuration();
            }
        }
        System.out.println("\tTotal for beginners is: " + durationForBeginners);
        System.out.println("\tTotal for non-beginners is: " + durationForNonBeginners);
	}
	private static void everyPairOfTrainersThatCouldDeliverJava(List<Course> courses) {
		System.out.println("Every pair of trainers that could deliver Java:");
        Set<Trainer> javaTrainers = new HashSet<>();
        for(Course c : courses) {
            for(Trainer t : c.getInstructors()) {
                if(t.getSkills().contains("Java")) {
                    javaTrainers.add(t);
                }
            }
        }
        Set<Pair<String,String>> trainerPairs = new HashSet<>();
        for(Trainer t1 : javaTrainers) {
            for(Trainer t2 : javaTrainers) {
                if(t1 != t2) {
                    Pair<String,String> pair = new Pair<>(t1.getName(),t2.getName());
                    if(!trainerPairs.contains(pair)) {
                        trainerPairs.add(pair);
                    }
                }
            }
        }
        for(Pair<String,String> p : trainerPairs) {
            printTabbed(p);
        }
	}
	private static void possibleCostsOfJeeWebDevelopment(List<Course> courses) {
		System.out.println("Possible costs of 'JEE Web Development'");
        Course selected = null;
        for(Course c : courses) {
            if(c.getTitle().equals("JEE Web Development")) {
                selected = c;
            }
        }
        if(selected != null) {
            String msg = "\t%s at a cost of %.2f\n";
            for (Trainer t : selected.getInstructors()) {
                System.out.printf(msg, t.getName(), t.getRate() * selected.getDuration());
            }
        }
	}
	private static void coursesIdsAndTitlesGroupedByType(List<Course> courses) {
		System.out.println("Course id's and titles grouped by type are:");
		String msg = "\tCourses of type %s are\n";

        Map<CourseType,List<Course>> coursesByType = new HashMap<>();
        for(Course c : courses) {
            if(!coursesByType.containsKey(c.getType())) {
                coursesByType.put(c.getType(), new ArrayList<>());
            }
            coursesByType.get(c.getType()).add(c);
        }
        for(Map.Entry<CourseType,List<Course>> row : coursesByType.entrySet()) {
            System.out.printf(msg, row.getKey());
            List<Course> coursesInGroup = row.getValue();
            for(Course c : coursesInGroup) {
                System.out.println("\t\t" + c.getTitle());
            }
        }
    }
	private static <T> void printTabbed(T input) {
		System.out.print("\t");
		System.out.println(input.toString());
	}
}
