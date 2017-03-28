package demos.spring.reactive.model;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="instilCourses")
public class CourseList {
    public CourseList(){
    }
    public CourseList(List<Course> courses){
		this.courses = courses;
}
	@XmlElement(name="greatCourse")
    public List<Course> getCourses() {
        return courses;
    }
    public void setCourses(List<Course> courses) {
        this.courses = courses;
    }
    public CourseList add(Course course) {
    		courses.add(course);
    		return this;
    }
    private List<Course> courses;
}
