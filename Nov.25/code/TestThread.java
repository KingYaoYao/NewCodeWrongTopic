package thread.test;

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
