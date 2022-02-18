package lms.corejava.assignment3;

public class Circle extends Shape{

    static double PI = 3.1415926;
    private double radius;

    public Circle(double radius) {
        this.radius = radius;
    }

    @Override
    public double getArea() {
        return PI * radius * radius;
    }

    public double getRadius() {
        return radius;
    }

    public void setRadius(double radius) {
        this.radius = radius;
    }

    @Override
    public String toString() {
        return "Circle (r = " + radius + ")";
    }
}
