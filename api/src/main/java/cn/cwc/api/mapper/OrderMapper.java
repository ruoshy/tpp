package cn.cwc.api.mapper;

import cn.cwc.api.entity.Cinema;
import cn.cwc.api.entity.Order;
import cn.cwc.api.entity.User;

import java.util.List;

public interface OrderMapper {

    Order findById(String orderId);

    List<Order> findByUser(User user);

    List<Order> findByCinema(Cinema cinema);

    int insert(Order order);

    int update(Order order);
}
