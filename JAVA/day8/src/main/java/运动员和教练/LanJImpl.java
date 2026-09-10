package 运动员和教练;

public class LanJImpl extends JiaoL{
    public LanJImpl(String name, int age) {
        super(name, age);
    }

    public LanJImpl() {
    }

    @Override
    public void eat() {
        System.out.println("篮球教练吃篮球运动员");
    }

    @Override
    public void teach() {
        System.out.println("篮球教练教篮球");
    }

}
