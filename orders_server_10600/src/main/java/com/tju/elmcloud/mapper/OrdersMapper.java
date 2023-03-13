package com.tju.elmcloud.mapper;

import com.tju.elmcloud.po.Orders;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface OrdersMapper {
    public void saveOrders(Orders orders);

    public int createOrders(Orders orders);

    public Orders getOrdersById(Integer orderID);

    public List<Orders> listOrdersByUserId(String userId);
}
