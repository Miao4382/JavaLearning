package lms.corejava.assignment3;

public class Square extends Rectangle{
    public Square(double len) {
        super(len, len);
    }

    @Override
    public String toString() {
        return "Square (len = " + getWidth() + ")";
    }
}
