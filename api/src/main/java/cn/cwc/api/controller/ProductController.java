package cn.cwc.api.controller;

import cn.cwc.api.entity.Cinema;
import cn.cwc.api.entity.Film;
import cn.cwc.api.model.Action;
import cn.cwc.api.model.Result;
import cn.cwc.api.service.ProductService;
import com.alibaba.fastjson.JSON;
import com.github.pagehelper.PageInfo;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

@RestController
@RequestMapping("/w")
public class ProductController {

    @Resource
    private ProductService productService;

    @RequestMapping("/hotinfo")
    public Result<PageInfo<Film>> hotInfo(int page, int size) {
        List<Film> films = productService.hotInfo(page, size);
        return new Result<>(new PageInfo<>(films), Action.OK);
    }

    @RequestMapping("/comingSoonInfo")
    public Result<PageInfo<Film>> comingSoonInfo(int page, int size) {
        List<Film> films = productService.comingSoonInfo(page, size);
        return new Result<>(new PageInfo<>(films), Action.OK);
    }

    @RequestMapping("/film/list")
    public Result<PageInfo<Film>> filmList(int page, int size) {
        List<Film> films = productService.filmList(page, size);
        return new Result<>(new PageInfo<>(films), Action.OK);
    }

    @RequestMapping("/hotCinema")
    public Result<PageInfo<Cinema>> hotCinema(int page, int size) {
        Date end = new Date();
        Calendar calendar = new GregorianCalendar();
        calendar.setTime(end);
        calendar.add(Calendar.DATE, -7);
        List<Cinema> cinemas = productService.hotCinemaInfo(calendar.getTime(), end, page, size);
        return new Result<>(new PageInfo<>(cinemas), Action.OK);
    }
}
