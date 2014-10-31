/**
 * @(#) Waiter.java
 */

package RestaurantGame;

import RestaurantGame.enums.ExperienceLevel;

public class Waiter extends Employee {

	public Waiter(String name, String surname) {
		super(name, surname);
		setExperienceLevel(ExperienceLevel.LOW);
		setSalary(200);
	}

	public void computeSalary() {

	}

}
