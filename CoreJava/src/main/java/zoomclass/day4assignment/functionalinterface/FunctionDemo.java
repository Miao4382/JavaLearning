package zoomclass.day4assignment.functionalinterface;

import java.util.function.Function;

public class FunctionDemo {

    public static void main(String[] args) {
        // Note_1: call the traditional method
        int num = incrementByOne(1);
        System.out.println("Traditional method call res = " + num);

        // Note_3: use the function entity we defined. We use .apply() to pass in arguments to that function entity
        int num2 = incremenmtByOneFunction.apply(1);
        System.out.println("\nUse the incrementByOneFunction entity to get the result, res = " + num2);

        // Note_5: we use andThen() to concatenate functions to form new functions
        Function<Integer, Integer> addOneThenMultiplyBy10 = incremenmtByOneFunction.andThen(multiplyBy10Function);
        int res = addOneThenMultiplyBy10.apply(1);
        System.out.println("\nUse the combined new function entity, res = " + res);

        // Note_6: you can continue to chain other functions
        Function<Integer, Integer> subtractBy5 = addOneThenMultiplyBy10.andThen(number -> number - 5);
        System.out.println("After chaining subtractBy5, res = " + subtractBy5.apply(1));    // should be 15


    }

    // Note_2: we can define a function entity by using the Function<T, R> interface
    static Function<Integer, Integer> incremenmtByOneFunction = num -> num + 1;

    // Note_4: we define another function entity. We use this to demo concatenation of functions.
    static Function<Integer, Integer> multiplyBy10Function = num -> num * 10;

    // Note_0: an example method
    public static int incrementByOne(int num) {
        return num + 1;
    }
}
