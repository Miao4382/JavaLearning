package day3;

public class AccessModifierDemo {

    AccessDemoChild defaultChild = new AccessDemoChild();
    public AccessDemoChild publicChild = new AccessDemoChild();

    public static void main(String[] args) {
        AccessDemo demo = new AccessDemo(1, 2, 3, 4);
    }

}

class AccessDemoChild extends AccessDemo {
}

class AccessDemo {
    private int num1;
    protected int num2;
    public int num3;
    int num4;   // default access modifier

    public AccessDemo() {
        this.num1 = 1;
        this.num2 = 2;
        this.num3 = 3;
        this.num4 = 4;
    }

    public AccessDemo(int num1, int num2, int num3, int num4) {
        this.num1 = num1;
        this.num2 = num2;
        this.num3 = num3;
        this.num4 = num4;
    }

    public int getNum1() {
        return num1;
    }

    public void setNum1(int num1) {
        this.num1 = num1;
    }

    public int getNum2() {
        return num2;
    }

    public void setNum2(int num2) {
        this.num2 = num2;
    }

    public int getNum3() {
        return num3;
    }

    public void setNum3(int num3) {
        this.num3 = num3;
    }

    public int getNum4() {
        return num4;
    }

    public void setNum4(int num4) {
        this.num4 = num4;
    }
}