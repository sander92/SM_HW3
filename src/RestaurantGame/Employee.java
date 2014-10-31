/**
 * @(#) Employee.java
 */

package RestaurantGame;

import RestaurantGame.enums.ExperienceLevel;

public abstract class Employee {
	private String name;

	private String surname;

	private float salary;

	private ExperienceLevel experienceLevel;

	public void increaseExperience( ) {

	}

	public void computeSalary( ) {

	}

	public String getName( ) {
		return name;
	}

	public String getSurname( ) {
		return surname;
	}

	public void setSalary( float salary ) {
		this.salary = salary;
	}

	public float getSalary( ) {
		return salary;
	}

	public void setExperienceLevel( ExperienceLevel experienceLevel ) {
		this.experienceLevel = experienceLevel;
	}

	public ExperienceLevel getExperienceLevel( ) {
		return experienceLevel;
	}

	public void setName( String name ) {
		this.name = name;
	}

	public void setSurname( String surname ) {
		this.surname = surname;
	}

}
