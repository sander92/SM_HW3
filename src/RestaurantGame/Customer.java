/**
 * @(#) Customer.java
 */

package RestaurantGame;

import java.util.List;
import java.util.ArrayList;

public class Customer {
	private String name;

	private String surname;

	private String telephoneNr;

	private String taxCode;

	private List<Visit> visits;

	public String getName( ) {
		return name;
	}

	public String getSurname( ) {
		return surname;
	}

	public String getTelephoneNr( ) {
		return telephoneNr;
	}

	public String getTaxCode( ) {
		return taxCode;
	}

	public void setVisits( List<Visit> visits ) {
		this.visits = visits;
	}

	public List<Visit> getVisits( ) {
		if (visits == null) {
			visits = new ArrayList<>();
		}
		return visits;
	}

	public void setName( String name ) {
		this.name = name;
	}

	public void setSurname( String surname ) {
		this.surname = surname;
	}

	public void setTelephoneNr( String telephoneNr ) {
		this.telephoneNr = telephoneNr;
	}

	public void setTaxCode( String taxCode ) {
		this.taxCode = taxCode;
	}

}
