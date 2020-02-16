import java.io.*;
import java.util.*;
import java.awt.*;


public class Test {
	// Tests to make sure the calcPercentage method is running correctly
	void test_calcPercentage(int totalSpots, int filledSpots) {
		garage = new Garage();
		int output;
		if (output = garage.calcPercent(totalSpots, filledSpots) != filledSpots / totalSpots)) {
			System.err.println("test_calcPercentage error.\n inputs: totalSpots- " +
													totalSpots + " filledSpots- " + filledSpots + " \n"+
													"expected output: " + (filledSpots / totalSpots) + "\n"
													+ "given output: " + output);
		}
	}

	// Tests to make sure the changeStart method is running correctly
	void test_changeStart(Point p) {
		route = new Route();
		route.changeStart(p);
		if(route.start.x != p.x || route.start.y != p.y) {
			System.err.println("test_changeStart error.\n inputs: Point p-" + p +
												 " expected results: x- " + p.x + " y- " + p.y +
												 " given results: x- " + route.start.x + " y- " +
												 route.start.y);
		}
	}

	// Tests to make sure the changeEnd method is running correctly
	void test_changeEnd(Point p) {
		route = new Route();
		route.changeEnd(p);
		if(route.end.x != p.x || route.end.y != p.y) {
			System.err.println("test_changeEnd error.\n inputs: Point p-" + p +
												 " expected results: x- " + p.x + " y- " + p.y +
												 " given results: x- " + route.end.x + " y- " +
												 route.end.y);
		}
	}
}
