package cn.cwc.api.mapper;

import cn.cwc.api.entity.Seat;

import java.util.List;

public interface SeatMapper {

    int lock(List<Seat> seats);

    List<Seat> findAll(Integer arrangeId);
}
