# Java 学习记录：day8

## 记录约定

- 每次说“记录”时，先检查 `day8/src/main/java` 下最近新增或修改的代码。
- 记录代码功能、关键语法、运行结果、问题和当前学习进度。
- 已记录内容不重复堆砌；有新变化时更新或追加章节。
- day8 相关代码和问题优先记录到本文件中。

## 2026-09-09

### 1. day8 当前项目结构

`day8` 是一个 Maven 项目，当前核心结构为：

```text
JAVA/day8
├── pom.xml
├── src
│   ├── main
│   │   ├── java
│   │   └── resources
│   └── test
│       └── java
└── target
```

其中：

- `pom.xml` 是 Maven 配置文件，当前 `groupId` 和 `artifactId` 都是 `day8`。
- `src/main/java` 是正式 Java 源代码目录。
- `src/main/resources` 用于放配置文件、SQL、图片等资源。
- `src/test/java` 用于放测试代码。
- `target` 是编译后自动生成的目录，不需要手动创建。

当前学习阶段主要在 `src/main/java` 中写代码。

### 2. day8 当前代码

```text
接口/main.java                  接口概念说明
接口的特点/Jumpping.java        定义接口 Jumpping
接口的特点/Cat.java             Cat 实现 Jumpping 接口并重写 jump()
接口的特点/Dog.java             抽象类 Dog 实现 Jumpping 接口但暂不实现 jump()
接口的特点/JumppingTest.java    使用接口多态测试 Cat 的 jump()
```

当前代码已经使用 `javac` 编译并运行通过。

### 3. 接口的基本概念

`接口/main.java` 中记录了接口的概念：接口是一种公共规范，只要符合规范，大家都可以使用。

Java 中接口更多体现为对行为的抽象。例如会跳高这个行为可以抽象成接口：

```java
public interface Jumpping {
    public abstract void jump();
}
```

接口使用关键字 `interface` 定义。类实现接口时使用 `implements`。

### 4. 接口的定义和实现

`Jumpping.java` 定义接口：

```java
public interface Jumpping {
    public abstract void jump();
}
```

`Cat.java` 实现接口：

```java
public class Cat implements Jumpping {
    @Override
    public void jump() {
        System.out.println("依依可以蹦起来");
    }
}
```

因为 `Cat` 是普通具体类，所以实现 `Jumpping` 接口后，必须重写并实现接口中的抽象方法 `jump()`。

### 5. 抽象类实现接口的特点

`Dog.java` 当前写法：

```java
public abstract class Dog implements Jumpping {
}
```

`Dog` 是抽象类，所以它实现接口后，可以暂时不重写 `jump()`。后续如果有具体子类继承 `Dog`，那个具体子类需要实现 `jump()`，否则也必须声明为抽象类。

这体现了接口和抽象类结合使用时的特点：抽象类可以承接接口规范，但不一定马上完成所有抽象方法。

### 6. 接口多态

`JumppingTest.java` 中：

```java
Jumpping j = new Cat();
j.jump();
```

左边是接口类型 `Jumpping`，右边是实现类对象 `new Cat()`。这就是接口多态。

接口不能直接实例化：

```java
// Jumpping j = new Jumpping();
```

原因是接口中的方法没有具体实现。接口需要通过实现类对象来使用。

### 7. 本次运行结果

执行 `JumppingTest` 时，逻辑输出为：

```text
依依可以蹦起来
```

本次在终端运行时中文出现乱码，是控制台编码和程序输出编码不一致导致的显示问题，不影响 Java 代码逻辑。

### 8. day8 学习进度

```text
[已完成] 创建 day8 Maven 项目
[已完成] 理解接口是一种公共规范
[已完成] 使用 interface 定义接口
[已完成] 使用 implements 实现接口
[已完成] 普通类实现接口必须重写抽象方法
[已完成] 抽象类实现接口可以暂时不实现抽象方法
[已完成] 使用接口引用指向实现类对象
[进行中] 接口多态
[待学习] 接口成员特点、接口默认方法、接口静态方法、类和接口的多实现关系
```

## 2026-09-10

### 9. 接口的成员特点：成员变量

本次新增“接口的成员特点”包，包含 `Inter`、`InterImpl` 和 `InterfaceTest`。

`Inter` 中定义了三个变量：

```java
public int num = 10;
public final int num2 = 20;
int num3 = 30;
```

接口中的成员变量默认具有以下特点：

```text
public static final
```

因此上面的写法等价于：

```java
public static final int num = 10;
public static final int num2 = 20;
```

也就是说，接口中的变量本质上是公开的静态常量：

- 必须在定义时初始化。
- 不能被修改。
- 可以通过接口名访问，例如 `Inter.num`。
- 变量名通常使用全大写和下划线，例如 `MAX_SIZE`。

虽然本次代码使用了下面的方式读取：

```java
Inter i = new InterImpl();
System.out.println(i.num);
System.out.println(i.num2);
```

但这些变量属于接口本身，不属于 `InterImpl` 创建的对象。实际开发中更推荐使用 `Inter.num` 这种接口名访问方式，以体现它是常量。

### 10. 接口的成员方法与实现类

`InterImpl` 使用 `implements` 实现 `Inter`：

```java
public class InterImpl extends Object implements Inter {
    @Override
    public void method() {
        System.out.println("method");
    }

    @Override
    public void show() {
        System.out.println("show");
    }
}
```

接口中的成员方法默认具有以下修饰符：

```text
public abstract
```

所以这两个方法是等价的：

```java
public abstract void method();
void show();
```

接口中的抽象方法没有方法体，实现类必须使用 `public` 重写全部抽象方法，否则实现类也必须声明为抽象类。

接口不能定义构造方法，也不能直接创建接口对象。`InterImpl` 中显式写出的 `extends Object` 是多余的，因为所有没有明确父类的普通类默认继承 `Object`。构造方法中的 `super()` 调用的是 `Object` 的无参构造方法，通常也可以省略，Java 会自动补充。

测试类中：

```java
Inter i = new InterImpl();
System.out.println(Inter.num);
System.out.println(Inter.num3);
i.method();
i.show();
```

左侧是接口类型，右侧是实现类对象，再次体现了接口多态。接口引用可以访问接口中声明的常量和方法，但不能访问实现类中额外定义、且接口中没有声明的成员。

### 11. 本次编译与运行结果

day8 当前全部 Java 源码使用 JDK 8 的 `javac` 编译通过，并运行了两个测试类：

```text
接口的特点.JumppingTest
依依可以蹦起来

接口的成员特点.InterfaceTest
10
20
```

`JAVA/day8/pom.xml` 配置的 Maven 编译版本是 Java 11，但当前终端没有安装或配置 `mvn` 命令，因此本次没有执行 Maven 构建，只完成了直接编译验证。

### 12. day8 学习进度更新

```text
[已完成] 接口成员变量默认是 public static final
[已完成] 理解接口常量必须初始化且不能修改
[已完成] 理解可以使用接口名读取接口常量
[已完成] 接口成员方法默认是 public abstract
[已完成] 接口没有构造方法
[已完成] 实现类重写接口中的抽象方法
[已完成] 使用接口引用指向实现类对象
[待学习] 接口默认方法和静态方法
[已完成] 一个类实现多个接口
```

### 13. 类和接口的关系

“类和接口的关系”包练习了接口之间的继承，以及一个类实现多个接口：

```java
public interface Inter3 extends Inter1, Inter2 {
}
```

接口和接口之间使用 `extends`，可以单继承，也可以多继承。

```java
public class InterImpl extends Object implements Inter1, Inter2, Inter3 {
}
```

类和接口之间使用 `implements`。一个类可以实现一个或多个接口；一个类在继承一个父类的同时，还可以实现多个接口。

类和类之间使用 `extends`，只能直接继承一个父类，但可以形成多层继承关系。当前示例中 `InterImpl` 同时实现了 `Inter1`、`Inter2` 和继承它们的 `Inter3`，这种写法可以编译，但实际开发中通常直接实现 `Inter3` 即可，避免重复声明。

### 14. 抽象类和接口的区别

抽象类和接口都可以用于抽象，但关注点不同：

```text
抽象类：对一个事物进行抽象，可以描述属性和行为
接口：对一种行为或能力进行抽象
```

抽象类可以包含：

- 普通成员变量和接口常量。
- 构造方法。
- 抽象方法和有具体实现的普通方法。

接口传统上主要包含：

- `public static final` 常量。
- `public abstract` 抽象方法。

Java 8 之后接口还可以定义 `default` 默认方法和 `static` 静态方法，后续继续学习。

继承和实现关系的主要限制是：

```text
类和类：只能单继承
类和接口：可以实现多个接口
接口和接口：可以多继承
```

设计时，可以把所有门都具备的“开门、关门”放到抽象类中，把不是所有门都具备的“报警”能力单独定义成接口，需要报警的门再实现该接口。

### 15. `接口猫和狗` 综合练习

当前目录包含：

```text
Animal.java   抽象父类，封装 name、age，并声明抽象方法 eat()
Jump.java     接口，声明 jump()
Cat.java      继承 Animal 并实现 Jump
Dog.java      继承 Animal 并实现 Jump
AnimalTest.java  测试抽象类、接口和多态
```

`Cat` 和 `Dog` 的关系是：

```java
public class Cat extends Animal implements Jump
public class Dog extends Animal implements Jump
```

它们既继承了 `Animal` 的属性和抽象方法，又具备 `Jump` 接口规定的跳跃行为。

`Animal` 的有参构造方法现在已经正确初始化成员变量：

```java
public Animal(String name, int age) {
    this.name = name;
    this.age = age;
}
```

因此下面的代码可以正确读取传入的姓名和年龄：

```java
Cat c = new Cat("依依", 1);
System.out.println(c.getName() + "," + c.getAge());
```

### 16. 为什么 `Animal` 引用不能直接调用 `jump()`

下面的代码中：

```java
Animal c = new Cat("依依", 1);
c.eat();
c.jump();       // 编译错误
```

`c` 的编译类型是 `Animal`，编译器只按照 `Animal` 中声明的成员检查调用是否合法。`Animal` 中声明了 `eat()`，所以 `c.eat()` 可以调用；`Animal` 中没有声明 `jump()`，所以 `c.jump()` 不能通过编译。

虽然 `c` 实际指向的是 `Cat` 对象，但实际对象类型主要影响重写方法的运行结果，不能让编译器在编译阶段自动增加引用类型中不存在的方法。

可以使用具体子类引用：

```java
Cat c = new Cat("依依", 1);
c.jump();
```

也可以使用接口引用：

```java
Jump j = new Cat("依依", 1);
j.jump();
```

如果已经使用 `Animal` 引用，可以在确认真实类型后向下转型：

```java
Animal c = new Cat("依依", 1);
if (c instanceof Cat) {
    ((Cat) c).jump();
}
```

直接强制转型也可以调用：

```java
((Cat) c).jump();
```

但如果 `c` 实际指向的是 `Dog`，却强转成 `Cat`，运行时会抛出 `ClassCastException`，所以实际使用时应先通过 `instanceof` 判断。

需要纠正一个容易产生误解的说法：抽象类引用并不是“只能调用抽象方法”，而是可以调用抽象类中声明的所有可访问方法，包括普通方法和抽象方法。当前 `Animal` 没有声明 `jump()`，因此 `Animal` 引用不能直接调用它。

### 17. 本次编译与运行结果

使用 JDK 8 的 `javac` 编译 `day8/src/main/java` 下全部 Java 源码通过，并运行了三个测试类：

```text
接口的成员特点.InterfaceTest
10
20
10
30
method
show

接口猫和狗.AnimalTest
依依爱吃小鱼干
依依,1
依依能跳的非常高
小狗爱吃屎
彬彬,48
小狗只能摔个狗吃屎

接口的特点.JumppingTest
依依可以蹦起来
```

### 18. day8 当前学习进度

```text
[已完成] 接口的基本概念和定义
[已完成] 普通类、抽象类实现接口
[已完成] 接口多态
[已完成] 接口成员变量默认是 public static final
[已完成] 接口成员方法默认是 public abstract
[已完成] 接口没有构造方法
[已完成] 类实现一个或多个接口
[已完成] 接口继承一个或多个接口
[已完成] 抽象类与接口的基本区别
[已完成] 抽象类引用和接口引用的调用边界
[已完成] 使用向下转型调用实现类特有方法
[已完成] 一个类实现多个接口
[待学习] JDK 8 接口默认方法和静态方法
[待学习] JDK 9 接口私有方法
[待巩固] instanceof 与向下转型的安全使用
```

### 19. `运动员和教练` 实战项目需求分析

本次完成了一个综合使用抽象类、继承、接口和多态的实战案例。

需求是：有乒乓球运动员、篮球运动员、乒乓球教练和篮球教练；所有和乒乓球相关的人员都需要学习英语。

#### 19.1 实体类

实体类是可以直接创建对象的具体类：

```text
PingYImpl   乒乓球运动员
PingJImpl   乒乓球教练
LanYImpl    篮球运动员
LanJImpl    篮球教练
```

#### 19.2 抽象类

`Person` 是所有人的抽象父类，封装公共属性和行为：

```java
public abstract class Person {
    private String name;
    private int age;
    public abstract void eat();
}
```

`YunD` 表示运动员，定义运动员共有的学习运动项目行为：

```java
public abstract class YunD extends Person {
    public abstract void learn();
}
```

`JiaoL` 表示教练，定义教练共有的教学行为：

```java
public abstract class JiaoL extends Person {
    public abstract void teach();
}
```

继承关系：

```text
Person
├── YunD
│   ├── PingYImpl
│   └── LanYImpl
└── JiaoL
    ├── PingJImpl
    └── LanJImpl
```

#### 19.3 接口

`LearnE` 表示“学习英语”这项额外能力：

```java
public interface LearnE {
    void English();
}
```

只有乒乓球相关的类实现这个接口：

```java
public class PingYImpl extends YunD implements LearnE
public class PingJImpl extends JiaoL implements LearnE
```

篮球相关类不实现 `LearnE`，所以不需要定义或调用 `English()`。

这个设计说明：接口适合表示某种行为或能力，而不是所有对象都必须具备的公共属性。

### 20. 实战项目中的构造方法和 `super`

`Person` 提供有参构造方法，用来初始化姓名和年龄：

```java
public Person(String name, int age) {
    this.name = name;
    this.age = age;
}
```

`YunD` 和 `JiaoL` 继续提供有参构造方法，并调用父类构造方法：

```java
public YunD(String name, int age) {
    super(name, age);
}
```

具体子类再调用抽象父类的构造方法：

```java
public PingYImpl(String name, int age) {
    super(name, age);
}
```

因此创建对象时可以直接传入数据：

```java
PingYImpl p1 = new PingYImpl("清楚", 22);
```

`super(name, age)` 的作用是把姓名和年龄继续传递给父类进行初始化。

### 21. 当前保留的直观测试写法

当前 `Test.java` 没有使用数组、增强 `for` 循环和 `instanceof`，而是分别创建四种对象，再直接调用各自的方法：

```java
PingYImpl p1 = new PingYImpl("清楚", 22);
PingJImpl p2 = new PingJImpl("栋梁", 50);
LanYImpl l1 = new LanYImpl("小明", 285);
LanJImpl l2 = new LanJImpl("丰田", 66);
```

这种写法更加直观，适合当前阶段巩固：

```text
继承
抽象类
接口
方法重写
构造方法
super
```

之前整理的 `Person[]`、增强 `for`、`instanceof` 和统一遍历版本属于进阶写法，不是当前案例必须使用的内容。它需要在已经熟悉数组遍历、父类引用、向下转型和 `instanceof` 后再学习。

### 22. `AnimalTest` 中接口方法调用问题

代码：

```java
Animal c = new Cat("依依", 1);
c.eat();
c.jump();
```

`c` 的编译类型是 `Animal`。编译器按照左侧的 `Animal` 检查可以调用哪些方法。

`Animal` 中声明了：

```java
public abstract void eat();
```

所以 `c.eat()` 可以调用，并且运行时会执行 `Cat.eat()`。

`jump()` 只在 `Jump` 接口中声明，`Animal` 没有声明这个方法，所以：

```java
c.jump(); // 编译错误
```

可以使用具体子类引用：

```java
Cat c = new Cat("依依", 1);
c.jump();
```

也可以使用接口引用：

```java
Jump j = new Cat("依依", 1);
j.jump();
```

如果已经使用 `Animal` 引用，可以在确认真实类型后向下转型：

```java
Animal c = new Cat("依依", 1);
Cat cat = (Cat) c;
cat.jump();
```

需要注意：抽象类引用不是“只能调用抽象方法”。引用能调用哪些方法，取决于引用类型中声明了哪些可访问方法；抽象类中的普通方法也可以调用。

### 23. 向上转型、向下转型和多态复习

下面三行代码：

```java
Person c1 = new PingYImpl("东哥", 18);
YunD c2 = (YunD) c1;
c2.learn();
```

#### 23.1 第一步：向上转型和多态

```java
Person c1 = new PingYImpl("东哥", 18);
```

实际对象是 `PingYImpl`，但使用父类 `Person` 类型的引用接收：

```text
c1 的编译类型：Person
c1 的实际对象：PingYImpl
```

这属于向上转型，也体现了多态：

```text
PingYImpl -> YunD -> Person
```

#### 23.2 第二步：向下转型

```java
YunD c2 = (YunD) c1;
```

把 `Person` 类型的引用转换成 `YunD` 类型：

```text
Person -> YunD
```

转换不会创建新对象，`c1` 和 `c2` 仍然指向同一个 `PingYImpl` 对象。转换后，`c2` 可以调用 `YunD` 中声明的 `learn()`。

#### 23.3 第三步：多态调用

```java
c2.learn();
```

`c2` 的编译类型是 `YunD`，实际对象仍然是 `PingYImpl`，因此运行时执行的是：

```java
PingYImpl.learn();
```

所以这三行可以总结为：

```text
第 1 行：向上转型，同时形成多态
第 2 行：向下转型
第 3 行：调用重写方法，再次体现方法多态
```

### 24. 教练对象的正确向下转型

错误示例：

```java
Person c1 = new PingJImpl("栋梁", 50);
YunD c2 = (YunD) c1;
```

`PingJImpl` 是教练，继承关系是：

```text
Person
└── JiaoL
    └── PingJImpl
```

它不是运动员 `YunD` 的子类。虽然这段代码可以通过编译，但运行时会抛出：

```text
ClassCastException
```

正确写法是：

```java
Person c1 = new PingJImpl("栋梁", 50);
JiaoL c2 = (JiaoL) c1;
c2.teach();
```

这里的实际对象是 `PingJImpl`，它继承了 `JiaoL`，所以向下转型为 `JiaoL` 是正确的。

判断向下转型是否正确时，不能只看引用变量左边的类型，还要看右边实际创建的对象类型。

### 25. 本次实战项目运行结果

使用 JDK 8 的 `javac` 编译 `day8/src/main/java` 下全部 Java 源码通过，并运行 `运动员和教练.Test` 成功：

```text
清楚,22
乒乓球运动员吃乒乓球
乒乓球运动员学乒乓球
乒乓球运动员还得会说英语
栋梁,50
乒乓球教练吃乒乓球运动员
乒乓球教练教乒乓球
乒乓球教练也得会说英语
小明,285
篮球运动员吃篮球
篮球运动员学篮球
丰田,66
篮球教练吃篮球运动员
篮球教练教篮球
乒乓球运动员学乒乓球
```

最后三行代码：

```java
Person c1 = new PingYImpl("东哥", 18);
YunD c2 = (YunD) c1;
c2.learn();
```

输出最后一行“乒乓球运动员学乒乓球”，验证了向上转型、向下转型和多态方法调用。

### 26. day8 当前学习进度更新

```text
[已完成] 抽象类、继承和方法重写
[已完成] 接口定义、接口实现和接口多态
[已完成] 接口成员变量和接口成员方法特点
[已完成] 类实现多个接口、接口多继承
[已完成] 使用构造方法和 super 初始化父类数据
[已完成] 分析实体类、抽象类和接口
[已完成] 向上转型
[已完成] 向下转型
[已完成] 理解编译类型和实际对象类型
[已完成] 理解方法多态调用
[待巩固] instanceof 与向下转型的安全判断
[待学习] JDK 8 接口默认方法和静态方法
[待学习] JDK 9 接口私有方法
[待学习] 使用父类数组和增强 for 统一处理多个对象
```
