package 接口的成员特点;

public interface Inter {
    public int num = 10;
    public final int num2 = 20;
    //默认被 final static修饰，可以直接通过接口名来访问“成员变量”
    //public static final int num3 = 30;//看这里前面三个字母都是灰色的，说明可以不写
    int num3 = 30; //这个跟上面一句是等价的
    //public Inter(){}接口里也没有构造方法
//    public void show(){}
    public abstract void method();
    void show();

}
