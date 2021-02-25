package cn.com.yangzhenyu.redisboot.service.impl;

import cn.com.yangzhenyu.redisboot.entity.Shop;
import cn.com.yangzhenyu.redisboot.mapper.ShopMapper;
import cn.com.yangzhenyu.redisboot.service.IShopService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

@Service
public class ShopServiceImpl extends ServiceImpl<ShopMapper, Shop> implements IShopService {

    @Override
    public Shop one() {
        return getBaseMapper().getOne();
    }
}
