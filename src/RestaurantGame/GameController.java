/**
 * @(#) GameController.java
 */

package RestaurantGame;

public class GameController {
	private Player player;

	private Restaurant restaurant;

	public void startGame() {

	}

	public void trainEmployee(Employee employee) {

	}

	public void setPrice(float lowDishCost, float highDishCost,
			float lowBeverageCost, float highBeverageCost) {

	}

	public void chooseName(String name) {

	}

	public void setDishesQuality(Integer highNo, Integer lowNo) {

	}

	public void setBeveragesQuality(Integer highNo, Integer lowNo) {

	}

	public Player getPlayer() {
		return player;
	}

	public void setPlayer(Player player) {
		this.player = player;
	}

	public Restaurant getRestaurant() {
		return restaurant;
	}

	public void setRestaurant(Restaurant restaurant) {
		this.restaurant = restaurant;
	}

	public static void main(String[] args) {
		GameController gameController = new GameController();
		gameController.startGame();
	}
}
