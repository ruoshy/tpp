package cn.cwc.api.service;

import cn.cwc.api.entity.*;
import cn.cwc.api.model.Result;

import java.util.List;

public interface BaseService {


    List<String> getBannerList();
    /**
     * get city list
     *
     * @return city list
     */
    List<City> getCityList();

    /**
     * find region by cityId
     *
     * @param cityId city_id
     * @return region list
     */
    List<Region> getRegionList(Integer cityId);


    List<CinemaFilm> getCinemaFilmList(Cinema cinema);

    /**
     * 根据区域获取影院信息
     *
     * @param city
     * @return
     */
    List<Cinema> getCinemaList(Region region);

    /**
     * 根据条件查找影片信息
     *
     * @param film
     * @return
     */
    List<Film> getFilmList(Film film);

    /**
     * 获取影片信息
     *
     * @param filmId 影片id
     * @return
     */
    Film getFilm(Integer filmId);

    /**
     * 获取影院信息
     *
     * @param cinemaId 影院id
     * @return
     */
    Cinema getCinema(Integer cinemaId);
}
