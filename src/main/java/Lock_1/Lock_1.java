package Lock_1;

/**
 * 通过synchronized实现
 *  1.同步普通方法，锁的是当前对象(以及对应的静态变量)
 *  2.同步静态方法，锁的是当前 Class 对象
 *  3.同步块，锁的是 () 中的对象
 */
public class Lock_1 implements Runnable {

    private static int count = 0;

    public static void main(String[] args) {

        for (int i = 0; i < 9; i++) {
            /**
             * 锁的是代码块(run方法)
             */
            Thread thread = new Thread(new Lock_1());
            thread.start();
        }
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("result: " + count);
    }

    @Override
    public void run() {
        synchronized (Lock_1.class) {
            for (int i = 0; i < 10000; i++){
                count++;
            }
        }
    }

}
