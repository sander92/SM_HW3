/**
 * @(#) Restaurant.java
 */

package RestaurantGame;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import RestaurantGame.enums.QualityLevel;

public class Restaurant {
	private String name;

	private String city;

	private String address;

	private float budget;

	private Integer reputation;

	private List<Visit> visits;

	private java.util.List<Employee> employees;

	private java.util.List<Table> tables;

	private java.util.List<MenuItem> menu;

	private int highQualityDishCost;
	private int lowQualityDishCost;

	private int highQualityDishNo;
	private int lowQualityDishNo;

	public Restaurant() {
		setBudget(10_000);
		setReputation(15);
	}

	public void paySuppliers(float amount) {

	}

	public void computeReputation(Boolean clientSatisfaction) {

	}

	public void payUtilities(float amount) {

	}

	public void paySalaries(float amount) {

	}

	public void populateTables() {

	}

	public void computeClientStatistics() {

	}

	public void payTraining(float amount) {

	}

	public void processOrder(Dish dish, Beverage beverage, Table table) {

	}

	public String getName() {
		return name;
	}

	public String getCity() {
		return city;
	}

	public String getAddress() {
		return address;
	}

	public void setBudget(float budget) {
		this.budget = budget;
	}

	public float getBudget() {
		return budget;
	}

	public Integer getReputation() {
		return reputation;
	}

	public void setTables(List<Table> tables) {
		this.tables = tables;
	}

	public List<Table> getTables() {
		return tables;
	}

	public void setVisits(List<Visit> visits) {
		this.visits = visits;
	}

	public List<Visit> getVisits() {
		return visits;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public void setReputation(Integer reputation) {
		this.reputation = reputation;
	}

	public void setEmployees(List<Employee> employees) {
		this.employees = employees;
	}

	public List<Employee> getEmployees() {
		return employees;
	}

	public void setMenu(java.util.List<MenuItem> menu) {
		this.menu = menu;
	}

	public java.util.List<MenuItem> getMenu() {
		return menu;
	}

	public <T> List<T> getMenuitemByType(Class<T> clazz) {
		List<T> lst = new ArrayList<T>();
		for (MenuItem item : menu) {
			if (item.getClass() == clazz) {
				lst.add((T) item);
			}
		}
		return lst;
	}

	public <T> List<T> getEmployeeByType(Class<T> clazz) {
		List<T> lst = new ArrayList<T>();
		for (Employee employee : employees) {
			if (employee.getClass() == clazz) {
				lst.add((T) employee);
			}
		}
		return lst;
	}


	public void initRestaurant(BufferedReader input) throws IOException {
		String resName = "", address = "", city = "";
		try {
			while (resName == ""){
				System.out.print("What is Restaurant's name: ");
				resName = input.readLine();
			}
			this.name = resName;

			while (address == ""){
				System.out.print("What is Restaurant's address: ");
				address = input.readLine();
			}
			this.address = address;

			while (city == ""){
				System.out.print("What is Restaurant's city: ");
				city = input.readLine();
			}
			this.city = city;

			this.visits = new ArrayList<>();
		}
		catch (IOException e){
			e.printStackTrace();
		}
	}
	
	public void initEmployees() {
		List<Employee> employees = new ArrayList<>();
		employees.add(new Waiter("Jim", "Parsons"));
		employees.add(new Waiter("James", "Man"));
		employees.add(new Waiter("Johnny", "Money"));

		employees.add(new Chef("Icook", "Foods", "coooode"));
		employees.add(new Barman("Bever", "Agemaker"));
		this.employees = employees;
	}

	public void initTables() {
		List<Table> tables = new ArrayList<>();

		for (int i = 1; i <= 9; i++) {
			tables.add(new Table(i));
		}
		this.tables = tables;
	}
	

	// TODO check kas on ok input
	public void initMenuItems(BufferedReader input) throws IOException {
		List<MenuItem> menu = null;
		while (true) {
			try {
				
				int highQualityDishNo, lowQualityDishNo;
				while(true){
					System.out.print("What is nr of High quality dishes: ");
					highQualityDishNo = Integer.parseInt(input.readLine());

					System.out.print("What is nr of Low quality dishes: ");
					lowQualityDishNo = Integer.parseInt(input.readLine());
					
					if (highQualityDishNo + lowQualityDishNo != 5){
						System.out.print("Number of high and low quality dishes must add up to 5.");
					}
					else {
						this.highQualityDishNo = highQualityDishNo;
						this.lowQualityDishNo = lowQualityDishNo;
						break;
					}
				}
				
				int highQualityDishCost, lowQualityDishCost;
				while(true){
					System.out.print("What is the cost of High quality dishes: ");
					highQualityDishCost = Integer.parseInt(input.readLine());

					System.out.print("What is the cost of Low quality dishes: ");
					lowQualityDishCost = Integer.parseInt(input.readLine());
					
					if (highQualityDishCost <= 0 || lowQualityDishCost <= 0){
						System.out.print("Dish cost must be positive.");
					}
					else {
						this.highQualityDishCost = highQualityDishCost;
						this.lowQualityDishCost = lowQualityDishCost;
						break;
					}
				}
				
				//TODO: repeat for beverages
				//TODO: possibly move to separate methods


				menu = new ArrayList<>();
				makeItems(highQualityDishCost, "High", highQualityDishNo, menu, true);
				makeItems(lowQualityDishCost, "Low", lowQualityDishNo, menu, false);
				break;
			} catch (Exception e) {
				System.err.println("\nError");
			}
		}

		this.menu = menu;
	}
	
	
	//TODO: separate dish and beverage creation
	private void makeItems(float cost, String namePrefix, Integer nrOfItems, List<MenuItem> menu, boolean isHighQuality) {
		Random volumeAndCalories = new Random();
		for (int i = 0; i < nrOfItems; i++) {
			Dish dish = new Dish();
			dish.setName(namePrefix + "Dish " + i);
			dish.setQualityLevel(isHighQuality ? QualityLevel.HIGH : QualityLevel.LOW);
			dish.setPreparationCost(isHighQuality ? 10 : 3);
			dish.setSellingPrice(cost);
			dish.setCalorieCount(volumeAndCalories.nextInt(1000) / 10);
			menu.add(dish);

			Beverage bev = new Beverage();
			bev.setName(namePrefix + "Bev" + i);
			bev.setQualityLevel(isHighQuality ? QualityLevel.HIGH : QualityLevel.LOW);
			bev.setPreparationCost(isHighQuality ? 3 : 1);
			bev.setSellingPrice(cost);
			bev.setVolume(volumeAndCalories.nextInt(1000) / 10);
			menu.add(bev);
		}
	}
}
