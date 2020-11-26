# 2020.11.26

## 子类调用父类构造方法

```java
class Person {
	String name = "No name";
	public Person(String nm) {
		name = nm;
	}
}
class Employee extends Person {
	String empID = "0000";
	public Employee(String id) {
		empID = id;
	}
}
public class Test {
	public static void main(String args[]) {
		Employee e = new Employee("123");
		System.out.println(e.empID);
	}
}
编译错误
```

 如果子类构造器没有显示地调用超类的构造器，则将自动地调用超类默认（没有参数）的构造器。如果超类没有不带参数的构造器，并且在子类的构造器中有没有显示地调用超类的其他构造器，则Java编译器将报告错误。使用super调用构造器的语句必须是子类构造器的第一条语句。 

​                                                   ——p153《Java核心技术卷I》 

## JAVA和C/C++ char类型区别及溢出问题（反码补码）

```java
执行如下程序代码
char chr = 127;
int sum = 200;
chr += 1;
sum += chr;
后，sum的值是   ; （     ）
备注：同时考虑c/c++和Java的情况的话 
```

java中只有byte, boolean是一个字节, char是两个字节, 所以对于java来说127不会发生溢出, 输出328   但是对于c/c++语言来说, char是一个字节, 会发生溢出, 对127加一发生溢出, 0111 1111 --> 1000 0000, 1000 0000为补码-128, 所以结果为200-128=72 