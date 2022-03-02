package cn.cwc.api.service.impl;

import cn.cwc.api.entity.*;
import cn.cwc.api.mapper.*;
import cn.cwc.api.model.Action;
import cn.cwc.api.model.Result;
import cn.cwc.api.service.BaseService;
import com.alibaba.fastjson.JSON;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Service
public class BaseServiceImpl implements BaseService {

    @Resource
    private CityMapper cityMapper;
    @Resource
    private RegionMapper regionMapper;
    @Resource
    private CinemaFilmMapper cinemaFilmMapper;
    @Resource
    private CinemaMapper cinemaMapper;
    @Resource
    private FilmMapper filmMapper;

    public List<String> getBannerList() {
        List<String> list = new ArrayList<>();
        list.add("banner.jpeg");
        return list;
    }

    @Override
    public List<City> getCityList() {
        return cityMapper.findAll();
    }

    @Override
    public List<Region> getRegionList(Integer cityId) {
        return regionMapper.findByCityId(cityId);
    }

    @Override
    public List<CinemaFilm> getCinemaFilmList(Cinema cinema) {
        return cinemaFilmMapper.findByCinema(cinema);
    }

    @Override
    public List<Cinema> getCinemaList(Region region) {
        return cinemaMapper.findByRegion(region);
    }

    @Override
    public List<Film> getFilmList(Film film) {
        System.out.println(JSON.toJSONString(film));
        return filmMapper.findAll(film);
    }

    @Override
    public Film getFilm(Integer filmId) {
        return filmMapper.findById(filmId);
    }

    @Override
    public Cinema getCinema(Integer cinemaId) {
        return cinemaMapper.findById(cinemaId);
    }
}
