package 抽象类练习;
/*
* 抽象类
* 如果一个类里有抽象方法，那一定是抽象类
* 但是抽象类里不一定要有抽象方法
*
* */
public abstract class Animal {
    //抽象方法
    public abstract void eat();
    public void sleep(){
        System.out.println("唏哩呼噜睡大觉");//抽象类里可以有非抽象方法
    }

}
