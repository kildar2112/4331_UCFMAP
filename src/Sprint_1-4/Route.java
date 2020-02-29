import java.io.*;
import java.util.*;
import java.awt.*;

// This class calculates routes between two points
public class Route {
	public Point start;
	public Point end;
	public ArrayList<Point> intermediates;

	public Route() {
		this.start = new Point(0, 0);
		this.end = new Point(0, 0);
	}

	public void changeStart(Point newStartPoint) {
		start.x = newStartPoint.x;
		start.y = newStartPoint.y;
	}

	public void changeEnd(Point newEndPoint) {
		end.x = newEndPoint.x;
		end.y = newEndPoint.y;
	}

	public void findRoute(Point start, Point end) {

	}

	public void turnOn() {

	}

	public void turnOff() {

	}
}
