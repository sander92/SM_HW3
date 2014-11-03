/**
 * @(#) Player.java
 */

package RestaurantGame;

import java.io.IOException;
import java.io.BufferedReader;

public class Player {
	private String name = "";

	private int score;

	private Restaurant restaurant;

	public String getName() {
		return name;
	}

	public void setScore(int score) {
		this.score = score;
	}

	public int getScore() {
		return score;
	}

	public void setRestaurant(Restaurant restaurant) {
		this.restaurant = restaurant;
	}

	public Restaurant getRestaurant() {
		return restaurant;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void initPlayer(BufferedReader input) {
		String name = "";
		while ("".equals(name)) {
			System.out.print("What is your name: ");
			try {
				name = input.readLine();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		this.name = name;
	}

}
