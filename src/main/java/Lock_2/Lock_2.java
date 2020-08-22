package Lock_2;

import java.util.List;
import java.util.Map;

/**
 * 通过synchronized实现
 *  1.当前class的锁,JVM唯一
 */
public class Lock_2{

    private static int count = 0;
    private static boolean locked = false;

    public static void main(String[] args) {

        for (int i = 0; i < 9; i++) {
            /**
             * 锁的是代码块(run方法)
             */
            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {

                        for (int i = 0; i < 1000; i++){
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
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("result: " + count);
    }

    /**
     * 最简单的获取锁的概念示意
     * synchronized确保了getLock同一时刻只能响应一个调用(其余排队等候响应)
     * @return
     */
    public synchronized static boolean getLock(){
        if (!locked){
            locked = true;
            return true;
        }
        return false;
    }

    /**
     * 最简单的释放锁概念释义
     */
    public static boolean releaseLock(){
        if(locked){
            locked = false;
            return true;
        }
        return false;
    }

    //@Override
    //public void run() {
    //    for (int i = 0; i < 1000; i++){
    //        while(getLock()){
    //            count++;
    //            releaseLock();
    //        }
    //    }
    //}

}
