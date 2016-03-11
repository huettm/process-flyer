/**
 * 
 */
package com.olia.processflyer.shared.bpmn.template;

/**
 * @author philipp
 *
 */
public class Point {

	private double x;
	private double y;
	private double z;

	public Point(double x, double y, double z) {
		super();
		this.x = x;
		this.y = y;
		this.z = z;
	}

	public double getX() {
		return x;
	}

	public double getY() {
		return y;
	}

	public double getZ() {
		return z;
	}

	public Point subtract(Point other) {

		return new Point(x - other.getX(), y - other.getY(), z - other.z);
	}

	public Point add(Point other) {
		return new Point(x + other.getX(), y + other.getY(), z + other.z);
	}

}
