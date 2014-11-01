/**
 * @(#) Beverage.java
 */

package RestaurantGame;

public class Beverage extends MenuItem {
	private float volume;

	public void setVolume(float volume) {
		this.volume = volume;
	}

	public float getVolume() {
		return volume;
	}

	@Override
	public String toString() {
		return "Beverage [name=" + getName() + ", qualityLevel=" + getQualityLevel() + "]";
	}
}
