package cn.com.yangzhenyu.redisboot.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class RedisLuckAop {

    @Autowired
    private RedisTemplate redisTemplate;

    public Object aop(ProceedingJoinPoint joinPoint) throws Throwable{
        Object result = null;


        return result;
    }
}
