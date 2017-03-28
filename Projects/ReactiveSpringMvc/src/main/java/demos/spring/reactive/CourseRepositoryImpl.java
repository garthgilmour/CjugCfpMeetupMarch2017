package demos.spring.reactive;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import demos.spring.reactive.model.Course;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Component
public class CourseRepositoryImpl implements CourseRepository {
	@Autowired
	public CourseRepositoryImpl(@Qualifier("portfolio") List<Course> portfolio) {
		this.portfolio = portfolio;
	}
	public Mono<Course> singleCourse(String id) {
		Optional<Course> selected = portfolio.stream()
										     .filter(c -> c.getId().equals(id))
										     .findAny();
		return Mono.justOrEmpty(selected);
	}
	public Flux<Course> allCourses() {
		return Flux.fromIterable(portfolio);
	}
	public Mono<Void> addOrUpdateCourse(Mono<Course> mono) {
		return mono.then(this::adder);
	}
	private Mono<Void> adder(Course course) {
		if(portfolio.contains(course)) {
			portfolio.remove(course);
		}
		portfolio.add(course);
		return Mono.empty();
	}
	private List<Course> portfolio;
}
