package com.sky.mapper;

import com.sky.entity.OrderDetail;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface OrderDetailMapper {
    /**
     * 批量插入订单明细数据
     * @param orderDetails
     */
    void insertBatch(List<OrderDetail> orderDetails);

    /**
     * 根据订单id查询订单明细
     * @param id
     * @return
     */
    @Select("select * from order_detail where order_id=#{id}")
    List<OrderDetail> getByOrderId(Long id);

    @Delete("delete from order_detail where order_id=#{id}")
    void deleteByOrderId(Long id);

    /**
     * 插入订单明细数据
     * @param orderDetail
     */
    @Insert("insert into order_detail (order_id, dish_id, setmeal_id, dish_flavor, number, amount,name,image) " +
            "values (#{orderId}, #{dishId}, #{setmealId}, #{dishFlavor}, #{number}, #{amount},#{name},#{image})")
    void insert(OrderDetail orderDetail);
}
