package cn.cwc.api.service.impl;

import cn.cwc.api.entity.*;
import cn.cwc.api.mapper.BroadcastMapper;
import cn.cwc.api.mapper.CinemaMapper;
import cn.cwc.api.mapper.FilmMapper;
import cn.cwc.api.model.Seat;
import cn.cwc.api.service.ProductService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {

    @Resource
    private FilmMapper filmMapper;
    @Resource
    private BroadcastMapper broadcastMapper;
    @Resource
    private CinemaMapper cinemaMapper;

    @Override
    public List<Film> hotInfo(Integer page, Integer size) {
        PageHelper.startPage(page, size);
        Film film = new Film();
        film.setStatus(true);
        film.setReleaseDate(new Date());
        return filmMapper.findAll(film);
    }

    @Override
    public List<Film> comingSoonInfo(Integer page, Integer size) {
        PageHelper.startPage(page, size);
        Film film = new Film();
        film.setStatus(false);
        film.setReleaseDate(new Date());
        return filmMapper.findAll(film);
    }

    @Override
    public List<Film> filmList(Integer page, Integer size) {
        PageHelper.startPage(page, size);
        return filmMapper.findAll(null);
    }

    @Override
    public List<Film> rankInfo(Date start, Date end, Integer page, Integer size) {

        return null;
    }

    @Override
    public List<Cinema> hotCinemaInfo(Date start, Date end, Integer page, Integer size) {
        PageHelper.startPage(page, size);
        return cinemaMapper.findByRegion(null);
    }

    @Override
    public List<Broadcast> hotTrailerInfo(Date start, Date end, Integer page, Integer size) {
        return null;
    }

    @Override
    public List<Arrange> arrangeInfo(Integer cinemaId, Date date, Integer page, Integer size) {
        return null;
    }

    @Override
    public List<Order> orderInfo(Integer userId) {
        return null;
    }

    @Override
    public List<Order> handleOrder(List<Order> orders) {
        return null;
    }

    @Override
    public Order getOrder(Integer orderId) {
        return null;
    }

    @Override
    public List<Seat> seatInfo(Arrange arrange) {
        return null;
    }

    @Override
    public boolean payOrder(List<Order> orders, User user) {
        return false;
    }


}
