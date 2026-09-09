package 抽象类练习;

public class AnimalTest {
    public static void main(String[] args) {
//        Animal a = new Animal();
      //抽象类参照多态的形式创建对象
        Animal a=new Cat();
        a.eat();
        a.sleep();

    }
}
