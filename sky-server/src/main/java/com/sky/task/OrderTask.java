package com.sky.task;

import com.sky.entity.Orders;
import com.sky.mapper.OrderMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;

@Component
@Slf4j
public class OrderTask {
    @Autowired
    private OrderMapper orderMapper;
    /**
     * 定时处理状态为“待支付”的订单
     */
    @Scheduled(cron="0 * * * * ?")
    public void processTimeOutOrder(){
        log.info("处理超时订单");
        LocalDateTime localDateTime = LocalDateTime.now().plusMinutes(-15);
        List<Orders> orders = orderMapper.getByStatusAndOrderTimeOut(Orders.PENDING_PAYMENT, localDateTime);
        if(orders!=null&&orders.size()>0){
            for (Orders order : orders) {
                order.setStatus(Orders.CANCELLED);
                order.setCancelReason("订单超时，自动取消");
                order.setCancelTime(LocalDateTime.now());
                orderMapper.update(order);
            }
        }
    }
    /**
     * 定时处理处于派送中的订单
     */
    @Scheduled(cron="0 0 1 * * ?")
    public void processDeliveryOrder(){
        log.info("定时处理处于派送状态的订单");
        LocalDateTime localDateTime = LocalDateTime.now().plusMinutes(-60);
        List<Orders> orders = orderMapper.getByStatusAndOrderTimeOut(Orders.DELIVERY_IN_PROGRESS, localDateTime);
        if(orders!=null&&orders.size()>0){
            for (Orders order : orders) {
                order.setStatus(Orders.COMPLETED);
                orderMapper.update(order);
            }
        }
    }
}
