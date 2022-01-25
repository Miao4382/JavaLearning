package day1;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class EqualsHashCode {

    public static void main(String[] args) {
        EHNode n1 = new EHNode(1);
        EHNode n2 = new EHNode(2);
        EHNode n3 = new EHNode(1);

        Map<EHNode,String> map = new HashMap<>();
        map.put(n1, "One");
        map.put(n2, "Two");

        System.out.println(n1.hashCode());
        System.out.println(map.get(n3));
    }

}

class EHNode {
    int a;

    public EHNode(int a) {
        this.a = a;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this) return true;
        if (!(o instanceof EHNode)) return false;
        return ((EHNode) o).a == this.a;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(a);
    }
}
