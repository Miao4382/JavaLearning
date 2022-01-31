package day4assignment.functionalinterface;

import java.util.function.Predicate;

public class PredicateDemo {

    public static void main(String[] args) {
        Person p1 = new Person("Jack");
        Person p2 = new Person("Emily");
        System.out.println(nameStartWithE.test(p1));
        System.out.println(nameStartWithE.test(p2));

        // the predicate can be combined (similar to how we use andThen() when combining functions)
        Person p3 = new Person("Emma");
        System.out.println("\nTest combined predicate");
        System.out.println(nameStartWithE.and(nameLengthIsFive).test(p2));
        System.out.println(nameStartWithE.and(nameLengthIsFive).test(p3));
    }

    static Predicate<Person> nameStartWithE = person -> person.name.charAt(0) == 'E';
    static Predicate<Person> nameLengthIsFive = person -> person.name.length() == 5;

    static class Person {
        private String name;

        public Person(String name) {
            this.name = name;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }
}
