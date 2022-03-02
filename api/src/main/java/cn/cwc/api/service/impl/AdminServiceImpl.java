package cn.cwc.api.service.impl;

import cn.cwc.api.entity.*;
import cn.cwc.api.mapper.*;
import cn.cwc.api.service.AdminService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class AdminServiceImpl implements AdminService {

    @Resource
    private CinemaMapper cinemaMapper;
    @Resource
    private FilmMapper filmMapper;
    @Resource
    private BroadcastMapper broadcastMapper;
    @Resource
    private CityMapper cityMapper;
    @Resource
    private RegionMapper regionMapper;

    @Override
    public void addCinema(Cinema cinema) {
        cinemaMapper.insert(cinema);
    }

    @Override
    public void updateCinema(Cinema cinema) {
        cinemaMapper.update(cinema);
    }

    @Override
    public void addCity(List<City> cities) {
        cityMapper.insert(cities);
    }

    @Override
    public void addRegion(List<Region> regions) {
        regionMapper.insert(regions);
    }

    @Override
    public List<Film> getFilmList(int page, int size) {
        return filmMapper.findAll(null);
    }

    @Override
    public void addFilm(Film film) {
        filmMapper.insert(film);
    }

    @Override
    public void updateFilm(Film film) {
        filmMapper.update(film);
    }

    @Override
    public List<Broadcast> getBroadcastList(Film film) {
        return broadcastMapper.findByFilmId(film.getId());
    }

    @Override
    public void addBroadcast(List<Broadcast> broadcasts) {
        broadcastMapper.insert(broadcasts);
    }

    @Override
    public void deleteBroadcast(Broadcast broadcast) {
        broadcastMapper.delete(broadcast.getId());
    }
}
