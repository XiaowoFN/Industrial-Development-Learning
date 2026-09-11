package 形参和返回值;

public class CatTest {
    public static void main(String[] args) {
        //创建操作类对象并调用方法
        CatOperator op = new CatOperator();
        //Cat c = new Cat();
        op.useCat(new Cat());
        Cat c = op.getCat();//new Cat()相当于这句话就等于Cat c = new Cat();自然下面可以调用eat方法
        c.eat();

    }
}
