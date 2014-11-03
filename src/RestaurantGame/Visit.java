/**
 * @(#) Visit.java
 */

package RestaurantGame;

import RestaurantGame.enums.QualityLevel;
import java.util.Date;
import java.util.Random;
import RestaurantGame.enums.ExperienceLevel;

public class Visit {
	private Date daytime;

	private boolean isSatisfiedWithService;

	private boolean isSatisfiedWithBeverage;

	private boolean isSatisfiedWithFood;

	private Table table;

	private Barman barman;

	private Beverage beverage;

	private Dish dish;

	private Chef chef;

	public void calculateSatisfactionLevel( ) {
		if (table.getWaiter() != null) {
			calcSatisfiedWithService();
			calcSatisfiedWithBeverage();
			calcSatisfiedWithFood();
		}
	}

	private void calcSatisfiedWithService( ) {
		Random r = new Random();
		int random = r.nextInt(100) + 1;

		isSatisfiedWithService = getProbablyTrue(random, table.getWaiter().getExperienceLevel(), 60, 80, 90);
	}

	private void calcSatisfiedWithBeverage( ) {
		Random r = new Random();
		int random = r.nextInt(100) + 1;
		boolean isHigh = beverage.getQualityLevel().equals(QualityLevel.HIGH);
		float diff = beverage.getSellingPrice() - beverage.getPreparationCost();
		diff = (int) (diff / 3);
		isSatisfiedWithBeverage = getProbablyTrue(random, barman.getExperienceLevel(), isHigh ? getForDiff(60, diff) : getForDiff(40, diff),
				isHigh ? getForDiff(80, diff) : getForDiff(60, diff), isHigh ? getForDiff(100, diff) : getForDiff(80, diff));
	}

	private void calcSatisfiedWithFood( ) {
		Random r = new Random();
		int random = r.nextInt(100) + 1;
		boolean isHigh = dish.getQualityLevel().equals(QualityLevel.HIGH);
		float diff = dish.getSellingPrice() - dish.getPreparationCost();
		diff = (int) (diff / 3);
		isSatisfiedWithFood = getProbablyTrue(random, chef.getExperienceLevel(), isHigh ? getForDiff(60, diff) : getForDiff(40, diff),
				isHigh ? getForDiff(80, diff) : getForDiff(60, diff), isHigh ? getForDiff(100, diff) : getForDiff(80, diff));
	}

	private float getForDiff( int percentage, float diffCost ) {
		float nPercent = percentage - 10 * diffCost;
		if (nPercent < 0) {
			return 0;
		} else if (nPercent > 100) {
			return 100;
		}
		return nPercent;
	}

	private boolean getProbablyTrue( int random, ExperienceLevel level, float low, float med, float high ) {
		switch (level) {
		case LOW:
			if (random < low) {
				return true;
			}
			break;
		case MEDIUM:
			if (random < med) {
				return true;
			}
			break;
		case HIGH:
			if (random < high) {
				return true;
			}
			break;
		}
		return false;
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
