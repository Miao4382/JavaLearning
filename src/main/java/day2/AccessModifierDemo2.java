package day2;



import day3.AccessDemo;

public class AccessModifierDemo2 {
    public static void main(String[] args) {
        AccessDemo demo = new AccessDemo();

        // in different package and non-subclass, we can only access public field
        System.out.println(demo.num3);

    }
}
