package zoomclass.day3Keyword;

import java.util.Arrays;

/**
 * Keyword enum is used to define enumerated class.
 */
public class EnumDemo {

    public static void main(String[] args) {
        Weekday day = Weekday.FRIDAY;
        System.out.println(day);

        // test some methods inherited from Enum<> class
        Size[] sizes = Size.values();
        System.out.println(Arrays.toString(sizes));
        System.out.println("Size.LARGE's abbreviation is: " + Size.LARGE.getAbbreviation());

        Weekday day2 = Enum.valueOf(Weekday.class, "TUESDAY");
        System.out.println("zoomclass.day2 = " + day2);

        System.out.println("Check ordinal, Size.MEDIUM.ordinal() == " + Size.MEDIUM.ordinal());
    }

}


/**
 * The keyword enum means this class is an enumerated class. It has 7 objects (no new object is allowed). Class
 * Weekday is subclass of Enum<Weekday>. You can not construct new objects to the enum class.
 */
enum Weekday {
    // these are actually default constructor calls
    MONDAY, TUESDAY, WEDNESDAY, THURSDAY, FRIDAY, SATURDAY, SUNDAY
}

/**
 * You can also add private constructor, method and field to an enumerated type.
 */
enum Size {
    SMALL("S"), MEDIUM("M"), LARGE("L");

    private final String abbreviation;

    private Size(String abbreviation) {
        this.abbreviation = abbreviation;
    }

    public String getAbbreviation() {
        return abbreviation;
    }
}