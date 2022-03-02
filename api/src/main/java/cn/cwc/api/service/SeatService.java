package cn.cwc.api.service;

import cn.cwc.api.entity.Order;
import cn.cwc.api.entity.Seat;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface SeatService {

    int lock(List<Seat> seats);

    boolean isOccupy(Integer arrangeId, List<Seat> seats);

    int generateOrder(Order order);

    List<Seat> getSeatList(Integer arrangeId);

    Order getOrder(String orderId);

    int pay(Order order);
}
