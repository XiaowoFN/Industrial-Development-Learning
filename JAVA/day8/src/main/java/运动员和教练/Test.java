package 运动员和教练;

public class Test {
    public static void main(String[] args) {
        PingYImpl p1 = new PingYImpl("清楚",22);
        PingJImpl p2 = new PingJImpl("栋梁",50);
        LanYImpl l1 = new LanYImpl("小明",285);
        LanJImpl l2 = new LanJImpl("丰田",66);
        System.out.println(p1.getName()+","+p1.getAge());
        p1.eat();
        p1.learn();
        p1.English();
        System.out.println(p2.getName()+","+p2.getAge());
        p2.eat();
        p2.teach();
        p2.English();
        System.out.println(l1.getName()+","+l1.getAge());
        l1.eat();
        l1.learn();
        System.out.println(l2.getName()+","+l2.getAge());
        l2.eat();
        l2.teach();
        Person c1=new PingYImpl("东哥",18);
        YunD c2=(YunD)c1;
        c2.learn();

    }
}
