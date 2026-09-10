package 运动员和教练;

public class PingJImpl extends JiaoL implements LearnE {
    public PingJImpl(String name, int age) {
        super(name, age);
    }

    public PingJImpl() {
    }

    @Override
    public void eat() {
        System.out.println("乒乓球教练吃乒乓球运动员");
    }

    @Override
    public void teach() {
        System.out.println("乒乓球教练教乒乓球");
    }

    @Override
    public void English() {
        System.out.println("乒乓球教练也得会说英语");
    }
}
