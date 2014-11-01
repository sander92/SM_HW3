/**
 * @(#) Restaurant.java
 */

package RestaurantGame;

import java.util.ArrayList;
import java.util.List;

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

}
