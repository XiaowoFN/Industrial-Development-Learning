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
