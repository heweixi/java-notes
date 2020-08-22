package Lock_3;

import java.util.List;
import java.util.Map;

/**
 * 通过synchronized实现
 *  1.对象的锁(对象之间不共用)
 */
public class Lock_3{

    private int count = 0;
    private boolean locked = false;

    public static void main(String[] args) {
        Lock_3 lock3 = new Lock_3();
        Lock_3 lock31 = new Lock_3();
        for (int i = 0; i < 9; i++) {
            /**
             * 锁的是代码块(run方法)
             */
            if (i%3==0){
                lock31.testLock();
            }
            lock3.testLock();
        }
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("lock3: " + lock3.count);
        System.out.println("lock31: " + lock31.count);
    }

    public void testLock(){
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    for (int i = 0; i < 100000; i++){
                        inner:
                        for (;!getLock();){
                            continue inner;
                        }
                        count++;
                        releaseLock();
                    }
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        }).start();
    }

    /**
     * 最简单的获取锁的概念示意
     * synchronized确保了getLock同一时刻只能响应一个调用(其余排队等候响应)
     * @return
     */
    public synchronized boolean getLock(){
        if (!this.locked){
            this.locked = true;
            return true;
        }
        return false;
    }

    /**
     * 最简单的释放锁概念释义
     */
    public boolean releaseLock(){
        if(this.locked){
            this.locked = false;
            return true;
        }
        return false;
    }

}
