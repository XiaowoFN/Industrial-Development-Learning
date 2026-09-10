package 接口的成员特点;

//public class InterImpl implements Inter {
    public class InterImpl extends Object implements Inter {
    public InterImpl() {
        super();//因此这个super就用的是Object类里面的构造方法
    }

    @Override
    public void method() {
        System.out.println("method");
    }

    @Override
    public void show() {
        System.out.println("show");
    }
}
