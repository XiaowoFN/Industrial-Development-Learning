package 接口的成员特点;

public class InterfaceTest {
    public static void main(String[] args) {
        Inter i = new InterImpl();
        System.out.println(i.num);
        System.out.println(i.num2);
        System.out.println(Inter.num);
        System.out.println(Inter.num3);
        i.method();
        i.show();
    }
}
