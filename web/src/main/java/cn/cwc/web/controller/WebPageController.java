package cn.cwc.web.controller;

import cn.cwc.web.entity.*;
import cn.cwc.web.model.PageInfo;
import cn.cwc.web.service.feign.BaseServiceFeign;
import cn.cwc.web.util.DateUtil;
import com.alibaba.fastjson.JSON;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.*;

@Controller
public class WebPageController {

    @Resource
    private BaseServiceFeign baseService;

    @RequestMapping({"/", "/index"})
    public String index(Map<String, Object> map) {
        PageInfo<Film> hotInfo = baseService.hotInfo(1, 5).getData();
        PageInfo<Film> comingSoonInfo = baseService.comingSoonInfo(1, 5).getData();
        PageInfo<Cinema> hotCinemaInfo = baseService.hotCinema(1, 6).getData();
        map.put("hotInfo", hotInfo);
        map.put("hotCinemaInfo", hotCinemaInfo);
        map.put("comingSoonInfo", comingSoonInfo);
        map.put("path", "index");
        return "web/index";
    }

    @RequestMapping("/films")
    public String films(Map<String, Object> map, @RequestParam(required = false, defaultValue = "1") int page) {
        PageInfo<Film> films = baseService.filmList(page, 10).getData();
        System.out.println(JSON.toJSONString(films));
        map.put("films", films);
        map.put("path", "films");
        map.put("page", page);
        return "web/films";
    }

    @RequestMapping("/filmDetail")
    public String filmDetail(Map<String, Object> map, @RequestParam("id") Integer filmId) {
        map.put("film", baseService.filmInfo(filmId).getData());
        Integer cityId = 1;
        map.put("regions", baseService.getRegionList(cityId).getData());
        map.put("cinemas", baseService.getCinemaList(cityId).getData());
        List<Date> dates = new ArrayList<>();
        Date date = new Date();
        dates.add(date);
        Calendar calendar = new GregorianCalendar();
        calendar.setTime(date);
        calendar.add(Calendar.DATE, 1);
        dates.add(calendar.getTime());
        map.put("dates", dates);
        map.put("tomorrow", DateUtil.getDayForWeek(calendar));
        return "web/filmDetail";
    }

    @RequestMapping("/seatOrder")
    public String seatOrder(HttpSession session, Map<String, Object> map, Integer arrangeId) {
        Arrange arrange = baseService.getArrange(arrangeId).getData();
        Calendar calendar = new GregorianCalendar();
        calendar.setTime(arrange.getTime());
        map.put("dayForWeek", DateUtil.getDayForWeek(calendar));
        map.put("arrange", arrange);
        map.put("userId", ((User) session.getAttribute("user")).getId());
        return "web/seatOrder";
    }

    @RequestMapping("/payOrder")
    public String payOrder(Map<String, Object> map, String orderId) {
        Order order = baseService.getOrder(orderId).getData();
        map.put("order", order);
        List<Seat> seats = order.getSeats();
        if (seats != null) {
            Integer arrangeId = seats.get(0).getArrangeId();
            Arrange arrange = baseService.getArrange(arrangeId).getData();
            map.put("arrange", arrange);
            System.out.println(JSON.toJSONString(arrange));
            Calendar calendar = new GregorianCalendar();
            calendar.setTime(arrange.getTime());
            map.put("dayForWeek", DateUtil.getDayForWeek(calendar));
        }
        return "web/payOrder";
    }

    @RequestMapping("/paySuccess")
    public String paySuccess(Map<String, Object> map, String orderId) {
        map.put("order", baseService.getOrder(orderId).getData());
        return "web/paySuccess";
    }
}
