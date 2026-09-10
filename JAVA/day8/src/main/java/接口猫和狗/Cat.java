package 接口猫和狗;

public class Cat extends Animal implements Jump {
    public Cat(String name, int age) {
        super(name, age);
    }

    public Cat() {
    }

    @Override
    public void jump() {
        System.out.println("依依能跳的非常高");
    }

    @Override
    public void eat() {
        System.out.println("依依爱吃小鱼干");
    }



}
