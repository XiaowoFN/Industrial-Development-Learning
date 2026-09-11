package 接口和返回值;

public class JumppingOperator{

    public void useJumpping(Jumpping j)
    {
        j.jump();
    }
    public Jumpping getJumpping()
    {
        Jumpping j=new Cat();
        return j;
    }
}
