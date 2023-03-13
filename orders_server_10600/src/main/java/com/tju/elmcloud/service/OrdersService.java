package com.tju.elmcloud.service;

import com.tju.elmcloud.po.Orders;

import java.util.List;

public interface OrdersService {

    public int createOrders(Orders orders);

    public Orders getOrdersById(Integer orderID);

    public List<Orders> listOrdersByUserId(String userId);
}
