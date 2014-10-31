/**
 * @(#) Barman.java
 */

package RestaurantGame;

import RestaurantGame.enums.ExperienceLevel;

public class Barman extends Employee {
	public Barman(String name, String surname) {
		super(name, surname);
		setExperienceLevel(ExperienceLevel.LOW);
		setSalary(300);
	}

	public void computeSalary() {

	}

}
