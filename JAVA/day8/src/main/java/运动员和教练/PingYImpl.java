package 运动员和教练;

public class PingYImpl extends YunD implements LearnE {
    public PingYImpl(String name, int age) {
        super(name, age);
    }

    public PingYImpl() {
    }

    @Override
    public void eat() {
        System.out.println("乒乓球运动员吃乒乓球");
    }

    @Override
    public void learn() {
        System.out.println("乒乓球运动员学乒乓球");
    }

    @Override
    public void English() {
        System.out.println("乒乓球运动员还得会说英语");
    }
}
