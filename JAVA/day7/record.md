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

## 2026-09-09

### 18. 后续记录约定确认

从本次开始，`day7` 模块中新编写或修改的代码，以及围绕这些代码提出的问题和对应解答，都需要按照本文档已有格式及时记录。

后续记录继续关注以下内容：

- 新增或修改的源码及其功能。
- 涉及的 Java 语法、概念和实现方式。
- 编译或运行结果。
- 遇到的问题、错误原因和解决方式。
- 当前学习进度及下一步待学习内容。

已经记录过的内容不重复展开；只有在代码、理解或运行结果发生变化时，才追加或更新对应章节。

### 19. Git 分支删除

在 IDEA 中删除新建分支时，不能直接删除当前正在使用的分支。操作步骤：

1. 点击右下角当前分支名称。
2. 先切换到其他分支，例如 `main`。
3. 再次打开本地分支列表，右键要删除的分支，例如 `tt`。
4. 选择“删除”。

也可以在终端执行：

```bash
git switch main
git branch -d tt
```

`-d` 只允许删除已经合并的分支。如果确认不需要该分支，即使它还没有合并，也可以使用：

```bash
git branch -D tt
```

如果 `tt` 已经推送到远程仓库，还需要删除远程分支：

```bash
git push origin --delete tt
```

本次检查 `JAVA/day7` 仓库时，当前分支是 `main`，本地分支列表中暂未发现 `tt`。因此需要先确认 IDEA 当前打开的项目是否就是 `JAVA/day7`，以及 `tt` 是本地分支还是远程分支。

## 2026-09-09

### 20. `抽象类实战` 案例评审

本次检查目录：

```text
src/main/java/抽象类实战/
├── Animal.java
├── Cat.java
├── Dog.java
└── AnimalTest.java
```

当前实现与需求基本一致：

- `Animal` 使用 `abstract` 修饰，说明它是抽象类。
- `Animal` 中定义了私有成员变量 `age` 和 `name`。
- `Animal` 提供了无参构造方法和有参构造方法。
- 通过 getter/setter 对成员变量进行封装。
- `Animal` 定义抽象方法 `eat()`。
- `Cat`、`Dog` 继承 `Animal`，并使用 `@Override` 实现各自的 `eat()`。
- `AnimalTest` 使用 `Animal` 类型的引用指向 `Cat`、`Dog` 对象，验证了抽象类引用和成员方法多态。

关键代码结构：

```java
public abstract class Animal {
    private int age;
    private String name;

    public Animal() {
    }

    public Animal(int age, String name) {
        this.age = age;
        this.name = name;
    }

    public abstract void eat();
}
```

```java
Animal c = new Cat(1, "依依");
Animal d = new Dog(48, "冰");

c.eat();
d.eat();
```

本次使用 JDK 8 的 `javac` 直接编译并运行通过，说明源码没有编译错误。终端中的中文输出出现乱码，是控制台编码与输出编码不一致导致的显示问题，不影响 Java 程序逻辑。

### 21. `抽象类实战` 改进建议

当前没有必须修复的功能性错误，但可以继续改进：

1. 测试类目前使用了有参构造方法，没有实际验证无参构造方法和 setter。可以补充：

```java
Cat c = new Cat();
c.setAge(1);
c.setName("依依");
```

2. 当前构造方法参数顺序是 `age, name`。更符合日常表达的顺序通常是 `name, age`，建议父类和子类统一调整，避免调用时混淆。

3. `System.out.println(c.getAge() + c.getName())` 虽然可以运行，但输出可读性一般，建议改为：

```java
System.out.println(c.getName() + "，" + c.getAge() + "岁");
```

4. `Cat` 和 `Dog` 中的无效注释代码 `//c.eat()`、`//d.eat()` 可以删除，避免保留无用代码。

5. `Dog.eat()` 中的“小狗爱吃屎”只是输出文本，不影响语法和多态逻辑；为了让案例表达更自然，可以改成“小狗爱吃骨头”或“小狗爱吃狗粮”。

当前学习进度：

```text
[已完成] 抽象类和抽象方法的实际定义
[已完成] 子类继承抽象类并实现抽象方法
[已完成] 抽象类引用指向不同子类对象
[已完成] 通过多态调用 Cat、Dog 的 eat()
[待补充] 无参构造方法和 setter 的测试
[待巩固] 构造方法参数顺序和测试输出的规范性
```

### 22. 关于 `Dog.eat()` 输出内容的说明

之前建议将 `Dog.eat()` 中的输出：

```java
System.out.println("小狗爱吃屎");
```

改成“小狗爱吃骨头”，只是为了让教学案例中的表达更常见、更自然，不是因为原代码存在语法或逻辑错误。

用户明确选择保留“小狗爱吃屎”。该输出属于自定义业务文本，不影响抽象类、方法重写和多态调用，因此保留原实现，不修改 `Dog.java`。

### 23. day8 模块与 day7 不一致的原因

本次检查 `E:\cyr\industrial-development-learning\JAVA` 目录时，实际存在的模块只有：

```text
day1
day2
day3
day4
day5
day6
day7
```

其中 `day1` 到 `day7` 都包含 `pom.xml` 和 `src/main/java`，属于已经创建完整的 Maven 项目结构。

当前磁盘上没有找到 `JAVA/day8` 目录，也没有找到 `JAVA/day8/pom.xml`。但是 IDEA 配置文件 `.idea/misc.xml` 中已经记录了：

```text
$PROJECT_DIR$/JAVA/day8/pom.xml
```

这说明 IDEA 记住了一个 `day8` Maven 项目路径，但这个路径对应的实际文件并不存在。常见原因：

- 新建模块时只在 IDEA 配置里添加了 Maven 路径，但文件夹或 `pom.xml` 没有创建成功。
- 新建时路径选错，`day8` 被建到了其他位置。
- `day8` 文件夹被删除了，但 IDEA 还保留了旧的 Maven 项目记录。
- IDEA 还没有刷新项目结构。

要让 `day8` 和 `day7` 一样，应确保真实存在：

```text
JAVA/day8/pom.xml
JAVA/day8/src/main/java
```

如果 IDEA 里显示异常，可以先删除无效的 day8 Maven 项目记录，再重新按 day7 的结构创建或导入。

### 24. 清理 day8 无效模块痕迹

用户删除了之前创建异常的 `day8`，为了后续重新创建，需要清理 IDEA 中残留的无效配置。

本次清理位置：

```text
.idea/compiler.xml
.idea/encodings.xml
.idea/misc.xml
.idea/workspace.xml
```

清理内容包括：

- `day8` 的编译目标配置。
- `JAVA/day8/src/main/java` 和 `JAVA/day8/src/main/resources` 的编码配置。
- Maven 项目列表中的 `JAVA/day8/pom.xml`。
- IDEA workspace 中指向 `JAVA/day8/src/main/java/day8/Main.java` 的调试路径和断点。

复查结果：

```text
.idea 中已经没有 day8
JAVA/day8 目录不存在
```

因此现在可以重新创建 `day8`。建议创建后确认结构与 `day7` 一致：

```text
JAVA/day8
├── pom.xml
└── src
    └── main
        └── java
```

### 25. day7 的 Maven 结构与 day8 创建方式

`day7` 是一个独立 Maven 项目，核心结构是：

```text
JAVA/day7
├── pom.xml
└── src
    └── main
        └── java
```

其中：

- `pom.xml` 是 Maven 项目的配置文件。
- `src/main/java` 是 Java 源代码目录。
- `target` 是编译后自动生成的目录，不需要手动创建，也不需要提交到 Git。

当前仓库不是一个统一父级 `pom.xml` 管理所有 day 模块的多模块 Maven 项目，而是 `day1` 到 `day7` 各自都有自己的 `pom.xml`，在 IDEA 中分别作为 Maven 项目导入。

创建 `day8` 时，应让它和 `day7` 保持同样结构：

```text
JAVA/day8
├── pom.xml
└── src
    └── main
        └── java
```

在 IDEA 中推荐方式：

1. 右键 `JAVA` 目录。
2. 选择 `New -> Module`。
3. 构建系统选择 `Maven`。
4. 模块名填写 `day8`。
5. 路径确认是 `E:\cyr\industrial-development-learning\JAVA\day8`。
6. JDK 选择和 day7 一致的 JDK 11。
7. 创建完成后确认有 `pom.xml` 和 `src/main/java`。

如果 IDEA 没有自动创建完整目录，也可以手动创建 `JAVA/day8/src/main/java`，再添加一个和 day7 类似的 `pom.xml`。

### 26. IDEA 右键菜单没有 `Module`

用户在 IDEA 中右键目录时，新建菜单里没有看到 `Module`。这个菜单主要用于新建普通文件、目录、Java 类、HTML 文件等，不一定显示模块创建入口。

创建 Maven 模块更可靠的入口是：

```text
File -> New -> Module
```

如果顶部菜单也没有 `Module`，可以使用：

```text
File -> Project Structure -> Modules -> + -> New Module
```

也可以直接按 `day7` 的结构手动创建：

```text
JAVA/day8
├── pom.xml
└── src
    └── main
        └── java
```

然后在 IDEA 的 Maven 面板中点击 `+` 或 `Add Maven Project`，选择：

```text
JAVA/day8/pom.xml
```

这样 `day8` 也会被 IDEA 识别为 Maven 项目。

### 27. 新建 day8 后文件结构与 day7 不完全一样

用户重新创建 `day8` 后，检查到当前结构为：

```text
JAVA/day8
├── .mvn
├── pom.xml
└── src
    ├── main
    │   ├── java
    │   └── resources
    └── test
        └── java
```

`day7` 的核心结构为：

```text
JAVA/day7
├── pom.xml
└── src
    └── main
        └── java
```

两者不一样的原因是：`day8` 是通过 IDEA 新建 Maven 项目时生成的更完整标准结构，而 `day7` 是更精简的 Maven 结构。

`day8` 多出来的目录含义：

- `.mvn`：Maven 相关配置目录，目前为空，可以不管。
- `src/main/resources`：放配置文件、SQL、图片等资源文件。
- `src/test/java`：放测试代码。

这些目录不会影响 Java 代码编写。当前 IDEA 已经在 Maven 配置中识别了：

```text
$PROJECT_DIR$/JAVA/day8/pom.xml
```

因此 `day8` 已经是 Maven 项目。只要在 `src/main/java` 下写代码即可。

如果想让视觉结构和 `day7` 完全一致，可以删除空的 `.mvn`、`src/main/resources` 和 `src/test/java`；但没有必要，因为它们属于 Maven 项目常见目录。

### 28. 同样创建 Maven 模块但结构不同的原因

用户反馈：在另一台电脑上用同样方式创建 Maven 模块时，结构像 `day7` 一样更精简；但当前电脑创建的 `day8` 多出了 `.mvn`、`src/main/resources`、`src/test/java`。

主要原因可能有三类：

1. IDEA 版本或 Maven 创建模板不同。不同版本的 IDEA 对 Maven 项目的默认生成目录不完全一样，有的只生成 `src/main/java`，有的会同时生成 `resources` 和 `test` 目录。

2. 创建向导中的选项不同。例如是否启用 Maven Wrapper、是否生成测试目录、是否使用某个 archetype，都会影响最终结构。

3. Git 不跟踪空目录。如果另一台电脑上的项目是从 Git 拉下来的，空的 `src/main/resources`、`src/test/java`、`.mvn` 即使曾经存在，也不会被 Git 保存和同步。只有目录中有文件时，Git 才会跟踪它。

因此 `day7` 和 `day8` 的差异不代表项目有问题。Maven 真正关键的是：

```text
pom.xml
src/main/java
```

`src/main/resources`、`src/test/java` 和 `.mvn` 都是可选或辅助目录。当前学习阶段只需要在 `src/main/java` 中写代码即可。

### 29. Maven 中 `test` 文件夹的作用

`src/test/java` 是 Maven 约定的测试代码目录，用来放专门测试程序是否正确的 Java 代码。

常见 Maven 目录分工：

```text
src/main/java      放正式 Java 源代码
src/main/resources 放正式程序用到的资源文件
src/test/java      放测试 Java 代码
src/test/resources 放测试时用到的资源文件
```

例如正式代码中有一个 `Student` 类，可以在 `src/test/java` 中写 `StudentTest`，专门测试 `Student` 的方法是否正确。

执行 Maven 测试命令时：

```bash
mvn test
```

Maven 会编译并运行 `src/test/java` 下的测试代码。

当前学习阶段如果还没有学单元测试，可以暂时不用管 `src/test/java`。平时练习 Java 代码仍然写在：

```text
src/main/java
```
