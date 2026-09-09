package 抽象类实战;

public class AnimalTest {
    public static void main(String[] args) {
        Animal c=new Cat(1,"依依");
        //c.eat();

        Animal d=new Dog(48,"冰");
        //d.eat();

        System.out.println(c.getAge()+c.getName());
        c.eat();
        System.out.println(d.getAge()+d.getName());
        d.eat();
    }
}
