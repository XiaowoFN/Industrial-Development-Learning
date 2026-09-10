package 运动员和教练;

public class LanYImpl extends YunD {
    public LanYImpl(String name, int age) {
        super(name, age);
    }

    public LanYImpl() {
    }

    @Override
    public void eat() {
        System.out.println("篮球运动员吃篮球");
    }

    @Override
    public void learn() {
        System.out.println("篮球运动员学篮球");
    }
}
