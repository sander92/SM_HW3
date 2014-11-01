/**
 * @(#) GameController.java
 */

package RestaurantGame;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Random;

import util.CollectionUtil;
import RestaurantGame.enums.ExperienceLevel;
import RestaurantGame.enums.QualityLevel;

public class GameController {
	private static PriorityQueue<Player> scoreBoard;
	private Player player;

	private Restaurant restaurant;
	private List<Customer> customersBank;
	private Integer highNo;
	private Integer lowNo;
	private float runningSupplierBill;

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

		initRestaurant(input);
		initEmployees();
		initTables();
		initMenuItems(input);
		initCustomerBank();
		// TODO check kas on ok input

		// choose client
		Random unusedRandom = new Random();
		List<Integer> unUsedClients = new ArrayList<>();
		for (int day = 1; day <= 30; day++) {
			System.out.println("Day " + day + ": Budget " + restaurant.getBudget() + ", Reputation: " + restaurant.getReputation());
			initWaitersToTables(input);
			sendToTraining(input);

			for (int i = 0; i < 18; i++) {
				unUsedClients.add(i);
			}
			if (restaurant.getReputation() > 30) {
				// TODO kas see on õige nii teha?
				// või peaks mingit pidi siduma asjad omavahel kokku
				for (int i = 0; i < 10; i++) {
					Integer nextClientIdx1 = getNextClientIdx(unusedRandom, unUsedClients);
					Integer nextClientIdx2 = getNextClientIdx(unusedRandom, unUsedClients);
					simulateCustomers(restaurant.getTables().get(i), day, customersBank.get(nextClientIdx1), customersBank.get(nextClientIdx2));
				}
			} else if (restaurant.getReputation() > 15) {
				for (int i = 0; i < 5; i++) {
					Integer nextClientIdx1 = getNextClientIdx(unusedRandom, unUsedClients);
					Integer nextClientIdx2 = getNextClientIdx(unusedRandom, unUsedClients);
					simulateCustomers(restaurant.getTables().get(i), day, customersBank.get(nextClientIdx1), customersBank.get(nextClientIdx2));
				}
			} else {
				for (int i = 0; i < 2; i++) {
					Integer nextClientIdx1 = getNextClientIdx(unusedRandom, unUsedClients);
					Integer nextClientIdx2 = getNextClientIdx(unusedRandom, unUsedClients);
					simulateCustomers(restaurant.getTables().get(i), day, customersBank.get(nextClientIdx1), customersBank.get(nextClientIdx2));
				}
			}

			if (day % 7 == 0) {
				float nBudget = restaurant.getBudget() - runningSupplierBill;
				runningSupplierBill = 0;
				restaurant.setBudget(nBudget);
			}

			if (restaurant.getBudget() < 0) {
				System.out.println("You ran out of money");
				break;
			}
		}

		printCustomerStatistics();
		restaurant.setBudget(restaurant.getBudget() - 4000);
		System.out.println("Game over!");
		System.out.println("Final score: " + restaurant.getBudget());
		scoreBoard.add(player);
		System.out.println(scoreBoard);
	}

	private void printCustomerStatistics() {
		for (Customer customer : customersBank) {
			System.out.println(customer.getName() + " " + customer.getSurname());
			List<Visit> visits = customer.getVisits();
			HashMap<MenuItem, Integer> itemsToCounts = new HashMap<>();
			float moneySpent = 0;
			for (Visit visit : visits) {
				countItems(itemsToCounts, visit.getBeverage());
				countItems(itemsToCounts, visit.getDish());
				moneySpent += visit.getBeverage().getSellingPrice();
				moneySpent += visit.getDish().getSellingPrice();
			}
			List<Float> calories = new ArrayList<>();
			List<Float> volumes = new ArrayList<>();
			for (MenuItem item : itemsToCounts.keySet()) {
				if (item instanceof Dish) {
					Dish d = (Dish) item;
					calories.add(d.getCalorieCount());
				} else if (item instanceof Beverage) {
					Beverage b = (Beverage) item;
					volumes.add(b.getVolume());
				}
				System.out.println(item + ": " + itemsToCounts.get(item) + " times");
			}
			System.out.println("Average calorie count: " + CollectionUtil.average(calories));
			System.out.println("Average volume: " + CollectionUtil.average(volumes));
			System.out.println("Money spent: " + moneySpent);
			System.out.println();
		}
	}

	private void countItems(HashMap<MenuItem, Integer> itemsToCounts, MenuItem item) {
		if (itemsToCounts.containsKey(item)) {
			itemsToCounts.put(item, itemsToCounts.get(item) + 1);
		} else {
			itemsToCounts.put(item, 1);
		}
	}

	private void initCustomerBank() {
		customersBank = new ArrayList<>();
		for (int i = 0; i < 18; i++) {
			Customer c = new Customer();
			c.setName("Name " + i);
			c.setSurname("SurName " + i);
			c.setTaxCode("code" + i);
			c.setTelephoneNr("2435245" + i);
			c.setVisits(new ArrayList<>());
			customersBank.add(c);
		}
	}

	private Integer getNextClientIdx(Random unusedRandom, List<Integer> unUsedClients) {
		int rnd = unusedRandom.nextInt(unUsedClients.size());
		Integer nextIdx = unUsedClients.get(rnd);
		unUsedClients.remove(rnd);
		return nextIdx;
	}

	private void simulateCustomers(Table table, Integer day, Customer customer1, Customer customer2) {
		List<Beverage> beverages = restaurant.getMenuitemByType(Beverage.class);
		Beverage b1 = beverages.get(new Random().nextInt(beverages.size()));
		Beverage b2 = beverages.get(new Random().nextInt(beverages.size()));

		List<Dish> dishes = restaurant.getMenuitemByType(Dish.class);
		Dish d1 = dishes.get(new Random().nextInt(dishes.size()));
		Dish d2 = dishes.get(new Random().nextInt(dishes.size()));

		runningSupplierBill += b1.getPreparationCost() + b2.getPreparationCost() + d1.getPreparationCost() + d2.getPreparationCost();

		// TODO customer things
		Visit visit1 = makeVisit(table, b1, d1, day);
		Visit visit2 = makeVisit(table, b2, d2, day);

		// TODO refactor ja feature envy välja viia, i.e. restorani asjad resto
		// klassi jne
		calcIsSatisfied(visit1);

		customer1.getVisits().add(visit1);
		customer2.getVisits().add(visit2);

		restaurant.getVisits().add(visit1);
		restaurant.getVisits().add(visit2);
		float budget = restaurant.getBudget() + b1.getSellingPrice() + b2.getSellingPrice() + d1.getSellingPrice() + d2.getSellingPrice();
		restaurant.setBudget(budget);
	}

	private void calcIsSatisfied(Visit visit) {
		Integer reputation = restaurant.getReputation();
		if (visit.isIsSatisfiedWithBeverage()) {
			reputation += 1;
		} else {
			reputation -= 1;
		}
		if (visit.isIsSatisfiedWithFood()) {
			reputation += 1;
		} else {
			reputation -= 1;
		}
		if (visit.isIsSatisfiedWithService()) {
			reputation += 1;
		} else {
			reputation -= 1;
		}
		restaurant.setReputation(reputation);
	}

	private Visit makeVisit(Table table, Beverage beverage1, Dish dish1, Integer day) {
		Visit visit = new Visit();
		visit.setBarman(restaurant.getEmployeeByType(Barman.class).get(0));
		visit.setChef(restaurant.getEmployeeByType(Chef.class).get(0));
		visit.setTable(table);
		visit.setDaytime(new Date());
		visit.setBeverage(beverage1);
		visit.setDish(dish1);
		visit.calculateSatisfactionLevel();
		return visit;
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

		restaurant.setVisits(new ArrayList<>());
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
				List<Table> subList = tables.subList(start, start + count);
				Waiter w = (Waiter) employee;
				for (Table table : subList) {
					table.assignWaiter(w);
				}

				start = start + count;
			}
		}
		List<Table> subList = tables.subList(start, tables.size());
		for (Table table : subList) {
			table.assignWaiter(null);
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

	private void sendToTraining(BufferedReader input) {
		List<Employee> employees = restaurant.getEmployees();
		for (int i = 0; i < employees.size(); i++) {
			System.out.println("Send " + employees.get(i) + " to training? (y/n)");
			String yesNo = "";
			try {
				yesNo = input.readLine();
			} catch (IOException e) {
				e.printStackTrace();
			}
			if ("y".equalsIgnoreCase(yesNo)) {
				trainEmployee(employees.get(i));
			}
		}
	}

	public void trainEmployee(Employee employee) {
		float cost = 1200;
		if (employee instanceof Waiter) {
			cost = 800;
		}
		if (restaurant.getBudget() < cost) {
			System.out.println("Not enough money");
			return;
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
		System.out.println(employee + " trained for €" + cost);
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
