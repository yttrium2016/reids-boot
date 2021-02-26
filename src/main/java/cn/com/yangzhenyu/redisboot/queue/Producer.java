package cn.com.yangzhenyu.redisboot.queue;

import org.springframework.data.redis.core.ListOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.UUID;
import java.util.concurrent.TimeUnit;

/**
 * 消息队列
 * 生产者
 */
public class Producer extends Thread {

    private ListOperations<String,String> listOperations;
    private RedisTemplate<String, Object> redisTemplate;
    private String producerName;
    private volatile int count;

    public Producer(String name,RedisTemplate redisTemplate) {
        this.producerName = name;
        this.redisTemplate = redisTemplate;
        listOperations = redisTemplate.opsForList();
    }

    public void putMessage(String message) {
        Long size = listOperations.leftPush(QueueConstant.MESSAGE_KEY, message);
        System.out.println(producerName + ": 当前未被处理消息条数为:" + size);
        count++;
    }

    public int getCount() {
        return count;
    }

    @Override
    public void run() {
        try {
            while (true) {
                putMessage(UUID.randomUUID().toString());
                TimeUnit.SECONDS.sleep(5);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
