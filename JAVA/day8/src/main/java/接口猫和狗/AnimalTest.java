package 接口猫和狗;
/*
* 接口只能调接口里的方法
* 抽象类只能调抽象类
* 但是可以直接用子类实体化
*
*
* */
public class AnimalTest {
    public static void main(String[] args) {
        /*Animal c = new Cat("依依",1);
        Animal d = new Dog("彬彬",48);
        System.out.println(c.getName()+","+c.getAge());
        c.eat();
        ((Cat)c).jump();
        System.out.println(d.getName()+","+d.getAge());
        d.eat();
        //d.jump();
        ((Dog)d).jump();*/
        Cat c=new Cat("依依",1);
        Dog d=new Dog("彬彬",48);
        c.eat();
        System.out.println(c.getName()+","+c.getAge());
        c.jump();
        d.eat();
        System.out.println(d.getName()+","+d.getAge());
        d.jump();
    }
}
