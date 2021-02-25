package cn.com.yangzhenyu.redisboot.mapper;

import cn.com.yangzhenyu.redisboot.entity.Order;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Update;

@Mapper
public interface OrderMapper extends BaseMapper<Order> {

    @Update("INSERT INTO `redis_boot`.`order` (`user_id`, `shop_id`) VALUES ( #{id1}, #{id})")
    void saveOrder(Integer id, int id1);
}
