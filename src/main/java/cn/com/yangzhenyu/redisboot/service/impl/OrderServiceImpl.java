package cn.com.yangzhenyu.redisboot.service.impl;

import cn.com.yangzhenyu.redisboot.annotation.RedisLuck;
import cn.com.yangzhenyu.redisboot.entity.Order;
import cn.com.yangzhenyu.redisboot.entity.Shop;
import cn.com.yangzhenyu.redisboot.mapper.OrderMapper;
import cn.com.yangzhenyu.redisboot.mapper.ShopMapper;
import cn.com.yangzhenyu.redisboot.service.IOrderService;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Random;
import java.util.concurrent.locks.ReentrantLock;

@Service
public class OrderServiceImpl extends ServiceImpl<OrderMapper, Order> implements IOrderService {

    @Autowired
    private ShopMapper shopMapper;

    private ReentrantLock reentrantLock = new ReentrantLock();

    @Override
    @RedisLuck("luck")
    public void bug() {
        Shop shop = shopMapper.getOne();
        Integer num = shop.getNum();
        if (num > 0) {
            shopMapper.update(num - 1);
            this.getBaseMapper().saveOrder(shop.getId(), new Random().nextInt(10000));
        } else {
            throw new RuntimeException("庫存不足");
        }
    }

    @Override
    @RedisLuck("luck")
    public void buy() {
        // ....
    }
}
