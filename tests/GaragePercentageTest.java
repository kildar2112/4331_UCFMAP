import java.io.*;
import java.util.*;

public class GaragePercentageTest {
  private static void test_calcPercentage(int totalSpots, int filledSpots) {
		Garage garage = new Garage("Test Garage", totalSpots);

		garage.updateSpots(filledSpots);
		garage.calcPercentage();

		int expectedOutput = (int)((double)filledSpots / totalSpots * 100);
		int actualOutput = garage.getPercentage();

		if (actualOutput != expectedOutput) {
			System.err.println("test_calcPercentage error.\n inputs: totalSpots- " +
													totalSpots + " filledSpots- " + filledSpots + " \n"+
													"expected output: " + expectedOutput + "\n"
													+ "actual output: " + actualOutput);
		}
	}

  private static void test_updateColor() {
		// Test a random percentage updates the colors correctly
		for (int testNumber = 1; testNumber <= 10000; testNumber++) {
			int testPercent = (int)(Math.random() * 101); // Random percentage from 0-100%
			test_updateColor(testPercent);
		}

		// Test two extremes
		test_updateColor(100);
		test_updateColor(0);

		System.out.println("Passed test_updateColor");
	}

  public static void main(String [] args) {
    test_calcPercentage();
  }
}
