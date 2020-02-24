import java.util.*;
import java.io.*;

public class GarageColorTest {
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

    if (!garage.getColor().equals(expectedColor))
      System.err.println("test_updateColor error\n inputs : percentage- " + percentage +
                          " expected result: Color- " + expectedColor + " actual result : Color- " + garage.getColor());
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
    test_updateColor();
  }
}
