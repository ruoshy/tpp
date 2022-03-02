package cn.cwc.api.controller;

import cn.cwc.api.entity.*;
import cn.cwc.api.service.AdminService;
import com.alibaba.fastjson.JSON;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/admin")
public class AdminController {

    @Resource
    private AdminService adminService;

    @RequestMapping(value = "/cinema/add", method = RequestMethod.POST)
    public void addCinema(Cinema cinema) {
        adminService.addCinema(cinema);
    }

    @RequestMapping(value = "/cinema/update", method = RequestMethod.POST)
    public void updateCinema(Cinema cinema) {
        System.out.println(JSON.toJSONString(cinema));
        adminService.updateCinema(cinema);
    }

    @RequestMapping(value = "/city/add", method = RequestMethod.POST)
    public void addCity(List<City> citys) {
        adminService.addCity(citys);
    }

    @RequestMapping(value = "/region/add", method = RequestMethod.POST)
    public void addRegion(List<Region> regions) {
        adminService.addRegion(regions);
    }

    @RequestMapping(value = "/film/add", method = RequestMethod.POST)
    public void addFilm(Film film) {
        System.out.println(JSON.toJSONString(film));
        adminService.addFilm(film);
    }

    @RequestMapping(value = "/film/update", method = RequestMethod.POST)
    public void updateFilm(Film film) {
        System.out.println(JSON.toJSONString(film));
        adminService.updateFilm(film);
    }

    @RequestMapping(value = "/broadcast/add", method = RequestMethod.POST)
    public void addBroadcast(List<Broadcast> broadcasts) {
        adminService.addBroadcast(broadcasts);
    }

    @RequestMapping(value = "/broadcast/delete", method = RequestMethod.POST)
    public void deleteBroadcast(Broadcast broadcast) {
        adminService.deleteBroadcast(broadcast);
    }
}
