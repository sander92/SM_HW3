/**
 * @(#) Restaurant.java
 */

package RestaurantGame;

public class Restaurant {
	private String name;

	private String city;

	private String address;

	private float budget;

	private Integer reputation;

	private Table table;

	private Visit visit;

	private Employee employee;

	public void paySuppliers( float amount ) {

	}

	public void computeReputation( Boolean clientSatisfaction ) {

	}

	public void payUtilities( float amount ) {

	}

	public void paySalaries( float amount ) {

	}

	public void populateTables( ) {

	}

	public void computeClientStatistics( ) {

	}

	public void payTraining( float amount ) {

	}

	public void processOrder( Dish dish, Beverage beverage, Table table ) {

	}

	public String getName( ) {
		return name;
	}

	public String getCity( ) {
		return city;
	}

	public String getAddress( ) {
		return address;
	}

	public void setBudget( float budget ) {
		this.budget = budget;
	}

	public float getBudget( ) {
		return budget;
	}

	public Integer getReputation( ) {
		return reputation;
	}

	public void setTable( Table table ) {
		this.table = table;
	}

	public Table getTable( ) {
		return table;
	}

	public void setVisit( Visit visit ) {
		this.visit = visit;
	}

	public Visit getVisit( ) {
		return visit;
	}

	public void setEmployee( Employee employee ) {
		this.employee = employee;
	}

	public Employee getEmployee( ) {
		return employee;
	}

	public void setName( String name ) {
		this.name = name;
	}

	public void setCity( String city ) {
		this.city = city;
	}

	public void setAddress( String address ) {
		this.address = address;
	}

	public void setReputation( Integer reputation ) {
		this.reputation = reputation;
	}

}
