package lms.corejava.assignment3;

public abstract class Shape implements Comparable<Shape> {
    public abstract double getArea();

    @Override
    public int compareTo(Shape s) {
        if (this.getArea() > s.getArea()) {
            return 1;
        } else if (Math.abs(this.getArea() - s.getArea()) < 1e-9) {
            return 0;
        } else {
            return -1;
        }
    }
}
