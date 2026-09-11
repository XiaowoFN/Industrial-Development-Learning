package 成员内部类;

public class InnerTest {
    public static void main(String[] args) {
        //创建内部类对象并调用方法
        /*Outer.Inner i = new Outer().new Inner();
        i.show();*/
        Outer o = new Outer();
        o.method();

    }
}
