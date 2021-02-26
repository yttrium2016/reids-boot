package cn.com.yangzhenyu.redisboot.controller;

import cn.com.yangzhenyu.redisboot.service.IOrderService;
import cn.com.yangzhenyu.redisboot.service.IShopService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class TestController {

    @Autowired
    private IShopService shopService;

    @Autowired
    private IOrderService orderService;

    @RequestMapping("/get")
    @ResponseBody
    public Object get() {
        return shopService.one();
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
}
