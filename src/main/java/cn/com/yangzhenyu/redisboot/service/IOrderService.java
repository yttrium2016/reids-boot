package cn.com.yangzhenyu.redisboot.service;

import cn.com.yangzhenyu.redisboot.entity.Order;
import com.baomidou.mybatisplus.extension.service.IService;

public interface IOrderService extends IService<Order> {
    void bug();

    void buy();
}
