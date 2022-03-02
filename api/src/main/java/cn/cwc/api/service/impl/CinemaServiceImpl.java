package cn.cwc.api.service.impl;

import cn.cwc.api.entity.*;
import cn.cwc.api.mapper.*;
import cn.cwc.api.service.CinemaService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class CinemaServiceImpl implements CinemaService {

    @Resource
    private CinemaFilmMapper cinemaFilmMapper;
    @Resource
    private ArrangeMapper arrangeMapper;
    @Resource
    private RoomMapper roomMapper;
    @Resource
    private FilmMapper filmMapper;
    @Resource
    private CinemaMapper cinemaMapper;

    @Override
    public void addFilm(Integer cinemaId, Integer filmId) {
        cinemaFilmMapper.insert(new CinemaFilm(cinemaId, filmId));
    }

    @Override
    public void addArrange(Arrange arrange) {
        arrangeMapper.insert(arrange);
    }

    @Override
    public void updateArrange(Arrange arrange) {
        arrangeMapper.update(arrange);
    }

    @Override
    public void addRoom(Room room) {
        roomMapper.insert(room);
    }

    @Override
    public void updateRoom(Room room) {
        roomMapper.update(room);
    }

    @Override
    public List<Film> findByCinema(Cinema cinema, Boolean select) {
        return filmMapper.findByCinema(cinema, select);
    }

    @Override
    public List<Room> getRoomList(Cinema cinema) {
        return roomMapper.findByCinema(cinema);
    }

    @Override
    public List<Arrange> getArrangeList(Arrange arrange) {
        return arrangeMapper.findAll(arrange);
    }

    @Override
    public Arrange getArrange(Integer arrangeId) {
        return arrangeMapper.findById(arrangeId);
    }

    @Override
    public List<Cinema> getCinemaList(Film film) {
        return cinemaMapper.findAllByFilm(film);
    }
}
