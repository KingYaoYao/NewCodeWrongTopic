# 2020.11.25

## 直接调用线程start方法

```java
public class TestThread {
    public static void main(String[] args) {
        Runnable Runnable = new Runnable() {
            @Override
            public void run() {
                System.out.print("foo");
            }
        };
        Thread t = new Thread(Runnable);
        t.run();
        System.out.print("bar");
    }
}

结果：foobar
```

 调用start（）后，**线程会被放到等待队列**，等待CPU调度，并不一定要马上开始执行，只是将这个线程置于可动行状态。然后通过JVM，线程Thread会调用run（）方法，执行本线程的线程体。
1.start（）方法来启动线程，真正实现了多线程运行。**这时无需等待run方法体代码执行完毕，可以直接继续执行下面的代码**；
2.run（）方法当作普通方法的方式调用。程序还是要顺序执行，**要等待run方法体执行完毕**后，才可继续执行下面的代码， 这样就没有达到写线程的目的。

## 变量初始化问题

```java
public class Test {
public static void main(String args[]) {
int a = 10;
int b;
int c;
if (a > 50) {
b = 9;
}
c = b + a;
}
}

结果：
    编译错误
```

 方法内定义的变量需要进行初始化，类中定义的变量不需要进行初始化。