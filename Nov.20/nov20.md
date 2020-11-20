# 2020.11.20

## JAVA中的数据类型自动提升

```java
关于代码片段描述的正确的是
byte b1=1,b2=2,b3,b6; 
final byte b4=4,b5=6; 
b6=b4+b5; 
b3=(b1+b2); 
System.out.println(b3+b6);
结果：语句：b3=b1+b2编译出错
    b1+b2会自动升级为int类型
    应该改为b3 = (byte)(b1+b2);
```

  表达式的数据类型自动提升, 关于类型的自动提升，注意下面的规则。 

  ①所有的byte,short,char型的值将被提升为int型； 

  ②如果有一个操作数是long型，计算结果是long型； 

  ③如果有一个操作数是float型，计算结果是float型； 

  ④如果有一个操作数是double型，计算结果是double型； 

------



## JAVA类初始化顺序

```java
输出结果是：？
class A {
    public A() {
        System.out.println("class A");
    }
    { System.out.println("I'm A class"); } 
    static { System.out.println("class A static"); }
}
public class B extends A {
    public B() {
        System.out.println("class B");
    }
    { System.out.println("I'm B class"); }
    static { System.out.println("class B static"); }
     
    public static void main(String[] args) { 
 new B(); 
	
}
}
答案：
class A static
class B static
I'm A class
class A
I'm B class
class B
```

java类的初始化顺序按照：

1. 静态初始化→父类初始化→子类初始化
2. 静态成员和static块→普通成员和非static块→构造函数
3. 父类static成员→子类static成员→父类普通成员和非static块→父类构造函数→子类普通成员和非static块→子类构造函数

