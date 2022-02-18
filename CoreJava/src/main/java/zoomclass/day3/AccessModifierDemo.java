package zoomclass.day3;

public class AccessModifierDemo {

    public static void main(String[] args) {
        // this is within the same package of class AccessDemo
        AccessDemo demo = new AccessDemo();
        // in same package, non-subclass, we can access num2,3,4
        System.out.println(demo.num2);
        System.out.println(demo.num3);
        System.out.println(demo.num4);
    }
}
