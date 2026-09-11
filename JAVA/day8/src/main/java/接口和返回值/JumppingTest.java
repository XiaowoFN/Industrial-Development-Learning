package 接口和返回值;

public class JumppingTest {
    public static void main(String[] args) {
        //创建操作类对象，并调用方法
        JumppingOperator jp = new JumppingOperator();
        Jumpping j =new Cat();
        jp.useJumpping(j);
        Jumpping j1 = jp.getJumpping();
        j1.jump();
    }
}
