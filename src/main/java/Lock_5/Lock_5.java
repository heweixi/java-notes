package Lock_5;

import org.redisson.Redisson;
import org.redisson.api.RAtomicLongReactive;
import org.redisson.api.RBatch;
import org.redisson.api.RBatchReactive;
import org.redisson.api.RedissonReactiveClient;
import org.redisson.config.Config;
import

public class Lock_5 implements Runnable{

    private static int count = 0;

    /**
     * 通过redisson实现redis锁
     * @param args
     */
    public static void main(String[] args) {

        Config config = new Config();
        config.useClusterServers().addNodeAddress("redis://127.0.0.1:7181");

        RedissonReactiveClient client = Redisson.createReactive(config);
        RAtomicLongReactive atomicLong = client.getAtomicLong("myLong");




        for (int i = 0; i < 10; i++) {
            Thread thread = new Thread(new Lock_5());
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
        for (int i = 0; i < 1000000; i++){
            count++;
        }
    }

}
