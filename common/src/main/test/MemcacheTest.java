import java.util.concurrent.TimeUnit;

import net.spy.memcached.AddrUtil;
import net.spy.memcached.MemcachedClient;
/**
 * Created by qilei on 2015/2/26.
 */
public class MemcacheTest {

    private final static String URI = "120.27.29.99:11211";

    private final static int num = 10;

    /**
     * @param args
     */
    public static void main(String[] args) throws Exception {
        MemcachedClient client = null;
        try {
            client = new MemcachedClient(AddrUtil.getAddresses(URI));

            //压力测试
            long start = System.currentTimeMillis();
            for(int i = 0; i < num; i++) {
                client.set("key"+i, 60, "value"+i);
            }
            System.out.println("Set " +num+" key-values cost time " + (System.currentTimeMillis()-start) + "ms.");

            start = System.currentTimeMillis();
            for(int i = 0; i < num; i++) {
                client.get("key"+i);
            }
            System.out.println("Get " +num+" key-values cost time " + (System.currentTimeMillis()-start) + "ms.");

        } catch(Exception e) {
            e.printStackTrace();
        } finally {
            if(client != null) {
                client.shutdown(3, TimeUnit.SECONDS);
            }
        }
    }
}
