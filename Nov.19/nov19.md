2020.11.19

## 数组复制效率

System.arraycopy>clone>Arrays.copyOf>for循环

```java
 
    @HotSpotIntrinsicCandidate
    public static native void arraycopy(Object src,  int  srcPos,Object dest, 
           int destPos,int length);
    @HotSpotIntrinsicCandidate（这个及时but的情况）
    protected native Object clone() throws CloneNotSupportedException;
 
    @HotSpotIntrinsicCandidate（不是NATIVE方法，调用了Systrm.arraycopy）
    public static <T,U> T[] copyOf(U[] original, int newLength, Class<? extends T[]> newType) {
        @SuppressWarnings("unchecked")
        T[] copy = ((Object)newType == (Object)Object[].class)
            ? (T[]) new Object[newLength]
            : (T[]) Array.newInstance(newType.getComponentType(), newLength);
        System.arraycopy(original, 0, copy, 0,
                         Math.min(original.length, newLength));
        return copy;
    }
```

>1. 浅复制：复制地址，相当于复制了房子(内存数据)的钥匙,一改全改
>2. 深复制：复制值，通俗意义上的拷贝，相当于建了一个一模一样的房子(内存的数据)
>3. @HotSpotIntrinsicCandidate 注释和 JNI(Java Native  Interface)普通的native方法通俗的讲就是编译后还要通过JNI再次编译成.cpp文件才能执行.而有  @HotSpotIntrinsicCandidate这个注解的方法在JVM里就是用.cpp文件写好的,所以就跳过了JNI阶段,所以速度就能提升。
>4. It indicates that an annotated method **may be (but is not guaranteed to be)** intrinsified by the HotSpot VM 可能但不被保证。 
>5. 前三种方法对于基本数据类型是深复制，对于对象是潜复制。

------

## volatile和synchronized

  volatile到底做了什么: 

- ​    禁止了指令重排      

- ​    保证了不同线程对这个变量进行操作时的可见性，即一个线程修改了某个变量值，这个新值对其他线程是立即可见的      

- ​    不保证原子性（线程不安全）     

  synchronized关键字和volatile关键字比较： 

- ​    volatile关键字是线程同步的轻量级实现，所以volatile性能肯定比synchronized关键字要好。但是volatile关键字只能用于变量而synchronized关键字可以修饰方法以及代码块。synchronized关键字在JavaSE1.6之后进行了主要包括为了减少获得锁和释放锁带来的性能消耗而引入的偏向锁和轻量级锁以及其它各种优化之后执行效率有了显著提升，实际开发中使用 synchronized 关键字的场景还是更多一些。      

- ​    多线程访问volatile关键字不会发生阻塞，而synchronized关键字可能会发生阻塞      

- ​    volatile关键字能保证数据的可见性，但不能保证数据的原子性。synchronized关键字两者都能保证。      

- ​    volatile关键字主要用于解决变量在多个线程之间的可见性，而 synchronized关键字解决的是多个线程之间访问资源的同步性。     

------

## 局部变量初始化

 局部变量可以先申明不用必须初始化，但使用到了一定要先初始化 

```java
下列代码运行结果
import java.io.*;
import java.util.*;
public class foo{
public static void main (String[] args){
String s;
System.out.println("s=" + s);
}
}
结果：由于String s没有初始化，代码不能编译通过
```

------

