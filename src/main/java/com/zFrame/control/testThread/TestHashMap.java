package com.zFrame.control.testThread;

import java.util.HashMap;
import java.util.Map;

/**
 * 
 *     
 * 项目名称：zFrame    
 * 类名称：TestHashMap    
 * 类描述：验证hashMap线程非安全实例    
 * 创建人：Gz    
 * 创建时间：2020年3月17日 上午11:27:13    
 * 修改人：Gz    
 * 修改时间：2020年3月17日 上午11:27:13    
 * 修改备注：    
 * @version     
 *
 */
public class TestHashMap {
    /**
     * @param args
     */
    public static void main(String[] args) {
        for (int i = 0; i < 5; i++) {
            QueryMap mq = new QueryMap(i, true);
            mq.start();
        }
        System.out.println(Thread.currentThread().getName());
    }

}

class QueryMap extends Thread {
    // public static ConcurrentHashMap map = new ConcurrentHashMap();
    public static Map<Integer, String> map = new HashMap<Integer, String>();
    static {
        map.put(0, "a");
        map.put(1, "a");
        map.put(2, "a");
        map.put(3, "a");
        map.put(4, "a");
    }

    /**
     * 查找数字
     */
    private Integer queryNum;

    /**
     * 预测返回结果
     */
    private boolean ret;

    public QueryMap(Integer n, boolean ret) {
        this.queryNum = n;
        this.ret = ret;
    }

    public void run() {
        while (true) {
            // int key = (int) (((int) 100000 * Math.random()) + 5);
            // String value = 100000 * Math.random() + "";
            //
            // map.put(key, value);
            // int removeKey = (int) (((int) 100000 * Math.random()) + 5);
            // map.remove(removeKey);
            map.put((int) (Math.random() * 1000000000), "2222");
            boolean retKey = map.containsKey(queryNum);
            if (retKey != ret) {
                // System.out.println("key-----------------" + key + "
                // removeKey-------------" + removeKey);
                System.out.println(Thread.currentThread().getName() + "查找" + queryNum + "错误!");
                return;
            }
            try {
                Thread.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("-------" + Thread.currentThread().getName());
        }
    }
}
