/**
 * @(#) Waiter.java
 */

package RestaurantGame;

import RestaurantGame.enums.ExperienceLevel;

public class Waiter extends Employee {

	public Waiter(String name, String surname) {
		super(name, surname);
		setExperienceLevel(ExperienceLevel.LOW);
	}

	@Override
	public float getSalary() {
		switch (getExperienceLevel()) {
		default:
		case LOW:
			return 200;
		case MEDIUM:
			return 300;
		case HIGH:
			return 400;
		}
	}

	@Override
	public String toString() {
		return "Waiter[name: " + getName() + " " + getSurname() + ",experienceLevel: " + getExperienceLevel() + "]";
	}

}
