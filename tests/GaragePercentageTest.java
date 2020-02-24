import java.io.*;
import java.util.*;
import java.awt.*;

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

  private static void test_calcPercentage() {
		int totalSpots = 1852; // 1852 corresponds to Garage C, the garage with the most spots

		// Test with 10000 random numbers
		for (int testNumber = 1; testNumber <= 10000; testNumber++) {
			int filledSpots = (int)(Math.random() * 1853); // The range is form 0 to 1852, or 0% - 100%
			test_calcPercentage(filledSpots, totalSpots);
		}

		// Test the two extremes
		test_calcPercentage(0, totalSpots);
		test_calcPercentage(totalSpots, totalSpots);

		System.out.println("Passed calcPercentage");
	}

  public static void main(String [] args) {
    test_calcPercentage();
  }
}
