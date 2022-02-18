package zoomclass.day2;

import zoomclass.day3.AccessDemo;

public class AccessDemoChind extends AccessDemo {

    public void printNum() {
        // in different package, subclass, we can only access protected and public field
        System.out.println(super.num2);
        System.out.println(super.num3);
    }
}
