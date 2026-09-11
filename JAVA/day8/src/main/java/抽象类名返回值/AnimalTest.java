package 抽象类名返回值;

public class AnimalTest {
    public static void main(String[] args) {
        //创建操作类对象，并调用方法
        AnimalOperator op = new AnimalOperator();
       // Animal a=new Animal();抽象类不能直接实例化
        Animal a=new Cat();
        op.useAnimal(a);
        Animal b=op.getAnimal();//new cat()
        b.eat();

    }
}
