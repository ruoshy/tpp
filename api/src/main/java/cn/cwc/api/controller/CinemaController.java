package cn.cwc.api.controller;

import cn.cwc.api.entity.*;
import cn.cwc.api.model.Action;
import cn.cwc.api.model.Result;
import cn.cwc.api.service.BaseService;
import cn.cwc.api.service.CinemaService;
import cn.cwc.api.service.SeatService;
import com.alibaba.fastjson.JSON;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/cinema")
public class CinemaController {

    @Resource
    private CinemaService cinemaService;

    @Resource
    private SeatService seatService;

    @RequestMapping(value = "/film/add", method = RequestMethod.POST)
    public Result<String> addFilm(@RequestParam("cid") Integer cinemaId,
                                  @RequestParam("fid") Integer filmId) {
        cinemaService.addFilm(cinemaId, filmId);
        return new Result<>(null, Action.OK);
    }

    @RequestMapping("/film/list")
    public Result<List<Film>> getFilmList(Cinema cinema,
                                          @RequestParam(required = false) Boolean select) {
        return new Result<>(cinemaService.findByCinema(cinema, select), Action.OK);
    }

    @RequestMapping("/room/list")
    public Result<List<Room>> getRoomList(Cinema cinema) {
        return new Result<>(cinemaService.getRoomList(cinema), Action.OK);
    }

    @RequestMapping(value = "/room/add", method = RequestMethod.POST)
    public Result<String> addRoom(Room room) {
        cinemaService.addRoom(room);
        return new Result<>(null, Action.OK);
    }

    @RequestMapping(value = "/room/update", method = RequestMethod.POST)
    public Result<String> updateRoom(Room room) {
        cinemaService.updateRoom(room);
        return new Result<>(null, Action.OK);
    }

    @RequestMapping("/arrange")
    public Result<Arrange> getArrange(Arrange arrange) {
        return new Result<>(cinemaService.getArrange(arrange.getId()), Action.OK);
    }

    @RequestMapping("/arrange/list")
    public Result<List<Arrange>> cinemaFilmArrange(Integer filmId, Integer cinemaId, long date) {
        Arrange arrange = new Arrange();
        arrange.setCinemaFilm(new CinemaFilm(cinemaId, filmId));
        arrange.setTime(new Date(date));
        return new Result<>(cinemaService.getArrangeList(arrange), Action.OK);
    }

    @RequestMapping(value = "/seat/lock", method = RequestMethod.POST)
    public Result<String> seatLock(@RequestBody List<Seat> seats) {
        System.out.println(JSON.toJSONString(seats));
        Action action;
        String orderId = null;
        if (seats.size() == 0 || seatService.isOccupy(seats.get(0).getArrangeId(), seats)) {
            action = Action.FAIL;
        } else {
            Integer arrangeId = seats.get(0).getArrangeId();
            Integer userId = seats.get(0).getOrder().getUserId();
            Arrange arrange = cinemaService.getArrange(arrangeId);
            Date date = new Date();
            orderId = new SimpleDateFormat("yyyyMMddHHmmss").format(date) + userId;
            Order order = new Order();
            order.setUserId(userId);
            order.setTime(date);
            order.setStatus(false);
            order.setId(orderId);
            order.setMoney(arrange.getDiscountPrice() * seats.size());
            seatService.generateOrder(order);
            for (Seat seat : seats)
                seat.setOrderId(orderId);
            seatService.lock(seats);
            action = Action.OK;
        }
        return new Result<>(orderId, action);
    }

    @RequestMapping("/seat/list")
    public Result<List<Seat>> seatLock(Integer arrangeId) {
        return new Result<>(seatService.getSeatList(arrangeId), Action.OK);
    }

    @RequestMapping("/order")
    public Result<Order> getOrder(String orderId) {
        return new Result<>(seatService.getOrder(orderId), Action.OK);
    }

    @RequestMapping(value = "/pay", method = RequestMethod.POST)
    public Result<String> pay(Order order) {
        order.setStatus(true);
        System.out.println(JSON.toJSONString(order));
        seatService.pay(order);
        return new Result<>(null, Action.OK);
    }

    @RequestMapping("/cinema_film/list")
    public Result<List<Cinema>> cinemaFilm(Film film) {
        return new Result<>(cinemaService.getCinemaList(film), Action.OK);
    }
}

