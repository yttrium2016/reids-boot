package cn.com.yangzhenyu.redisboot;

import cn.com.yangzhenyu.redisboot.queue.Customer;
import cn.com.yangzhenyu.redisboot.queue.Producer;
import cn.com.yangzhenyu.redisboot.queue.QueueConstant;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;

import java.util.concurrent.TimeUnit;

@SpringBootTest
class RedisBootApplicationTests {

    @Autowired
    private RedisTemplate redisTemplate;

    @Test
    void test() throws InterruptedException {
        Producer producer = new Producer("yProducer",redisTemplate);
        producer.start();

        Customer yzy = new Customer("yzy",redisTemplate);
        yzy.start();

//        Customer yzy2 = new Customer("yzy2",redisTemplate);
//        yzy2.start();

        while (true) {
            System.out.println("main : 已存储消息条数:" + producer.getCount());
            TimeUnit.SECONDS.sleep(10);
        }
    }

}
