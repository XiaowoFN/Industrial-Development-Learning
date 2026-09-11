package 匿名内部类;

public class Outer {
    public void method() {
        /*new Inter(){
            @Override
            public void show() {
                System.out.println("匿名内部类");//如果不加后面的.show()那就是个对象，本质上就是个对象加了才是调用了方法
            }
        }.show();*/
        Inter i = new Inter() {
            @Override
            public void show() {
                System.out.println("匿名内部类");
            }
        };
        i.show();


    }
}
