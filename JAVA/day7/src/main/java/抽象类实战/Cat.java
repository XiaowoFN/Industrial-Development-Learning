package 抽象类实战;

public class Cat extends Animal {
    public Cat() {
    }

    public Cat(int age, String name) {
        super(age, name);
    }

    @Override
    public void eat() {
        System.out.println("小猫爱吃小鱼干");
    }
}
