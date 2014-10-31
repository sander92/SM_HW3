/**
 * @(#) Chef.java
 */

package RestaurantGame;

import RestaurantGame.enums.ExperienceLevel;

public class Chef extends Employee {
	private String taxCode;

	public Chef(String name, String surname, String taxCode) {
		super(name, surname);
		setExperienceLevel(ExperienceLevel.LOW);
		setSalary(300);
		setTaxCode(taxCode);
	}

	public void computeSalary() {

	}

	public String getTaxCode() {
		return taxCode;
	}

	public void setTaxCode(String taxCode) {

	}

}
