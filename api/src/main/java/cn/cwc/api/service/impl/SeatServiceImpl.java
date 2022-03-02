package cn.cwc.api.service.impl;

import cn.cwc.api.entity.Order;
import cn.cwc.api.entity.Seat;
import cn.cwc.api.mapper.OrderMapper;
import cn.cwc.api.mapper.SeatMapper;
import cn.cwc.api.service.SeatService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class SeatServiceImpl implements SeatService {

    @Resource
    private SeatMapper seatMapper;

    @Resource
    private OrderMapper orderMapper;

    @Override
    public int lock(List<Seat> seats) {
        return seatMapper.lock(seats);
    }

    @Override
    public boolean isOccupy(Integer arrangeId, List<Seat> dSeats) {
        List<Seat> seatList = getSeatList(arrangeId);
        for (Seat dseat : dSeats)
            for (Seat seat : seatList)
                if (dseat.getRow().equals(seat.getRow()) && dseat.getCol().equals(seat.getCol()))
                    return true;
        return false;
    }

    @Override
    public int generateOrder(Order order) {
        return orderMapper.insert(order);
    }

    @Override
    public List<Seat> getSeatList(Integer arrangeId) {
        return seatMapper.findAll(arrangeId);
    }

    @Override
    public Order getOrder(String orderId) {
        return orderMapper.findById(orderId);
    }

    @Override
    public int pay(Order order) {
        return orderMapper.update(order);
    }
}
