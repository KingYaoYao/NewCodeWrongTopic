public class ClassLoadTest {
    public static void main(String[] args) {
        new B();
    }
}
class A {
    public A() {    //父类构造器
        System.out.println("class A");
    }
    { System.out.println("I'm A class"); }      //父类代码块
    static { System.out.println("class A static"); }    //父类静态代码块
}
 class B extends A {
    public B() {    //子类构造方法
        System.out.println("class B");
    }
    { System.out.println("I'm B class"); }      //子类代码块
    static { System.out.println("class B static"); }    //子类静态代码块

}
