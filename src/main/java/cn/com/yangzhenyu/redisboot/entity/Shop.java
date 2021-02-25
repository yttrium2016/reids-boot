package cn.com.yangzhenyu.redisboot.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@TableName("shop")
@Data
public class Shop {
    @TableId(value = "id",type = IdType.AUTO)
    private Integer id;
    private String name;
    private Integer num;
}
