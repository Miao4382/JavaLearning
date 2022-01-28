package day3;

public class ConstructorDemo {
}

class Parent {
    public Parent() {
        System.out.println("In Parent(num)");
    }
}

class Child extends Parent {
    private int num;

    public Child() {
        this(5);
    }

    public Child(int num) {
        this.num = num;
    }
}