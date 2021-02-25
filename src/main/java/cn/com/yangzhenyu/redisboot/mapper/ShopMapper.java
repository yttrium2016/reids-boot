package cn.com.yangzhenyu.redisboot.mapper;

import cn.com.yangzhenyu.redisboot.entity.Shop;
import com.baomidou.mybatisplus.annotation.SqlParser;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

@Mapper
public interface ShopMapper extends BaseMapper<Shop> {

    @Select("select * from shop where id = 1")
    Shop getOne();

    @Update("update shop set num = #{i} where id = 1")
    void update(int i);
}
