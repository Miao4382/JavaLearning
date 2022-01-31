package day4assignment.streams;

import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class StreamDemo {

    static class Person {
        private final String name;
        private final Gender gender;

        public Person(String name, Gender gender) {
            this.name = name;
            this.gender = gender;
        }

        @Override
        public String toString() {
            return "Person{" +
                    "name='" + name + '\'' +
                    ", gender=" + gender +
                    '}';
        }
    }

    enum Gender {
        MALE, FEMALE
    }

    static class Order {
        static int orderCount = 0;
        int orderId;
        List<Item> items;

        public Order(Item... items) {
            orderId = orderCount++;
            this.items = List.of(items);
        }

        public List<Item> getItems() {
            return items;
        }
    }

    static class Item {
        String itemName;
        int price;
        public Item(String itemName, int price) {
            this.itemName = itemName;
            this.price = price;
        }

        @Override
        public String toString() {
            return String.format("%s - $%d", itemName, price);
        }

        // override equals() method to be able to demo distinct() stream API
        @Override
        public boolean equals(Object obj) {
            if (!(obj instanceof Item)) return false;
            return itemName.equals(((Item) obj).itemName) && price == ((Item) obj).price;
        }

        // objects that are equal to each other must have same hashCode
        @Override
        public int hashCode() {
            return Objects.hash(itemName, price);
        }
    }

    public static void main(String[] args) {
        minMaxSumAverageDemo();
    }

    static void filterDemo() {
        // create a list of people
        List<Person> people = List.of(
                new Person("John", Gender.MALE),
                new Person("Maria", Gender.FEMALE),
                new Person("Aisha", Gender.FEMALE),
                new Person("Alex", Gender.MALE),
                new Person("Alice", Gender.FEMALE)
        );

        // filter example: how many person in people has a name starting with A (3)
        System.out.println("Number of person whose name starts with A: " + (int) people.stream()
                .filter(person -> person.name.charAt(0) == 'A').count());
    }

    static void matchDemo() {
        // create a list of people
        List<Person> people = List.of(
                new Person("John", Gender.MALE),
                new Person("Maria", Gender.FEMALE),
                new Person("Aisha", Gender.FEMALE),
                new Person("Alex", Gender.MALE),
                new Person("Alice", Gender.FEMALE)
        );

        // Example of allMatch().
        // Example 1: check if all elements in people has a name shorter than 10 characters (true)
        // Example 2: check if all elements in people has a name shorter than 5 characters (false)
        System.out.println("allMatch example 1: " + people.stream().allMatch(person -> person.name.length() < 10));
        System.out.println("allMatch example 2: " + people.stream().allMatch(person -> person.name.length() < 5));

        // anyMatch example: if any elements in people has a name starting with character 'A' (true)
        System.out.println("anyMatch example: " + people.stream().anyMatch(person -> person.name.charAt(0) == 'A'));
    }

    static void mapDemo() {
        // create a list of people
        List<Person> people = List.of(
                new Person("John", Gender.MALE),
                new Person("Maria", Gender.FEMALE),
                new Person("Aisha", Gender.FEMALE),
                new Person("Alex", Gender.MALE),
                new Person("Alice", Gender.FEMALE)
        );

        // map(): takes in a Function<T, R>, which is a mapping from T to R
        Set<Gender> genders = people.stream().map(person -> person.gender).collect(Collectors.toSet());
        System.out.println("There are " + genders.size() + " genders.");
        genders.forEach(System.out::println);

        // we can use concatenate map(), here Person is first mapped to person.name, then the stream of name is mapped
        // into the length of the name (using method reference String::length), then finally we use forEach() and method
        // reference System.out::println to print the result to stdout
        // The three lambda expressions are instantiating Function<Person, String>, Function<String, Integer> and
        // Consumer<Integer>, respectively
        people.stream().map(person -> person.name).map(String::length).forEach(System.out::println);
    }

    /**
     * flapMap(Function<T, R>) Returns a stream consisting of the results of replacing each element of this stream with
     * the contents of a mapped stream produced by applying the provided mapping function to each element.
     */
    static void flatMapDemo() {
        // create a list of orders
        List<Order> orders = List.of(
                new Order(
                        new Item("Laptop", 1000),
                        new Item("Soap", 5),
                        new Item("Bike", 125)
                ),
                new Order(
                        new Item("Pencil", 3),
                        new Item("Pen", 5),
                        new Item("Apple", 12)
                ),
                new Order(
                        new Item("Fish tank", 35),
                        new Item("KitchenAid", 300)
                )
        );

        // use flatMap() to get a list of items in all those orders
        List<Item> items = orders.stream().flatMap(order -> order.getItems().stream()).collect(Collectors.toList());
        items.forEach(System.out::println);

        // compare it with map (we can see that the item list is not flattened, still List<Item> for each order
        System.out.println("\nMap:");
        orders.stream().map(Order::getItems).forEach(System.out::println);

    }

    static void distinctDemo() {
        List<Item> items = List.of(
                new Item("Pencil", 5),
                new Item("Pen", 15),
                new Item("Brush", 3),
                new Item("Pencil", 5),
                new Item("Pencil", 5)
        );

        // use distinct stream API to print out distinct items
        items.stream().distinct().forEach(System.out::println);
    }

    static void sortedDemo() {
        List<Item> items = List.of(
                new Item("Pencil", 5),
                new Item("Pen", 15),
                new Item("Brush", 3),
                new Item("Pencil", 5),
                new Item("Pencil", 5)
        );

        // use stream.sorted() API to display the items list in ascending order of price
        items.stream().sorted(Comparator.comparingInt(item -> item.price)).forEach(System.out::println);

    }

    static void peekDemo() {
        List<String> nums = Stream.of("one", "two", "three", "four").peek(System.out::println).collect(Collectors.toList());
        System.out.println("Printing the list:");
        nums.forEach(e -> System.out.println(e + " "));
    }

    static void findFirstDemo() {
        List<String> nums = Stream.of("one", "two", "three", "four").collect(Collectors.toList());
        System.out.println(nums.stream().findFirst().orElse("Stream is empty"));
    }

    static void minMaxSumAverageDemo() {
        List<Integer> nums = Stream.of(6, 4, 3, 2, 5, 1, 7).collect(Collectors.toList());
        var minNum = nums.stream().min(Comparator.comparingInt(num -> num));
        System.out.println("List: " + nums);
        System.out.println("min num = " + minNum.orElse(Integer.MIN_VALUE));
        System.out.println("max num = " + nums.stream().max(Comparator.comparingInt(num -> num)).orElse(Integer.MAX_VALUE));

        System.out.println("Sorted list: ");
        nums.stream().sorted().forEach(num -> System.out.print(num + " "));

        // sum are special case of reduction, here are hand-made sum
        Integer sum = nums.stream().reduce(Integer::sum).orElse(null);
        System.out.println("\nSum: " + sum);

        // or you can use sum() and average() in IntStream, but first you need to convert it to an IntStream
        int sum2 = nums.stream().mapToInt(num -> num).sum();
        double average2 = nums.stream().mapToInt(num -> num).average().orElse(-1);
        System.out.println("sum2 = " + sum2);
        System.out.println("average2 = " + average2);
    }
}
