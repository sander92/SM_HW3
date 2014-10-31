/**
 * @(#) GameController.java
 */

package RestaurantGame;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class GameController {
	private Player player;

	private Restaurant restaurant;

	public GameController() {
		player = new Player();
		restaurant = new Restaurant();
	}

	public void startGame() throws IOException {
		System.out.println("Hello new player!");
		System.out.println("Welcome to Restaurant Game!");
		BufferedReader input = new BufferedReader(new InputStreamReader(
				System.in));
		System.out.print("What is your name: ");
		String name = input.readLine();
		player.setName(name);
		
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

	public static void main(String[] args) throws IOException {
		GameController gameController = new GameController();
		gameController.startGame();
	}
}
