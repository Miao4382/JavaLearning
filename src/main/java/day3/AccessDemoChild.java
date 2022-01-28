package day3;

public class AccessDemoChild extends AccessDemo{
    public void printNums() {
        // in same package, subclass, we can access num2,3,4. But not private field num1
        System.out.println(super.num2);
        System.out.println(super.num3);
        System.out.println(super.num4);
    }
}
