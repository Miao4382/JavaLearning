package zoomclass.day3;

public class OOPJava {

    public static void main(String[] args) {
        Cat cat = new Cat("Jibo");
        cat.shout();
        System.out.println(cat.isAlive());
    }
}

/**
 * This abstract class implements the Creature interface
 */
abstract class Animal implements Creature{
    String name;

    abstract void shout();

    public Animal(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

/**
 * This cat extends Animal abstract class, since Animal implements Creature, Cat needs to implement both isAlive()
 * method and shout() method
 */
class Cat extends Animal {

    private boolean alive;

    @Override
    void shout() {
        System.out.println("Meow");
    }

    public Cat(String name) {
        super(name);
        alive = true;
    }

    public Cat(String name, boolean alive) {
        super(name);
        this.alive = alive;
    }

    @Override
    public boolean isAlive() {
        return alive;
    }
}

interface Creature {
    boolean isAlive();
}
