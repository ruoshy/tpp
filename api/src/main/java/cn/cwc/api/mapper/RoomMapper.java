package cn.cwc.api.mapper;

import cn.cwc.api.entity.Cinema;
import cn.cwc.api.entity.CinemaFilm;
import cn.cwc.api.entity.Room;

import java.util.List;

public interface RoomMapper {

    Room findById(Integer roomId);

    List<Room> findByCinema(Cinema cinema);

    List<Room> findByCinemaFilm(CinemaFilm cinemaFilm);

    int insert(Room room);

    int update(Room room);
}
