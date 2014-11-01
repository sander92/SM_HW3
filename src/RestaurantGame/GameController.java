/**
 * @(#) GameController.java
 */

package RestaurantGame;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Random;

import RestaurantGame.enums.ExperienceLevel;
import RestaurantGame.enums.QualityLevel;

public class GameController {
	private Player player;

	private Restaurant restaurant;

	private static PriorityQueue<Player> scoreBoard;
	private Integer highNo;
	private Integer lowNo;

	public GameController() {
		player = new Player();
		restaurant = new Restaurant();
		scoreBoard = new PriorityQueue<Player>(new Comparator<Player>() {

			@Override
			public int compare(Player o1, Player o2) {
				return o1.getScore() - o2.getScore();
			}
		});
	}

	public void startGame() throws IOException {
		System.out.println("Hello new player!");
		System.out.println("Welcome to Restaurant Game!");
		BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
		System.out.print("What is your name: ");
		String name = input.readLine();
		player.setName(name);

		restaurant.setBudget(10_000);
		initRestaurant(input);
		initEmployees();
		initTables();
		initMenuItems(input);
		// TODO check kas on ok input

		restaurant.setReputation(15);

		// choose client
		new Random().nextInt(18);

		for (int day = 1; day <= 30; day++) {
			System.out.println("Day " + day + ": Budget " + restaurant.getBudget());
			initWaitersToTables(input);

			if (day % 7 == 0) {
				List<MenuItem> menu = restaurant.getMenu();
				float cost = 0;
				for (MenuItem menuItem : menu) {
					cost += menuItem.getPreparationCost();
				}
				float nBudget = restaurant.getBudget() - cost * 7;
				restaurant.setBudget(nBudget);
			}

			if (restaurant.getBudget() < 0) {
				System.out.println("You ran out of money");
				break;
			}
		}
		restaurant.setBudget(restaurant.getBudget() - 4000);
		System.out.println("Game over!");
		System.out.println("Final score: " + restaurant.getBudget());
		scoreBoard.add(player);
		System.out.println(scoreBoard);
	}

	// TODO check kas on ok input
	private void initMenuItems(BufferedReader input) throws IOException {
		List<MenuItem> menu = null;
		while (true) {
			try {
				System.out.print("What is nr of High quality menuitems: ");
				// TODO need peaks panema restorani sisse vist
				this.highNo = Integer.parseInt(input.readLine());

				System.out.print("What is nr of Low quality menuitems: ");
				this.lowNo = Integer.parseInt(input.readLine());

				System.out.print("What is the cost of High quality menuitems: ");
				float highCost = Integer.parseInt(input.readLine());

				System.out.print("What is the cost of Low quality menuitems: ");
				float lowCost = Integer.parseInt(input.readLine());

				menu = new ArrayList<>();
				makeItems(highCost, "High", highNo, menu, true);
				makeItems(lowCost, "Low", lowNo, menu, false);
				break;
			} catch (Exception e) {
				System.err.println("\nError");
			}
		}

		restaurant.setMenu(menu);
	}

	private void initTables() {
		List<Table> tables = new ArrayList<>();

		for (int i = 1; i <= 9; i++) {
			tables.add(new Table(i));
		}
		restaurant.setTables(tables);
	}

	private void initEmployees() {
		List<Employee> employees = new ArrayList<>();
		employees.add(new Waiter("Jim", "Parsons"));
		employees.add(new Waiter("James", "Man"));
		employees.add(new Waiter("Johnny", "Money"));

		employees.add(new Chef("Icook", "Foods", "coooode"));
		employees.add(new Barman("Bever", "Agemaker"));
		restaurant.setEmployees(employees);
	}

	private void initRestaurant(BufferedReader input) throws IOException {
		System.out.print("What is Restaurant's name: ");
		String resName = input.readLine();
		restaurant.setName(resName);

		System.out.print("What is Restaurant's address: ");
		String address = input.readLine();
		restaurant.setAddress(address);

		System.out.print("What is Restaurant's city: ");
		String city = input.readLine();
		restaurant.setCity(city);
	}

	private void makeItems(float cost, String namePrefix, Integer nrOfItems, List<MenuItem> menu, boolean isHighQuality) {
		for (int i = 0; i < nrOfItems; i++) {
			Dish dish = new Dish();
			dish.setName(namePrefix + "Dish " + i);
			dish.setQualityLevel(isHighQuality ? QualityLevel.HIGH : QualityLevel.LOW);
			dish.setPreparationCost(isHighQuality ? 10 : 3);
			dish.setSellingPrice(cost);
			menu.add(dish);

			Beverage bev = new Beverage();
			bev.setName(namePrefix + "Bev" + i);
			bev.setQualityLevel(isHighQuality ? QualityLevel.HIGH : QualityLevel.LOW);
			bev.setPreparationCost(isHighQuality ? 3 : 1);
			bev.setSellingPrice(cost);
			menu.add(bev);
		}
	}

	private void initWaitersToTables(BufferedReader input) {
		List<Employee> emp = restaurant.getEmployees();
		List<Table> tables = restaurant.getTables();
		int start = 0;
		for (Employee employee : emp) {
			if (employee instanceof Waiter) {
				int count = getTableCountForWaiter(input, employee);
				tables.subList(start, start + count + 1);
				start = start + count + 1;
			}
		}
	}

	private int getTableCountForWaiter(BufferedReader input, Employee employee) {
		int count = 0;
		while (true) {
			System.out.print("How many tables for " + employee + ": ");
			try {
				count = Integer.parseInt(input.readLine());
			} catch (NumberFormatException | IOException e) {
				System.err.println("Insert proper nr please");
				continue;
			}
			// TODO because if count !=3, then some table is
			// not assigned a waiter
			if (count > 3 || count < 0) {
				System.out.println("0 to 3 tables please!");
				continue;
			}
			break;
		}
		return count;
	}

	public void trainEmployee(Employee employee) {
		float cost = 1200;
		if (employee instanceof Waiter) {
			cost = 800;
		}
		if (restaurant.getBudget() < cost) {
			throw new IllegalArgumentException("Not enough money");
		}

		restaurant.setBudget(restaurant.getBudget() - 800);

		switch (employee.getExperienceLevel()) {
		case LOW:
			employee.setExperienceLevel(ExperienceLevel.MEDIUM);
			break;
		case MEDIUM:
			employee.setExperienceLevel(ExperienceLevel.HIGH);
			break;
		}
	}

	public void setPrice(float lowDishCost, float highDishCost, float lowBeverageCost, float highBeverageCost) {

	}

	public void chooseName(String name) {

	}

	public void setDishesQuality(Integer highNo, Integer lowNo) {
		this.highNo = highNo;
		this.lowNo = lowNo;
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
