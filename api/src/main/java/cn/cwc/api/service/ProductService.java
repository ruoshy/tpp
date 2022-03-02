package cn.cwc.api.service;

import cn.cwc.api.entity.*;
import cn.cwc.api.model.Seat;

import java.util.Date;
import java.util.List;

public interface ProductService {

    /**
     * 正在热映
     *
     * @param size 容量
     * @return
     */
    List<Film> hotInfo(Integer page, Integer size);

    /**
     * 即将上映
     *
     * @param size 容量
     * @return
     */
    List<Film> comingSoonInfo(Integer page, Integer size);

    List<Film> filmList(Integer page, Integer size);

    /**
     * 票房排行
     *
     * @param start 起始日期
     * @param end   结束日期
     * @param size  容量
     * @return
     */
    List<Film> rankInfo(Date start, Date end, Integer page, Integer size);

    /**
     * 热门影院排行
     *
     * @param start 骑士日期
     * @param end   结束日期
     * @param size  容量
     * @return
     */
    List<Cinema> hotCinemaInfo(Date start, Date end, Integer page, Integer size);

    /**
     * 热门预告片排行
     *
     * @param start 起始日期
     * @param end   结束日期
     * @param size  容量
     * @return
     */
    List<Broadcast> hotTrailerInfo(Date start, Date end, Integer page, Integer size);

    /**
     * 影片排期
     *
     * @param cinemaId 影院id
     * @param date     日期
     * @return
     */
    List<Arrange> arrangeInfo(Integer cinemaId, Date date, Integer page, Integer size);

    /**
     * 订单信息
     *
     * @param userId 用户信息
     * @return
     */
    List<Order> orderInfo(Integer userId);

    /**
     * 处理订单
     *
     * @param orders 订单信息
     * @return
     */
    List<Order> handleOrder(List<Order> orders);

    /**
     * 订单信息
     *
     * @return
     */
    Order getOrder(Integer orderId);

    /**
     * 影片排期影厅座位信息
     *
     * @param arrange 排期信息
     * @return
     */
    List<Seat> seatInfo(Arrange arrange);

    /**
     * 支付订单
     *
     * @param orders 订单信息
     * @param user   用户信息
     * @return
     */
    boolean payOrder(List<Order> orders, User user);


}
