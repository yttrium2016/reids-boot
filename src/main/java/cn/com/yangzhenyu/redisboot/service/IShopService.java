package cn.com.yangzhenyu.redisboot.service;

import cn.com.yangzhenyu.redisboot.entity.Shop;
import com.baomidou.mybatisplus.extension.service.IService;

public interface IShopService extends IService<Shop> {
    Shop one();
}
