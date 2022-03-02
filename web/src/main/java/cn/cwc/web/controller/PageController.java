package cn.cwc.web.controller;

import cn.cwc.web.entity.Cinema;
import cn.cwc.web.entity.Film;
import cn.cwc.web.entity.User;
import cn.cwc.web.model.Menu;
import cn.cwc.web.model.PageInfo;
import cn.cwc.web.model.Result;
import cn.cwc.web.service.feign.BaseServiceFeign;
import com.alibaba.fastjson.JSON;
import org.apache.http.HttpResponse;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Controller
public class PageController {

    @Resource
    private BaseServiceFeign baseService;

    @RequestMapping("/login")
    public String login(Map<String, Object> map) {
        return "web/login";
    }

    @RequestMapping(value = "/verify", method = RequestMethod.POST)
    public String verify(HttpSession session, User user) {
        System.out.println(JSON.toJSONString(user));
        Result<User> result = baseService.login(user.getPhone(), user.getPassword());
        System.out.println(JSON.toJSONString(result));
        if (result.getStatus() == 200) {
            session.setAttribute("user", result.getData());
            return "redirect:/";
        }
        return "redirect:/login";
    }

    @RequestMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/";
    }

    private List<Menu> menuList;

    public void adminInit(Map<String, Object> map) {
        if (menuList == null) {
            menuList = new ArrayList<>();
            menuList.add(new Menu("首页", "icon-shouye", "index"));
            menuList.add(new Menu("影片", "icon-moviesel", "film"));
            menuList.add(new Menu("影院", "icon-yingyuan", "cinema"));
            menuList.add(new Menu("区域", "icon-city", "area"));
        }
        map.put("role", "admin");
        map.put("menus", menuList);
    }

    @RequestMapping("/admin/index")
    public String adminIndex(Map<String, Object> map) {
        adminInit(map);
        map.put("path", "index");
        return "admin/index";
    }

    @RequestMapping("/admin/film")
    public String adminFilm(Map<String, Object> map,
                            @RequestParam(defaultValue = "1") Integer page,
                            @RequestParam(name = "name", required = false) String filmName,
                            @RequestParam(name = "status", required = false) boolean status) {
        adminInit(map);
        map.put("path", "film");
        map.put("headers", new String[]{"#", "影片名称", "导演", "主演", "类型", "地区", "语言", "时长", "介绍", "上映日期", "操作"});
        if (filmName != null) {
            map.put("filmList", baseService.getFilmList(filmName, status).getData());
            PageInfo pageInfo = new PageInfo();
//            pageInfo.setCount(cinemaList.size());
            pageInfo.setCount(1);
            pageInfo.setPage(page);
            pageInfo.setSize(10);
            pageInfo.setCurrent(page);
            map.put("pageInfo", pageInfo);
        }
        return "admin/film";
    }

    @RequestMapping("/admin/cinema")
    public String adminCinema(Map<String, Object> map,
                              @RequestParam(defaultValue = "1") Integer page,
                              @RequestParam(value = "city_id", required = false) Integer cityId,
                              @RequestParam(value = "region_id", required = false) Integer regionId) {
        adminInit(map);
        map.put("path", "cinema");
        map.put("citys", baseService.getCityList().getData());
        map.put("headers", new String[]{"影院名称", "详细地址", "联系号码", "经纬度", "操作"});
        if (cityId != null) {
            List<Cinema> cinemaList;
            map.put("cityId", cityId);
            if (regionId != null) {
                map.put("regionId", regionId);
                map.put("regions", baseService.getRegionList(cityId).getData());
                cinemaList = baseService.getCinemaList(cityId, regionId).getData();
            } else {
                cinemaList = baseService.getCinemaList(cityId).getData();
            }
            PageInfo pageInfo = new PageInfo();
            pageInfo.setCount(cinemaList.size());
            pageInfo.setPage(page);
            pageInfo.setSize(10);
            pageInfo.setCurrent(page);
            map.put("pageInfo", pageInfo);
            map.put("cinemaList", cinemaList);
        }
        return "admin/cinema";
    }

    @RequestMapping("/admin/area")
    public String adminArea(Map<String, Object> map) {
        adminInit(map);
        map.put("path", "area");
        return "admin/area";
    }

    private List<Menu> cinemaMenuList;
    private Integer cinemaId = 1;

    public void cinemaInit(Map<String, Object> map) {
        if (cinemaMenuList == null) {
            cinemaMenuList = new ArrayList<>();
            cinemaMenuList.add(new Menu("首页", "icon-shouye", "index"));
            cinemaMenuList.add(new Menu("影片", "icon-moviesel", "film"));
            cinemaMenuList.add(new Menu("排期", "icon-yingyuan", "arrange"));
            cinemaMenuList.add(new Menu("影厅", "icon-city", "room"));
        }
        map.put("role", "cinema");
        map.put("menus", cinemaMenuList);
    }

    @RequestMapping("/cinema/index")
    public String cinemaIndex(Map<String, Object> map) {
        cinemaInit(map);
        map.put("path", "index");
        return "cinema/index";
    }

    @RequestMapping("/cinema/film")
    public String cinemaFilm(Map<String, Object> map,
                             @RequestParam(defaultValue = "1") Integer page,
                             @RequestParam(name = "name", required = false) String filmName,
                             @RequestParam(name = "select", required = false, defaultValue = "0") boolean select) {
        cinemaInit(map);
        map.put("path", "film");
        map.put("headers", new String
                []{"#", "影片名称", "导演", "主演", "类型", "地区", "语言", "时长", "介绍", "上映日期", "操作"});
        if (filmName != null) {
            List<Film> films;
            map.put("filmList", baseService.getFilmListByCinemaId(filmName, cinemaId, select).getData());
            map.put("select", select);
            PageInfo pageInfo = new PageInfo();
//            pageInfo.setCount(cinemaList.size());
            pageInfo.setCount(1);
            pageInfo.setPage(page);
            pageInfo.setSize(10);
            pageInfo.setCurrent(page);
            map.put("pageInfo", pageInfo);
        }
        return "cinema/film";
    }

    @RequestMapping("/cinema/arrange")
    public String cinemaArrange(Map<String, Object> map) {
        cinemaInit(map);
        map.put("path", "arrange");

        map.put("film_headers", new String[]{"#", "影片名称"});
        map.put("arrange_headers", new String[]{"#", "影厅", "价格", "折扣", "操作"});
        map.put("filmList", baseService.getFilmListByCinemaId(null, cinemaId, true).getData());
        return "cinema/arrange";
    }

    @RequestMapping("/cinema/room")
    public String cinemaRoom(Map<String, Object> map) {
        cinemaInit(map);
        map.put("path", "room");
        map.put("room_headers", new String[]{"#", "名称", "座位数", "银幕类型", "详细", "操作"});
        map.put("rooms", baseService.getRoomList(cinemaId).getData());
        return "cinema/room";
    }
}
