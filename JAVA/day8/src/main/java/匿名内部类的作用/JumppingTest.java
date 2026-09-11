package 匿名内部类的作用;

public class JumppingTest {
    public static void main(String[] args) {
        JumppingOperator jp=new JumppingOperator();
        jp.method(new Jumpping() {
            @Override
            public void jump() {
                System.out.println("依依可以跳高了");
            }
        });
        jp.method(new Jumpping() {
            @Override
            public void jump() {
                System.out.println("狗可以吃屎了");
            }
        });

    }
}
