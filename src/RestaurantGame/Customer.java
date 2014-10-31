/**
 * @(#) Customer.java
 */

package RestaurantGame;

public class Customer {
	private String name;

	private String surname;

	private String telephoneNr;

	private String taxCode;

	private Visit visit;

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

	public void setVisit( Visit visit ) {
		this.visit = visit;
	}

	public Visit getVisit( ) {
		return visit;
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
