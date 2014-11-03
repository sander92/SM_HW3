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

	private int highQualityDishNo;
	private int lowQualityDishNo;
	private float highQualityDishCost;
	private float lowQualityDishCost;

	private int highQualityBeverageNo;
	private int lowQualityBeverageNo;
	private float highQualityBeverageCost;
	private float lowQualityBeverageCost;
	
	public int highQualityDishIngredientCost = 10;
	public int lowQualityDishIngredientCost = 3;
	public int highQualityBeverageIngredientCost = 3;
	public int lowQualityBeverageIngredientCost = 1;
	
	private float utilitiesCost = 4000;
	
	public Restaurant() {
		setBudget(10_000);
		setReputation(15);
	}

	public void paySuppliers(float amount) {
		this.budget = this.budget - amount;
	}

	public void computeReputation(Boolean clientSatisfaction) {

	}

	public void payUtilities() {
		this.budget = this.budget - utilitiesCost;
	}

	public void paySalaries() {
		for (Employee employee : employees){
			this.budget = this.budget - employee.getSalary();
		}
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
	

	
	public void initMenuItems(BufferedReader input) throws IOException {
		List<MenuItem> menu = null;
		while (true) {
			try {
				//TODO: possibly move to separate methods
				int highQualityDishNo, lowQualityDishNo;
				while(true){
					System.out.print("What is nr of High quality dishes: ");
					highQualityDishNo = Integer.parseInt(input.readLine());

					System.out.print("What is nr of Low quality dishes: ");
					lowQualityDishNo = Integer.parseInt(input.readLine());
					
					if (highQualityDishNo + lowQualityDishNo != 5){
						System.out.println("Number of high and low quality dishes must add up to 5.");
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
						System.out.println("Dish cost must be positive.");
					}
					else {
						this.highQualityDishCost = highQualityDishCost;
						this.lowQualityDishCost = lowQualityDishCost;
						break;
					}
				}

				int highQualityBeverageNo, lowQualityBeverageNo;
				while(true){
					System.out.print("What is nr of High quality beverages: ");
					highQualityBeverageNo = Integer.parseInt(input.readLine());

					System.out.print("What is nr of Low quality beverages: ");
					lowQualityBeverageNo = Integer.parseInt(input.readLine());
					
					if (highQualityBeverageNo + lowQualityBeverageNo != 5){
						System.out.println("Number of high and low quality beverages must add up to 5.");
					}
					else {
						this.highQualityBeverageNo = highQualityBeverageNo;
						this.lowQualityBeverageNo = lowQualityBeverageNo;
						break;
					}
				}
				
				int highQualityBeverageCost, lowQualityBeverageCost;
				while(true){
					System.out.print("What is the cost of High quality beverages: ");
					highQualityBeverageCost = Integer.parseInt(input.readLine());

					System.out.print("What is the cost of Low quality beverages: ");
					lowQualityBeverageCost = Integer.parseInt(input.readLine());
					
					if (highQualityBeverageCost <= 0 || lowQualityBeverageCost <= 0){
						System.out.println("Beverage cost must be positive.");
					}
					else {
						this.highQualityBeverageCost = highQualityBeverageCost;
						this.lowQualityBeverageCost = lowQualityBeverageCost;
						break;
					}
				}
							
				menu = new ArrayList<>();
				makeDishItems(highQualityDishCost, "High", highQualityDishNo, menu, true);
				makeDishItems(lowQualityDishCost, "Low", lowQualityDishNo, menu, false);
				makeBeverageItems(highQualityBeverageCost, "High", highQualityBeverageNo, menu, true);
				makeBeverageItems(lowQualityBeverageCost, "Low", lowQualityBeverageNo, menu, false);
				break;
			} catch (Exception e) {
				System.err.println("\nError when initialising menu items");
			}
		}

		this.menu = menu;
	}
	
	
	private void makeDishItems(float cost, String namePrefix, Integer nrOfItems, List<MenuItem> menu, boolean isHighQuality) {
		Random volumeAndCalories = new Random();
		for (int i = 0; i < nrOfItems; i++) {
			Dish dish = new Dish();
			dish.setName(namePrefix + "Dish " + i);
			dish.setQualityLevel(isHighQuality ? QualityLevel.HIGH : QualityLevel.LOW);
			dish.setPreparationCost(isHighQuality ? highQualityDishIngredientCost : lowQualityDishIngredientCost);
			dish.setSellingPrice(cost);
			dish.setCalorieCount(volumeAndCalories.nextInt(1000) / 10);
			menu.add(dish);
		}
	}
	
	private void makeBeverageItems(float cost, String namePrefix, Integer nrOfItems, List<MenuItem> menu, boolean isHighQuality) {
		Random volumeAndCalories = new Random();
		for (int i = 0; i < nrOfItems; i++) {
			Beverage bev = new Beverage();
			bev.setName(namePrefix + "Bev" + i);
			bev.setQualityLevel(isHighQuality ? QualityLevel.HIGH : QualityLevel.LOW);
			bev.setPreparationCost(isHighQuality ? highQualityBeverageIngredientCost : lowQualityBeverageIngredientCost);
			bev.setSellingPrice(cost);
			bev.setVolume(volumeAndCalories.nextInt(1000) / 10);
			menu.add(bev);
		}
	}

}
