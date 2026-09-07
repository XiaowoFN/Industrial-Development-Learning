# Java 学习记录：day7

## 记录约定

- 每次说“记录”时，先检查 `day7/src/main/java` 下最近新增或修改的代码。
- 记录代码功能、关键语法、运行结果、问题和当前学习进度。
- 已记录内容不重复堆砌；有新变化时更新或追加章节。

## 2026-09-04

### 1. day7 当前代码

```text
多态/hw.java       基础 Hello World
多态/animal.java   父类 Animal，定义年龄和 eat 方法
多态/cat.java      子类 Cat，重写 eat 并增加 playGame
多态/duotai.java   父类引用指向子类对象的多态练习
```

当前代码已经通过编译。

### 2. 多态的基本形式

`duotai.java` 中：

```java
animal c = new cat();
```

可以拆成两部分理解：

```text
animal c  -> 引用变量的编译类型是父类 animal
new cat() -> 实际创建的运行对象是子类 cat
```

这种“父类引用指向子类对象”是多态的常见形式。猫既是具体的 `cat`，也是一种 `animal`。

多态通常需要：

- 存在继承或接口实现关系。
- 子类重写父类方法。
- 父类或接口引用指向子类实现对象。

### 3. 方法重写与 `@Override`

父类定义：

```java
public void eat() {
    System.out.println("动物吃东西");
}
```

子类重新实现同一个方法：

```java
@Override
public void eat() {
    System.out.println("小猫吃小鱼");
}
```

`@Override` 表示当前方法打算重写父类方法。它能让编译器帮助检查方法名、参数列表等是否写对，建议重写方法时都写上。

如果执行：

```java
animal c = new cat();
c.eat();
```

实际运行的是 `cat` 重写后的 `eat()`，输出“小猫吃小鱼”。这体现了成员方法调用的多态：编译时看父类是否有该方法，运行时执行实际对象的重写实现。

### 4. 多态下成员的访问特点

`animal` 和 `cat` 都定义了 `age`：

```java
animal c = new cat();
System.out.println(c.age); // 40
```

成员变量不体现方法那样的动态多态，访问时主要看引用变量的声明类型，因此这里访问 `animal.age`。

`cat` 独有的 `weight` 和 `playGame()` 不能直接通过 `animal` 类型的引用访问：

```java
c.weight;     // 编译错误
c.playGame(); // 编译错误
```

原因是编译阶段只知道 `c` 的类型是 `animal`，而 `animal` 中没有这些成员。后续可以学习向下转型后访问子类特有成员。

### 5. day7 学习进度

```text
[已完成] 建立 animal 和 cat 的继承关系
[已完成] 使用 @Override 重写父类方法
[已完成] 写出父类引用指向子类对象
[进行中] 多态中成员变量和成员方法的访问特点
[待学习] 向上转型、向下转型、instanceof、抽象类和接口
```

### 6. 在 IDEA 中创建 `.sql` 文件

day1 中的文件：

```text
JAVA/day1/src/main/java/hl/postgresql-practice.sql
```

是一个普通的 `.sql` 文件，目前内容为空。创建方式：

1. 在 IDEA 左侧 Project 面板中选择目标目录。
2. 右键选择 `New -> File`。
3. 输入完整文件名，例如 `practice.sql`。
4. 按 Enter，IDEA 会按 SQL 文件识别并提供语法高亮。

如果右键菜单中直接有 `New -> SQL File`，也可以使用；是否显示该选项取决于 IDEA 版本和数据库相关功能是否启用。使用普通 `New -> File` 始终可以创建。

Java 项目中的 SQL 脚本通常不建议放到 `src/main/java`，因为这里主要存放 Java 源代码。更合适的位置是：

```text
src/main/resources/sql/
```

例如：

```text
src/main/resources/sql/create_table.sql
src/main/resources/sql/init_data.sql
```

如果只是临时练习，也可以放在项目根目录下的独立 `sql` 文件夹。`.sql` 文件只是文本文件，是否能够直接执行，还取决于 IDEA 是否已经连接数据库；没有数据库连接也可以正常编辑和保存 SQL。

### 7. 多态练习扩展：`dog`、`pig` 和 `use`

本次新增 `dog.java`、`pig.java` 和 `use.java`。`cat`、`dog`、`pig` 都继承 `animal`，并分别重写 `eat()`：

```java
public void useAnimal(animal a) {
    a.eat();
}
```

同一个方法参数 `animal a` 可以接收不同子类对象：

```java
u.useAnimal(new cat());
u.useAnimal(new dog());
u.useAnimal(new pig());
```

执行 `a.eat()` 时，会根据实际传入的对象调用对应的重写方法。这样不需要为每种动物分别编写 `useAnimal(cat)`、`useAnimal(dog)`、`useAnimal(pig)`，降低了重复代码，并方便以后增加新的动物类型。

这体现多态的扩展性：调用者依赖父类 `animal`，具体行为由不同子类实现。

### 8. 多态成员访问口诀

`duotai.java` 进一步验证：

```java
animal a = new cat();
System.out.println(a.age); // 40
a.eat();                  // 小猫吃小鱼
```

```text
成员变量：编译看左边，运行也看左边
成员方法：编译看左边，运行看右边的实际对象
```

原因是方法可以被子类重写，普通成员变量不存在相同的动态调用机制。父类引用也不能直接调用子类独有的 `playGame()`、`lookDoor()`，因为编译器只按照左侧类型检查有哪些方法。

### 9. 多态的向上转型和向下转型

“转型”包新增了 `Animal`、`Cat`、`Dog` 和 `Test`。

向上转型：

```java
Animal a = new Cat();
```

子类对象作为父类类型使用。这个转换通常自动完成，适合统一接收不同子类对象；但父类引用不能直接调用子类特有方法。

向下转型：

```java
Cat c = (Cat) a;
c.playGame();
```

把父类引用转换回具体子类类型，可以调用子类独有的方法。这里并没有创建新的 Cat 对象，`a` 和 `c` 仍然指向同一个对象，只是引用变量的类型不同。

代码中还练习了运行时对象的变化：

```java
a = new Dog();
a.eat();
Dog d = (Dog) a;
d.playGame();
```

此时 `a` 实际指向 Dog，所以转成 Dog 是安全的。

### 10. 向下转型的风险

向下转型必须与对象的真实类型一致。如果 `a` 实际指向 Cat，却强制转换成 Dog：

```java
Animal a = new Cat();
Dog d = (Dog) a;
```

代码可能通过编译，但运行时会抛出 `ClassCastException`。后续应使用 `instanceof` 判断：

```java
if (a instanceof Cat) {
    Cat c = (Cat) a;
    c.playGame();
}
```

Java 16 及以上还可以使用模式匹配，但当前课程/公司兼容基线可能是 Java 8 或 11，所以应先掌握传统写法。

### 11. 本次代码验证与学习进度

当前 day7 的全部 Java 文件已经通过编译，并验证了 `多态.duotai` 和 `转型.Test` 的运行流程。

```text
[已完成] 多个子类重写同一个父类方法
[已完成] 使用父类参数统一接收不同子类对象
[已完成] 理解成员变量“编译和运行都看左边”
[已完成] 理解成员方法“编译看左边，运行看右边”
[已完成] 向上转型
[已完成] 向下转型并调用子类特有方法
[待巩固] ClassCastException 和 instanceof 安全判断
[待学习] 抽象类、接口及它们与多态的结合
```

### 12. `多态练习`：Animal、Cat、Dog

本次新增“多态练习”包，包含 `Animal`、`Cat`、`Dog` 和 `Test`。

父类 `Animal` 封装了所有动物共有的数据和行为：

```java
private String name;
private int age;

public void eat() {
    System.out.println("动物要吃饭");
}
```

`Cat` 和 `Dog` 继承 `Animal`，分别重写 `eat()`。这体现了抽象共同属性、继承共同代码、通过重写表达不同行为。

### 13. 子类构造方法使用 `super`

`Cat` 和 `Dog` 都定义了无参、有参构造方法：

```java
public Cat(String name, int age) {
    super(name, age);
}
```

`super(name, age)` 调用父类 `Animal(String name, int age)` 构造方法，初始化父类中的私有成员变量。因为 `name` 和 `age` 是 `private`，子类不能直接赋值，但可以通过父类构造方法完成初始化。

`super(...)` 必须放在子类构造方法的第一行。子类无参构造方法没有显式写 `super()` 时，Java 会自动调用父类无参构造方法。

### 14. 两种对象初始化方式

第一种：无参构造方法加 Setter：

```java
Animal c = new Cat();
c.setName("依依");
c.setAge(1);
```

第二种：有参构造方法直接初始化：

```java
Animal c = new Cat("小安", 2);
```

两种方式都能创建完整对象。有参构造更简洁，Setter 适合创建对象后再修改属性。

虽然变量类型是 `Animal`，实际对象是 `Cat` 或 `Dog`，调用 `c.eat()`、`d.eat()` 时执行对应子类的重写方法，进一步验证了成员方法的多态。

本次运行结果：

```text
依依1
小猫爱吃小鱼干
小安2
小猫爱吃小鱼干
wangwang3
小狗爱吃是
wangwang3
小狗爱吃是
```

`Dog.eat()` 中的“小狗爱吃是”可能是练习时的文字笔误，不影响继承和多态逻辑。

### 15. 抽象类入门进度

新建了“抽象类”包，并记录了概念：没有方法体的方法可以声明为抽象方法；类中如果包含抽象方法，该类必须声明为抽象类。

标准形式：

```java
public abstract class Animal {
    public abstract void eat();
}
```

需要注意：当前“抽象类/Animal.java”仍然是普通空类，还没有使用 `abstract`；目前属于概念预习，尚未完成抽象类的实际代码练习。

抽象方法只有方法声明，没有方法体；具体子类需要重写并实现它。抽象类不能直接 `new`，但可以作为父类引用类型使用：

```java
Animal animal = new Cat();
```

### 16. day7 学习进度更新

```text
[已完成] 多态中父类引用指向子类对象
[已完成] 父类抽取共同属性和行为
[已完成] 子类使用 super 调用父类有参构造方法
[已完成] 无参构造 + Setter 和有参构造两种初始化方式
[已完成] Cat、Dog 重写 eat 并通过多态调用
[进行中] 抽象类和抽象方法
[待学习] 抽象类子类实现、接口、instanceof 安全转型
```

### 17. 当前 day7 代码基线

从现在开始，`day7` 里新写的代码都继续往这里追加记录。当前源码主要分成三块：

- `多态`：`animal` 作为父类，`cat`、`dog`、`pig` 继承后分别重写 `eat()`；`duotai.main()` 演示了父类引用指向子类对象、成员变量看左边、成员方法看右边，以及 `use.useAnimal(animal a)` 统一接收不同子类对象。
- `多态练习`：`Animal` 封装 `name`、`age`，提供 getter/setter 和无参/有参构造；`Cat`、`Dog` 通过 `super(name, age)` 初始化父类数据，`Test.main()` 验证了两种初始化方式和多态调用。
- `转型`：`Test.main()` 演示向上转型、向下转型和 `playGame()` 的调用边界，重点是先确认真实类型再强转。
- `抽象类`：`main.java` 先记录抽象类、抽象方法的概念，`Animal.java` 目前还是普通类，后面再补真正的 `abstract` 实现。
