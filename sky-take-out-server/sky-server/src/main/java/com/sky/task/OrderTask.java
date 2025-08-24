package com.sky.task;

import com.sky.entity.Orders;
import com.sky.mapper.OrderMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

/**
 * 自定义定时任务，实现订单状态定时处理
 */
@Component
@Slf4j
public class OrderTask {

    @Autowired
    private OrderMapper orderMapper;

    
    /**
     * 处理支付超时订单
     */
    @Scheduled(cron = "0 15 * * * ?")
    public void processTimeoutOrder(){
        log.info("处理支付超时订单：{}", LocalDateTime.now());

        LocalDateTime time = LocalDateTime.now().minusMinutes(15);
        List<Orders> ordersList = orderMapper.getByStatusAndOrderTimeLT(Orders.PENDING_PAYMENT, time);

        if(ordersList != null && !ordersList.isEmpty()) {
            for(Orders orders : ordersList) {
                orders.setStatus(Orders.CANCELLED);
                orders.setCancelReason("订单超时，自动取消");
                orders.setCancelTime(LocalDateTime.now());
                orderMapper.update(orders);
            }
        }
    }


    /**
     * 处理派送中状态的订单
     */
    @Scheduled(cron = "0 0 3 * * ?")
    public void processDeliveryOrder(){
        log.info("处理派送中订单：{}", LocalDateTime.now());

        LocalDateTime time = LocalDateTime.now().minusHours(3);
        List<Orders> ordersList = orderMapper.getByStatusAndOrderTimeLT(Orders.DELIVERY_IN_PROGRESS, time);

        if(ordersList != null && !ordersList.isEmpty()) {
            ordersList.forEach(orders -> {
                orders.setStatus(Orders.COMPLETED);
                orderMapper.update(orders);
            });
        }
    }
}
