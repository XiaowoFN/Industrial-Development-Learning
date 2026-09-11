package 内部类;

public class Outer {
    private int num=10;
    public class Inner {
        public void show()
        {
            System.out.println(num);
        }

    }
    public void method()
    {
       Inner i =new Inner();
       i.show();
    }
}
