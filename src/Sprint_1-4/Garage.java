import java.io.*;
import java.util.*;
import java.awt.*;

// This class represents a garage
public class Garage {
	private String name;
	private final int totalSpots;
	private int filledSpots;
	public int percentage;
	private int levels;
	private Color color;
	private ArrayList<String> acceptedPermits;

	public Garage(String garageName, int totalSpots) {
		this.name = garageName;
		this.totalSpots = totalSpots;

		this.color = Color.green;
		this.acceptedPermits = new ArrayList<String>();
	}

	// Updates the number of occupied spots
	public void updateSpots(int filledSpots) {
		this.filledSpots = filledSpots;
	}

	// Calculates the percentage of spots taken
	public void calcPercentage() {
		this.percentage = (int)((double) this.filledSpots / this.totalSpots * 100);
	}

	// This updates the color label displaying garage fullness depending on the percentage field.
	// 0 - 33 is green, 34 - 66 is yellow, 67 - 99 is red, and 100 is black
	public void updateColor() {

		if (percentage <= 33)
			color = Color.green;
		else if (percentage <= 66)
			color = Color.yellow;
		else if (percentage <= 99)
			color = Color.red;
		else if (percentage == 100)
			color = Color.black;

	}

	public Color getColor() {
		return this.color;
	}

	public int getPercentage() {
		return this.percentage;
	}
}
