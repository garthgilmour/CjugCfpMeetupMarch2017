package demos.spring.reactive.model;

import java.util.List;

public class Trainer {
	public Trainer() {
		super();
	}
	public Trainer(String name, double rate, List<String> skills) {
		super();
		this.name = name;
		this.rate = rate;
		this.skills = skills;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public double getRate() {
		return rate;
	}
	public void setRate(double rate) {
		this.rate = rate;
	}
	public List<String> getSkills() {
		return skills;
	}
	public void setSkills(List<String> skills) {
		this.skills = skills;
	}
	public String toString() {
		return name;
	}
	private String name;
	private double rate;
	private List<String> skills;
}
