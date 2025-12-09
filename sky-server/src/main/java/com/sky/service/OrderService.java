package com.sky.service;

import com.sky.dto.*;
import com.sky.entity.OrderDetail;
import com.sky.result.PageResult;
import com.sky.vo.OrderPaymentVO;
import com.sky.vo.OrderStatisticsVO;
import com.sky.vo.OrderSubmitVO;
import com.sky.vo.OrderVO;

import java.util.List;

public interface OrderService {

    //提交订单
    OrderSubmitVO submitOrder(OrdersSubmitDTO ordersSubmitDTO);

     //用户支付
    OrderPaymentVO payment(OrdersPaymentDTO ordersPaymentDTO) throws Exception;

    //支付成功，修改订单状态
    void paySuccess(String outTradeNo);
    //取消订单
    void cancelOrder(OrdersCancelDTO ordersCancelDTO);

    //查询订单列表
    PageResult conditionSearch(OrdersPageQueryDTO ordersPageQueryDTO);

    //查询订单详情
    OrderVO detailsOrder(Long id);

    //统计订单数据
    OrderStatisticsVO statistics();

    //接单
    void confirmOrder(OrdersConfirmDTO ordersConfirmDTO);

    //订单拒单
    void rejectionOrder(OrdersRejectionDTO ordersRejectionDTO);
    //派送订单
    void deliveryOrder(Long id);

    //订单完成
    void completeOrder(Long id);

    //用户端分页查询
    PageResult pageQuery(Integer pagenum, Integer pageSize);

    //用户端查询订单详情
    OrderVO userOrderDetail(Long id);

    //用户取消订单
    void userCancelById(Long id);

    //用户再来一单
    void repetition(Long id);
}
