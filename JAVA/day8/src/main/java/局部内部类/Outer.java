package 局部内部类;

public class Outer {
    private int num = 10;

    public void method() {
        int num2=20;
        class Inner {
            int num3=30;
            public void show() {
                System.out.println(num);//内部类可以访问外部类的成员变量包括私有
                System.out.println(num2);//内部类可以访问外部类的成员变量包括私有
                System.out.println(num3);//内部类可以访问外部类的成员变量包括私有
            }
        }
        Inner i=new Inner();
        i.show();

    }
}
