package cn.com.yangzhenyu.redisboot.controller;

import cn.com.yangzhenyu.redisboot.service.IOrderService;
import cn.com.yangzhenyu.redisboot.service.IShopService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Objects;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

@Controller
public class TestController {

    @Autowired
    private IShopService shopService;

    @Autowired
    private IOrderService orderService;

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    @RequestMapping("/get")
    @ResponseBody
    public Object get() {
        return shopService.one();
    }

    @RequestMapping("/ccc")
    @ResponseBody
    public Object ccc() {
        return "ccc";
    }

    @RequestMapping("/bug")
    @ResponseBody
    public Object bug() {
        try {
            orderService.bug();
            return "ok";
        } catch (Exception e) {
            e.printStackTrace();
            return e.getMessage();
        }
    }


    @RequestMapping("/buy")
    @ResponseBody
    public Object buy() {

        String key = "redisLuck";
        String value = UUID.randomUUID().toString();

        Boolean flag = redisTemplate.opsForValue().setIfAbsent(key, value, 30, TimeUnit.SECONDS);
        if (flag == null || !flag) {
            return "抢购失败,请稍后再试.";
        }

        try {
            orderService.buy();
            return "抢购成功";
        } finally {
            if (Objects.equals(redisTemplate.opsForValue().get(key), value)) {
                redisTemplate.delete(key);
            }
        }

    }

    @RequestMapping("/buy2")
    @ResponseBody
    public Object buy2() {

        String key = "redisLuck";
        String value = UUID.randomUUID().toString();

        Boolean flag = redisTemplate.opsForValue().setIfAbsent(key, value, 30, TimeUnit.SECONDS);
        if (flag == null || !flag) {
            return "抢购失败,请稍后再试.";
        }

        try {
            orderService.buy();
            return "抢购成功";
        } finally {
            if (Objects.equals(redisTemplate.opsForValue().get(key), value)) {
                redisTemplate.delete(key);
            }
        }

    }
}
