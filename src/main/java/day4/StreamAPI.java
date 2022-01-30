package day4;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class StreamAPI {

    public static void main(String[] args) {
        List<Integer> list = new ArrayList<>(Arrays.asList(5,4,2,1,7));
        List<Integer> res = list.stream().filter(integer -> integer > 2).collect(Collectors.toList());
        System.out.println(res);
    }
}
