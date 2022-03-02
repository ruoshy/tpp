package cn.cwc.web.service.feign;

import cn.cwc.web.entity.*;
import cn.cwc.web.model.PageInfo;
import cn.cwc.web.model.Result;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(value = "api")
public interface BaseServiceFeign {

    /**
     * 城市信息
     *
     * @return
     */
    @RequestMapping("/city/list")
    Result<List<City>> getCityList();

    /**
     * 地区信息
     *
     * @param cityId
     * @return
     */
    @RequestMapping("/region/list")
    Result<List<Region>> getRegionList(@RequestParam("id") Integer cityId);

    /**
     * 影院开放电影信息
     *
     * @param cinemaId
     * @return
     */
    @RequestMapping("/cinema/film")
    Result<List<CinemaFilm>> getCinemaFilmList(@RequestParam("id") Integer cinemaId);

    /**
     * 影院信息
     *
     * @param cityId
     * @return
     */
    @RequestMapping("/cinema/list")
    Result<List<Cinema>> getCinemaList(@RequestParam("cityId") Integer cityId);

    @RequestMapping("/cinema/list")
    Result<List<Cinema>> getCinemaList(@RequestParam("cityId") Integer cityId, @RequestParam("id") Integer regionId);

    /**
     * 影片信息
     *
     * @param name
     * @return
     */
    @RequestMapping("/film/list")
    Result<List<Film>> getFilmList(@RequestParam("name") String name, @RequestParam("status") Boolean status);

    @RequestMapping("/film")
    Result<Film> filmInfo(@RequestParam("id") Integer filmId);

    @RequestMapping("/cinema")
    Result<Cinema> cinemaInfo(@RequestParam("id") Integer cinemaId);

    // web
    @RequestMapping("/w/hotinfo")
    Result<PageInfo<Film>> hotInfo(@RequestParam int page, @RequestParam int size);

    @RequestMapping("/w/comingSoonInfo")
    Result<PageInfo<Film>> comingSoonInfo(@RequestParam int page, @RequestParam int size);

    // cinema
    @RequestMapping("/cinema/film/list")
    Result<List<Film>> getFilmListByCinemaId(@RequestParam("name") String name, @RequestParam("id") Integer cinemaId, @RequestParam("select") Boolean select);

    @RequestMapping("/cinema/room/list")
    Result<List<Room>> getRoomList(@RequestParam("id") Integer cinemaId);

    @RequestMapping("/cinema/arrange")
    Result<Arrange> getArrange(@RequestParam("id") Integer arrangeId);

    @RequestMapping("/cinema/order")
    Result<Order> getOrder(@RequestParam("orderId") String orderId);

    @RequestMapping("/login")
    Result<User> login(@RequestParam("phone") String phone, @RequestParam("password") String password);

    @RequestMapping("/w/film/list")
    Result<PageInfo<Film>> filmList(@RequestParam("page") int page, @RequestParam("size") int size);

    @RequestMapping("/w/hotCinema")
    Result<PageInfo<Cinema>> hotCinema(@RequestParam("page") int page, @RequestParam("size") int size);
}
