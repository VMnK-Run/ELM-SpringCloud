package com.tju.elmcloud.service.impl;

import com.tju.elmcloud.mapper.CartMapper;
import com.tju.elmcloud.mapper.OrderDetailetMapper;
import com.tju.elmcloud.mapper.OrdersMapper;
import com.tju.elmcloud.po.Cart;
import com.tju.elmcloud.po.OrderDetailet;
import com.tju.elmcloud.po.Orders;
import com.tju.elmcloud.service.OrdersService;
import com.tju.elmcloud.util.CommonUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class OrdersServiceImpl implements OrdersService {

    @Autowired
    private CartMapper cartMapper;
    @Autowired
    private OrdersMapper ordersMapper;
    @Autowired
    private OrderDetailetMapper orderDetailetMapper;

    @Override
    @Transactional
    public int createOrders(Orders orders) {

        //1、查询当前用户购物车中当前商家的所有食品
        Cart cart = new Cart();
        cart.setUserId(orders.getUserId());
        cart.setBusinessId(orders.getBusinessId());
        List<Cart> cartList = cartMapper.listCart(cart);

        //2、创建订单（返回生成的订单编号）
        orders.setOrderDate(CommonUtil.getCurrentDate());
        ordersMapper.saveOrders(orders);
        int orderId = orders.getOrderId();

        //3、批量添加订单明细
        List<OrderDetailet> list = new ArrayList<>();
        for(Cart c : cartList) {
            OrderDetailet od = new OrderDetailet();
            od.setOrderId(orderId);
            od.setFoodId(c.getFoodId());
            od.setQuantity(c.getQuantity());
            list.add(od);
        }
        if(!list.isEmpty()) {
            orderDetailetMapper.saveOrderDetailetBatch(list);
        }

        //4、从购物车表中删除相关食品信息
        cartMapper.removeCart(cart);

        //5、商家增加一条订单数
        ordersMapper.createOrders(orders);

        return orderId;
    }

    @Override
    public Orders getOrdersById(Integer orderID) {
        return ordersMapper.getOrdersById(orderID);
    }

    @Override
    public List<Orders> listOrdersByUserId(String userId) {
        return ordersMapper.listOrdersByUserId(userId);
    }
}
