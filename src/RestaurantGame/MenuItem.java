/**
 * @(#) MenuItem.java
 */

package RestaurantGame;

import RestaurantGame.enums.QualityLevel;

public abstract class MenuItem {
	private String name;

	private QualityLevel qualityLevel;

	private float preparationCost;

	private float sellingPrice;

	public String getName() {
		return name;
	}

	public void setQualityLevel(QualityLevel qualityLevel) {
		this.qualityLevel = qualityLevel;
	}

	public QualityLevel getQualityLevel() {
		return qualityLevel;
	}

	public void setPreparationCost(float preparationCost) {
		this.preparationCost = preparationCost;
	}

	public float getPreparationCost() {
		return preparationCost;
	}

	public void setSellingPrice(float sellingPrice) {
		this.sellingPrice = sellingPrice;
	}

	public float getSellingPrice() {
		return sellingPrice;
	}

	public void setName(String name) {
		this.name = name;
	}
}
