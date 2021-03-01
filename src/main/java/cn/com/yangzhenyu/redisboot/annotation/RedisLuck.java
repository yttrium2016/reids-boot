package cn.com.yangzhenyu.redisboot.annotation;

import java.lang.annotation.*;

@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface RedisLuck {
    String value() default "redis_luck";
}
