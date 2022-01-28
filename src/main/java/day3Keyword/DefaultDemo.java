package day3Keyword;

/**
 * The default keyword is used to provide the default implementation of method in an implementation
 */
public class DefaultDemo {
    public static void main(String[] args) {
        Apple apple = new Apple();
        Pear pear = new Pear();
        apple.printDetail();
        pear.printDetail();
    }
}

interface Fruit {
    default void printDetail() {
        System.out.println("This is an unknown fruit");
    }
}

class Apple implements Fruit {
    @Override
    public void printDetail() {
        System.out.println("This is an apple");
    }
}

class Pear implements Fruit {

}
