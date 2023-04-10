package com.tju.elmcloud.controller;

import com.tju.elmcloud.feign.CreditFeignClient;
import com.tju.elmcloud.po.CommonResult;
import com.tju.elmcloud.po.Orders;
//import com.tju.elmcloud.service.CreditService; TODO:积分系统待添加
import com.tju.elmcloud.service.OrdersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;

import java.util.List;

//@CrossOrigin("*")
@RestController
@RequestMapping("/OrdersController")
public class OrdersController {

    @Autowired
    private OrdersService ordersService;

//    @Autowired
//    private CreditService creditService;

    @Qualifier("com.tju.elmcloud.feign.CreditFeignClient")
    @Autowired
    private CreditFeignClient creditFeignClient;

    @PostMapping("/createOrders/{userId}/{businessId}/{daId}/{orderTotal}")
    public CommonResult<Integer> createOrders(
            @PathVariable("userId") String userId,
            @PathVariable("businessId") Integer businessId,
            @PathVariable("daId") Integer daId,
            @PathVariable("orderTotal") Double orderTotal) throws Exception {
        Orders orders = new Orders();
        orders.setUserId(userId);
        orders.setBusinessId(businessId);
        orders.setDaId(daId);
        orders.setOrderTotal(orderTotal);
        int orderId = ordersService.createOrders(orders);
//        int creditId = creditService.saveCreditByOrder(odId);
        creditFeignClient.saveCreditByOrder(orderId);
        return new CommonResult<>(200, "success", orderId);
    }

    @GetMapping("/getOrdersById/{orderId}")
    public CommonResult<Orders> getOrdersById(@PathVariable("orderId") Integer orderId) throws Exception {
        Orders orders = ordersService.getOrdersById(orderId);
        return new CommonResult<>(200, "success", orders);
    }

    @GetMapping("/listOrdersByUserId/{userId}")
    public CommonResult<List<Orders>> listOrdersByUserId(@PathVariable("userId") String userId) throws Exception {
        List<Orders> list = ordersService.listOrdersByUserId(userId);
        return new CommonResult<>(200, "success", list);
    }
}
