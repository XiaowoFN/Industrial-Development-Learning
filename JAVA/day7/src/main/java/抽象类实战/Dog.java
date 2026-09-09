package 抽象类实战;

public class Dog extends Animal{
    public Dog() {
    }

    public Dog(int age, String name) {
        super(age, name);
    }
    @Override
    public void eat() {
        System.out.println("小狗爱吃屎");
    }
}
