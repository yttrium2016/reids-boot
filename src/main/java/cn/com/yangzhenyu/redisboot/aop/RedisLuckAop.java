package cn.com.yangzhenyu.redisboot.aop;

import cn.com.yangzhenyu.redisboot.annotation.RedisLuck;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.redisson.Redisson;
import org.redisson.api.RBloomFilter;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import sun.reflect.generics.tree.ClassSignature;

import java.lang.reflect.Method;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

@Aspect
@Component
public class RedisLuckAop {

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    @Autowired
    private RedissonClient redissonClient;

//    @Around("@annotation(cn.com.yangzhenyu.redisboot.annotation.RedisLuck)")
    public Object around(ProceedingJoinPoint point) throws Throwable {
        Object result = null;

        MethodSignature signature = (MethodSignature) point.getSignature();
        RedisLuck redisLuck = signature.getMethod().getAnnotation(RedisLuck.class);
        if (null == redisLuck) {
            return point.proceed();
        }
        String key = redisLuck.value();
        String value = UUID.randomUUID().toString();

        try {
            Boolean flag = redisTemplate.opsForValue().setIfAbsent(key, value, 30, TimeUnit.SECONDS);
            if (flag) {
                result = point.proceed();
            } else {
                throw new RuntimeException("请刷新后重试");
            }
        } finally {
            if (redisTemplate.opsForValue().get(key).equals(value)) {
                redisTemplate.delete(key);
            }
        }
        return result;
    }

    @Around("@annotation(cn.com.yangzhenyu.redisboot.annotation.RedisLuck)")
    public Object aopRedisson(ProceedingJoinPoint point) throws Throwable{
        Object result = null;

        MethodSignature signature = (MethodSignature) point.getSignature();
        RedisLuck redisLuck = signature.getMethod().getAnnotation(RedisLuck.class);
        if (null == redisLuck) {
            return point.proceed();
        }

        RLock lock = redissonClient.getLock(redisLuck.value());

        try {
            lock.lock(10,TimeUnit.SECONDS);
            result = point.proceed();
        } finally {
            lock.unlock();
        }


        return result;
    }
}
