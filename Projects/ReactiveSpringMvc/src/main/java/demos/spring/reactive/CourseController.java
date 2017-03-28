package demos.spring.reactive;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import demos.spring.reactive.model.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
public class CourseController {
	@GetMapping(value="/courses", produces="application/json")
	public Flux<Course> fetchAllTheCoursesAsJSON() {
		return repository.allCourses();
	}
	@GetMapping(value="/courses", produces="application/xml")
	public Mono<CourseList> fetchAllTheCoursesAsXML() {
		CourseList courses = new CourseList(new ArrayList<>());
		return repository.allCourses()
						 .reduce(courses, (a,b) -> a.add(b));
	}
	@GetMapping("/courses/{id}")
	public Mono<Course> findById(@PathVariable String id) {
		return this.repository.singleCourse(id);
	}
	@PutMapping("/courses")
	Mono<Void> addOrUpdate(@RequestBody Mono<Course> courseStream) {
		return this.repository.addOrUpdateCourse(courseStream).then();
	}
	@Autowired
	public void setRepository(CourseRepository repository) {
		this.repository = repository;
	}
	private CourseRepository repository;
}
