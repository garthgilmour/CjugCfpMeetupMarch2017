package demos.spring.reactive;

import demos.spring.reactive.model.Course;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface CourseRepository {
	Mono<Course> singleCourse(String id);
	Flux<Course> allCourses();
	Mono<Void> addOrUpdateCourse(Mono<Course> mono);
}