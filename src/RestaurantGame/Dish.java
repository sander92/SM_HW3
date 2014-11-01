/**
 * @(#) Dish.java
 */

package RestaurantGame;

public class Dish extends MenuItem {
	private float calorieCount;

	public void setCalorieCount(float calorieCount) {
		this.calorieCount = calorieCount;
	}

	public float getCalorieCount() {
		return calorieCount;
	}

	@Override
	public String toString() {
		return "Dish [name=" + getName() + ", qualityLevel=" + getQualityLevel() + "]";
	}
}
