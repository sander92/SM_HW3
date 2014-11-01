/**
 * @(#) Table.java
 */

package RestaurantGame;

public class Table {

	public Table(int i) {
		number = i;
	}

	private int number;

	private Waiter waiter;

	public void assignWaiter(Waiter waiter) {
		this.waiter = waiter;
	}

	public void setNumber(int number) {
		this.number = number;
	}

	public int getNumber() {
		return number;
	}

	public void setWaiter(Waiter waiter) {
		this.waiter = waiter;
	}

	public Waiter getWaiter() {
		return waiter;
	}

	@Override
	public String toString() {
		return "Table " + number;
	}
}
