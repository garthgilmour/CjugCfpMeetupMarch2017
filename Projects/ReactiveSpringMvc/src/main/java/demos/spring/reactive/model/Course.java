package demos.spring.reactive.model;

import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Course {
	public Course() {
		super();
	}
	public Course(String id, String title, CourseType type, int duration, List<Trainer> instructors) {
		super();
		this.id = id;
		this.title = title;
		this.type = type;
		this.duration = duration;
		this.instructors = instructors;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public CourseType getType() {
		return type;
	}
	public void setType(CourseType type) {
		this.type = type;
	}
	public int getDuration() {
		return duration;
	}
	public void setDuration(int duration) {
		this.duration = duration;
	}
	public List<Trainer> getInstructors() {
		return instructors;
	}
	public void setInstructors(List<Trainer> instructors) {
		this.instructors = instructors;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Course other = (Course) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	private String id;
	private String title;
	private CourseType type;
	private int duration;
	private List<Trainer> instructors;
}
