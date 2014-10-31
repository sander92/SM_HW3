/**
 * @(#) GameController.java
 */

package RestaurantGame;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import RestaurantGame.enums.ExperienceLevel;

public class GameController {
	private Player player;

	private Restaurant restaurant;

	private Integer highNo;
	private Integer lowNo;

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

		System.out.print("What is Restaurant's name: ");
		String resName = input.readLine();
		restaurant.setName(resName);

		System.out.print("What is Restaurant's address: ");
		String address = input.readLine();
		restaurant.setAddress(address);

		System.out.print("What is Restaurant's city: ");
		String city = input.readLine();
		restaurant.setCity(city);

		restaurant.setBudget(10_000);

		List<Employee> employees = new ArrayList<>();
		employees.add(new Waiter("Jim", "Parsons"));
		employees.add(new Waiter("James", "Man"));
		employees.add(new Waiter("Johnny", "Money"));

		employees.add(new Chef("Icook", "Foods", "coooode"));
		employees.add(new Barman("Bever", "Agemaker"));
		restaurant.setEmployees(employees);

		List<Table> tables = new ArrayList<>();

		for (int i = 1; i <= 9; i++) {
			tables.add(new Table(i));
		}
		restaurant.setTables(tables);

		System.out.print("What is nr of High quality menuitems: ");
		this.highNo = Integer.parseInt(input.readLine());
		
		System.out.print("What is nr of Low quality menuitems: ");
		this.lowNo = Integer.parseInt(input.readLine());

		//TODO check kas on ok input
		
		restaurant.setReputation(15);
		
		// choose client
		new Random().nextInt(18);
	}

	public void trainEmployee(Employee employee) {
		ExperienceLevel level = employee.getExperienceLevel();
		switch (level) {
		case LOW:
			
			break;
		case MEDIUM:
			
			break;
		case HIGH:
			
			break;

		default:
			//error
			break;
		}
	}

	public void setPrice(float lowDishCost, float highDishCost,
			float lowBeverageCost, float highBeverageCost) {

	}

	public void chooseName(String name) {

	}

	public void setDishesQuality(Integer highNo, Integer lowNo) {
		this.highNo=highNo;
		this.lowNo=lowNo;
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
