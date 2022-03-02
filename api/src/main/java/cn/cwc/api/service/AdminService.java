package cn.cwc.api.service;

import cn.cwc.api.entity.*;

import java.util.List;

public interface AdminService {

    /**
     * 添加影院信息
     *
     * @param cinema
     */
    void addCinema(Cinema cinema);

    /**
     * 更新影院信息
     *
     * @param cinema
     */
    void updateCinema(Cinema cinema);

    /**
     * 添加城市
     *
     * @param cities
     */
    void addCity(List<City> cities);

    /**
     * 添加地区
     *
     * @param regions
     */
    void addRegion(List<Region> regions);

    /**
     * 获取影片信息
     *
     * @return
     */
    List<Film> getFilmList(int page, int size);

    /**
     * 添加影片信息
     *
     * @param film
     */
    void addFilm(Film film);

    /**
     * 更新影片信息
     */
    void updateFilm(Film film);

    /**
     * 获取影片相关海报信息
     *
     * @param film
     */
    List<Broadcast> getBroadcastList(Film film);

    /**
     * 添加海报信息
     *
     * @param broadcasts
     */
    void addBroadcast(List<Broadcast> broadcasts);

    /**
     * 删除海报信息
     *
     * @param broadcast
     */
    void deleteBroadcast(Broadcast broadcast);
}
