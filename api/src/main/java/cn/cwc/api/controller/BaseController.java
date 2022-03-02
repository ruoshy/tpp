package cn.cwc.api.controller;

import cn.cwc.api.entity.*;
import cn.cwc.api.model.Action;
import cn.cwc.api.model.Result;
import cn.cwc.api.service.BaseService;
import com.alibaba.fastjson.JSON;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RestController
public class BaseController {

    @Resource
    private BaseService baseService;

    @RequestMapping("/banner/list")
    public Result<List<String>> bannerInfo() {
        return new Result<>(baseService.getBannerList(), Action.OK);
    }

    /**
     * 城市信息
     *
     * @return
     */
    @RequestMapping("/city/list")
    public Result<List<City>> cityInfo() {
        List<City> cityList = baseService.getCityList();
        return new Result<>(cityList, Action.OK);
    }

    /**
     * 地区信息
     *
     * @param cityId
     * @return
     */
    @RequestMapping("/region/list")
    public Result<List<Region>> regionInfo(@RequestParam("id") Integer cityId) {
        List<Region> regionList = baseService.getRegionList(cityId);
        return new Result<>(regionList, Action.OK);
    }

    /**
     * 影院开放电影信息
     *
     * @param cinema
     * @return
     */
    @RequestMapping("/cinema/film")
    public Result<List<CinemaFilm>> cinemaFilmInfo(Cinema cinema) {
        List<CinemaFilm> regionList = baseService.getCinemaFilmList(cinema);
        return new Result<>(regionList, Action.OK);
    }

    /**
     * 影院信息
     *
     * @param city
     * @return
     */
    @RequestMapping("/cinema/list")
    public Result<List<Cinema>> cinemaInfo(Region region) {
        System.out.println(JSON.toJSONString(region));
        List<Cinema> cinemaList = baseService.getCinemaList(region);
        return new Result<>(cinemaList, Action.OK);
    }

    /**
     * 影片信息
     *
     * @param film
     * @return
     */
    @RequestMapping("/film/list")
    public Result<List<Film>> filmInfo(Film film) {
        List<Film> filmList = baseService.getFilmList(film);
        return new Result<>(filmList, Action.OK);
    }

    @RequestMapping("/film")
    public Result<Film> filmInfo(@RequestParam("id") Integer filmId) {
        return new Result<>(baseService.getFilm(filmId), Action.OK);
    }

    @RequestMapping("/cinema")
    public Result<Cinema> cinemaInfo(@RequestParam("id") Integer cinemaId) {
        return new Result<>(baseService.getCinema(cinemaId), Action.OK);
    }


}
