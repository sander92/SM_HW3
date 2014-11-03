/**
 * @(#) GameController.java
 */

package RestaurantGame;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Random;

import util.CollectionUtil;
import RestaurantGame.enums.ExperienceLevel;
import RestaurantGame.enums.QualityLevel;

@SuppressWarnings("unused")
public class GameController {
	private static PriorityQueue<Player> scoreBoard;
	private Player player;
	private Restaurant restaurant;
	private List<Customer> customersBank;
	
	private float runningSupplierBill;
	private Integer gameLength = 30; 
	private Integer HighReputationTreshold = 30;
	private Integer MediumReputationTreshold = 15;
	private Integer HighReputationTablesOccupied = 9;
	private Integer MediumReputationTablesOccupied = 5;
	private Integer LowReputationTablesOccupied = 2;
	
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
		player.initPlayer(input);
		restaurant.initRestaurant(input);
		restaurant.initEmployees();
		restaurant.initTables();
		restaurant.initMenuItems(input);
		initCustomerBank();
		

		Random unusedRandomGenerator = new Random();
		List<Integer> unUsedClients = new ArrayList<>();
		for (int day = 1; day <= gameLength; day++) {
			System.out.println("\nKDay " + day + ": Budget " + restaurant.getBudget() + ", Reputation: " + restaurant.getReputation());
			initWaitersToTables(input);
			sendToTraining(input);

			for (int i = 0; i < 18; i++) {
				unUsedClients.add(i);
			}
			if (restaurant.getReputation() > HighReputationTreshold ) {
				for (int i = 0; i < HighReputationTablesOccupied; i++) {
					Integer nextClientIdx1 = getNextClientIdx(unusedRandomGenerator, unUsedClients);
					Integer nextClientIdx2 = getNextClientIdx(unusedRandomGenerator, unUsedClients);
					simulateCustomers(restaurant.getTables().get(i), day, customersBank.get(nextClientIdx1), customersBank.get(nextClientIdx2));
				}
			} else if (restaurant.getReputation() >  MediumReputationTreshold) {
				for (int i = 0; i < MediumReputationTablesOccupied; i++) {
					Integer nextClientIdx1 = getNextClientIdx(unusedRandomGenerator, unUsedClients);
					Integer nextClientIdx2 = getNextClientIdx(unusedRandomGenerator, unUsedClients);
					simulateCustomers(restaurant.getTables().get(i), day, customersBank.get(nextClientIdx1), customersBank.get(nextClientIdx2));
				}
			} else { // low reputation
				for (int i = 0; i < LowReputationTablesOccupied; i++) {
					Integer nextClientIdx1 = getNextClientIdx(unusedRandomGenerator, unUsedClients);
					Integer nextClientIdx2 = getNextClientIdx(unusedRandomGenerator, unUsedClients);
					simulateCustomers(restaurant.getTables().get(i), day, customersBank.get(nextClientIdx1), customersBank.get(nextClientIdx2));
				}
			}

			if (day % 7 == 0) {
				restaurant.paySuppliers(runningSupplierBill);
				restaurant.paySalaries();
			}

			if (day % 30 == 0){
				restaurant.payUtilities();
			}
			if (restaurant.getBudget() < 0) {
				System.out.println("You ran out of money.");
				break;
			}
		}

		printCustomerStatistics();
		System.out.println("Game over!");
		System.out.println("Final score: " + restaurant.getBudget());
		scoreBoard.add(player);
		System.out.println(scoreBoard);
	}

	
	
	private void printCustomerStatistics() {
		for (Customer customer : customersBank) {
			System.out.println(customer.getName() + " " + customer.getSurname());
			List<Visit> visits = customer.getVisits();
			if (visits == null || visits.size() == 0) {
				System.out.println("Never came over :(\n");
				continue;
			}
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
					for (int i = 0; i < itemsToCounts.get(item); i++) {
						calories.add(d.getCalorieCount());
					}
				} else if (item instanceof Beverage) {
					Beverage b = (Beverage) item;
					for (int i = 0; i < itemsToCounts.get(item); i++) {
						volumes.add(b.getVolume());
					}
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

		Integer newReputation = restaurant.getReputation();
		
		// If no waiter, reduce reputation
		if (table.getWaiter() == null) {		
			restaurant.setReputation(newReputation - 3);
			return;
		}
		
		List<Beverage> beverages = restaurant.getMenuitemByType(Beverage.class);
		Beverage b1 = beverages.get(new Random().nextInt(beverages.size()));
		Beverage b2 = beverages.get(new Random().nextInt(beverages.size()));

		List<Dish> dishes = restaurant.getMenuitemByType(Dish.class);
		Dish d1 = dishes.get(new Random().nextInt(dishes.size()));
		Dish d2 = dishes.get(new Random().nextInt(dishes.size()));

		runningSupplierBill += b1.getPreparationCost() + b2.getPreparationCost() + d1.getPreparationCost() + d2.getPreparationCost();

		Visit visit1 = makeVisit(table, b1, d1, day);
		Visit visit2 = makeVisit(table, b2, d2, day);

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


	private void initWaitersToTables(BufferedReader input) {
		List<Employee> emp = restaurant.getEmployees();
		List<Table> tables = restaurant.getTables();
		for (Table table : tables) {
			table.assignWaiter(null);
		}
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
		for (Employee employee : employees) {
			if (employee.getExperienceLevel() == ExperienceLevel.HIGH){
				continue;
			}
			System.out.println("Send " + employee + " to training? (y/n)");
			String yesNo = "";
			try {
				yesNo = input.readLine();
			} catch (IOException e) {
				e.printStackTrace();
			}
			if ("y".equalsIgnoreCase(yesNo)) {
				trainEmployee(employee);
			}
		}
	}

	@SuppressWarnings("incomplete-switch")
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
