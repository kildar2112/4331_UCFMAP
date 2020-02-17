import java.io.*;
import java.util.*;
import java.awt.*;


public class Tests {
	// Tests to make sure the calcPercentage method in Garage class works
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

	// Test to make sure changeStart in the Route class works
	private static void test_changeStart() {
		// Test a point between positive 1 billion and negative 1 billion

		for (int testNumber = 1; testNumber <= 10000; testNumber++) {
			Point testPoint = new Point(0 ,0);
			testPoint.x = (int)(Math.random() * ((int)2e9 + 1) - (int)(1e9));
			testPoint.y = (int)(Math.random() * ((int)2e9 + 1) - (int)(1e9));

			test_changeStart(testPoint);
		}

		// Test extremes
		Point testPointMAX = new Point(Integer.MAX_VALUE, Integer.MAX_VALUE);
		test_changeStart(testPointMAX);

		Point testPointMIN = new Point(Integer.MIN_VALUE, Integer.MIN_VALUE);
		test_changeStart(testPointMIN);

		System.out.println("Passed test_changeStart");
	}

	// Test to make sure changeEnd in the Route class works
	private static void test_changeEnd() {
		// Test a point between positive 1 billion and negative 1 billion

		for (int testNumber = 1; testNumber <= 10000; testNumber++) {
			Point testPoint = new Point(0 ,0);
			testPoint.x = (int)(Math.random() * ((int)2e9 + 1) - (int)(1e9));
			testPoint.y = (int)(Math.random() * ((int)2e9 + 1) - (int)(1e9));

			test_changeEnd(testPoint);
		}

		// Test extremes
		Point testPointMAX = new Point(Integer.MAX_VALUE, Integer.MAX_VALUE);
		test_changeEnd(testPointMAX);

		Point testPointMIN = new Point(Integer.MIN_VALUE, Integer.MIN_VALUE);
		test_changeEnd(testPointMIN);

		System.out.println("Passed test_changeEnd");
	}

	// Test to make sure updateColor in Garage class works
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

	private static void test_calcPercentage(int totalSpots, int filledSpots) {
		Garage garage = new Garage("Test Garage", totalSpots);

		garage.updateSpots(filledSpots);
		garage.calcPercentage();

		int expectedOutput = (int)((double)filledSpots / totalSpots * 100);
		int actualOutput = garage.percentage;

		if (actualOutput != expectedOutput) {
			System.err.println("test_calcPercentage error.\n inputs: totalSpots- " +
													totalSpots + " filledSpots- " + filledSpots + " \n"+
													"expected output: " + expectedOutput + "\n"
													+ "actual output: " + actualOutput);
		}
	}

	private static void test_changeStart(Point newStartPoint) {
		Route route = new Route();
		route.changeStart(newStartPoint);

		if (route.start.x != newStartPoint.x || route.start.y != newStartPoint.y) {
			System.err.println("test_changeStart error.\n inputs: Point p-" + newStartPoint +
												 " expected results: x- " + newStartPoint.x + " y- " + newStartPoint.y +
												 " actual results: x- " + route.start.x + " y- " +
												 route.start.y);
		}
	}

	private static void test_changeEnd(Point newEndPoint) {
		Route route = new Route();
		route.changeEnd(newEndPoint);
		if(route.end.x != newEndPoint.x || route.end.y != newEndPoint.y) {
			System.err.println("test_changeEnd error.\n inputs: Point p-" + newEndPoint +
												 " expected results: x- " + newEndPoint.x + " y- " + newEndPoint.y +
												 " given results: x- " + route.end.x + " y- " +
												 route.end.y);
		}
	}

	private static void test_updateColor(int percentage) {
		Garage garage = new Garage("Test Garage", 10);
		garage.percentage = percentage;
		garage.updateColor();

		Color expectedColor = Color.white;
		if (percentage <= 33)
			expectedColor = Color.green;
		else if (percentage <= 66)
			expectedColor = Color.yellow;
		else if (percentage <= 99)
			expectedColor = Color.red;
		else if (percentage == 100)
			expectedColor = Color.black;

		if (!garage.color.equals(expectedColor))
			System.err.println("test_updateColor error\n inputs : percentage- " + percentage +
			  									" expected result: Color- " + expectedColor + " actual result : Color- " + garage.color);
	}

	private static void RunTests() {
		// These methods can be found in the Garage class
		test_calcPercentage();
		test_updateColor();
		// These methods can be found in the Route class
		test_changeStart();
		test_changeEnd();
	}

	public static void main(String [] args) {
		RunTests();
	}
}
