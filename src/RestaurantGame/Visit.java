/**
 * @(#) Visit.java
 */

package RestaurantGame;

import java.util.Date;

public class Visit {
	private Date daytime;

	private boolean isSatisfiedWithService;

	private boolean isSatisfiedWithBeverage;

	private boolean isSatisfiedWithFood;

	private Customer customer;

	private Table table;

	private Barman barman;

	private Beverage beverage;

	private Dish dish;

	private Chef chef;

	public void calculateSatisfactionLevel( ) {

	}

	public void setIsSatisfiedWithService( boolean isSatisfiedWithService ) {
		this.isSatisfiedWithService = isSatisfiedWithService;
	}

	public boolean isIsSatisfiedWithService( ) {
		return isSatisfiedWithService;
	}

	public void setIsSatisfiedWithBeverage( boolean isSatisfiedWithBeverage ) {
		this.isSatisfiedWithBeverage = isSatisfiedWithBeverage;
	}

	public boolean isIsSatisfiedWithBeverage( ) {
		return isSatisfiedWithBeverage;
	}

	public void setIsSatisfiedWithFood( boolean isSatisfiedWithFood ) {
		this.isSatisfiedWithFood = isSatisfiedWithFood;
	}

	public boolean isIsSatisfiedWithFood( ) {
		return isSatisfiedWithFood;
	}

	public void setCustomer( Customer customer ) {
		this.customer = customer;
	}

	public Customer getCustomer( ) {
		return customer;
	}

	public void setTable( Table table ) {
		this.table = table;
	}

	public Table getTable( ) {
		return table;
	}

	public void setBarman( Barman barman ) {
		this.barman = barman;
	}

	public Barman getBarman( ) {
		return barman;
	}

	public void setBeverage( Beverage beverage ) {
		this.beverage = beverage;
	}

	public Beverage getBeverage( ) {
		return beverage;
	}

	public void setDish( Dish dish ) {
		this.dish = dish;
	}

	public Dish getDish( ) {
		return dish;
	}

	public void setChef( Chef chef ) {
		this.chef = chef;
	}

	public Chef getChef( ) {
		return chef;
	}

	public void setDaytime( Date daytime ) {
		this.daytime = daytime;
	}

	public Date getDaytime( ) {
		return daytime;
	}

}
