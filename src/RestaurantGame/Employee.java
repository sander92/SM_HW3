/**
 * @(#) Employee.java
 */

package RestaurantGame;

import RestaurantGame.enums.ExperienceLevel;

public abstract class Employee {
	private String name;

	private String surname;

	private ExperienceLevel experienceLevel;

	public Employee(String name, String surname) {
		this.name = name;
		this.surname = surname;
	}

	public String getName() {
		return name;
	}

	public String getSurname() {
		return surname;
	}

	public float getSalary() {
		switch (getExperienceLevel()) {
		default:
		case LOW:
			return 300;
		case MEDIUM:
			return 400;
		case HIGH:
			return 500;
		}
	}

	public void setExperienceLevel(ExperienceLevel experienceLevel) {
		this.experienceLevel = experienceLevel;
	}

	public ExperienceLevel getExperienceLevel() {
		return experienceLevel;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	@Override
	public String toString() {
		return getClass().getSimpleName() + "[name: " + getName() + " " + getSurname() + ",experienceLevel: " + getExperienceLevel() + "]";
	}
}
