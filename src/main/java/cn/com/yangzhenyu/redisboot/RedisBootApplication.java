package cn.com.yangzhenyu.redisboot;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("cn.com.yangzhenyu.redisboot.mapper")
public class RedisBootApplication {

    public static void main(String[] args) {
        SpringApplication.run(RedisBootApplication.class, args);
    }

}
