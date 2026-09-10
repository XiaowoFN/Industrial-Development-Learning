package 接口猫和狗;

public class Dog extends Animal implements Jump {
    public Dog(String name, int age) {
        super(name, age);
    }

    public Dog() {
    }

    @Override
    public void jump() {
        System.out.println("小狗只能摔个狗吃屎");
    }

    @Override
    public void eat() {
        System.out.println("小狗爱吃屎");
    }
}
