package 抽象类的成员特点;

public abstract class Animal {
    public abstract void eat();

    private int age = 20;
    private final String city = "上海";


    public void show() {

        age = 40;
        System.out.println(age);
        //city="上海";
        System.out.println(city);

    }
}
