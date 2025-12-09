package com.sky.mapper;

import com.github.pagehelper.Page;
import com.sky.dto.OrdersPageQueryDTO;
import com.sky.entity.Orders;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.time.LocalDateTime;
import java.util.List;

@Mapper
public interface OrderMapper {
    /**
     * 插入订单数据
     *
     * @param order
     */
    void insert(Orders order);

    /**
     * 根据id查询订单数据
     *
     * @param id
     * @return
     */
    @Select("select * from orders where id = #{id}")
    Orders selectById(Long id);

    /**
     * 更新订单数据
     *
     * @param order
     */
    void update(Orders order);

    /**
     * 订单分页查询
     *
     * @param ordersPageQueryDTO
     * @return
     */
    Page<Orders> pageQuery(OrdersPageQueryDTO ordersPageQueryDTO);

    /**
     * 根据状态统计订单数量
     *
     * @param toBeConfirmed
     * @return
     */
    @Select("select count(id) from orders where status = #{status}")
    Integer countStatus(Integer status);

    /**
     * 根据id删除订单数据
     *
     * @param id
     */
    @Delete("delete from orders where id=#{id}")
    void deleteById(Long id);

    /**
     * 根据订单号查询订单
     *
     * @param outTradeNo
     * @return
     */
    @Select("select * from orders where number=#{outTradeNo}")
    Orders getByNumber(String outTradeNo);

    /**
     * 根据状态和下单时间查询订单
     *
     * @param pendingPayment
     * @param localDateTime
     * @return
     */
    @Select("select * from Orders where status=#{status} and order_time=#{localDateRime}")
    List<Orders> getByStatusAndOrderTimeOut(Integer status, LocalDateTime localDateTime);
}
