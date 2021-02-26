package cn.com.yangzhenyu.redisboot.queue;

import org.springframework.data.redis.core.BoundListOperations;
import org.springframework.data.redis.core.ListOperations;
import org.springframework.data.redis.core.RedisTemplate;

public class Customer extends Thread{
    private String customerName;
    private volatile int count;
    private RedisTemplate redisTemplate;
    private volatile boolean isOver = true;
    private ListOperations<String,String> listOperations;

    public Customer(String name,RedisTemplate redisTemplate) {
        this.customerName = name;
        this.redisTemplate = redisTemplate;
        listOperations = redisTemplate.opsForList();
    }

    public void processMessage() {
        BoundListOperations boundListOperations = redisTemplate.boundListOps(QueueConstant.MESSAGE_KEY);
        String message = (String) boundListOperations.rightPop();
        if(message != null) {
            count++;
            handle(message);
        }
    }

    public void handle(String message) {
        System.out.println(customerName + " 正在处理消息,消息内容是: " + message + " 这是第" + count + "条");
        isOver = true;
    }

    @Override
    public void run() {
        while (true) {
            if (isOver){
                isOver = false;
                System.out.println("111");
                processMessage();
            }
        }
    }
}
