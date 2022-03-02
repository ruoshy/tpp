package cn.cwc.api.entity;

import java.util.Date;

public class Arrange {

    private Integer id;

    private Integer cinemaFilmId;

    private Integer roomId;

    private Date time;

    private Double price;

    private Double discountPrice;

    private CinemaFilm cinemaFilm;

    private Room room;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getCinemaFilmId() {
        return cinemaFilmId;
    }

    public void setCinemaFilmId(Integer cinemaFilmId) {
        this.cinemaFilmId = cinemaFilmId;
    }

    public Integer getRoomId() {
        return roomId;
    }

    public void setRoomId(Integer roomId) {
        this.roomId = roomId;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Double getDiscountPrice() {
        return discountPrice;
    }

    public void setDiscountPrice(Double discountPrice) {
        this.discountPrice = discountPrice;
    }

    public CinemaFilm getCinemaFilm() {
        return cinemaFilm;
    }

    public void setCinemaFilm(CinemaFilm cinemaFilm) {
        this.cinemaFilm = cinemaFilm;
    }

    public Room getRoom() {
        return room;
    }

    public void setRoom(Room room) {
        this.room = room;
    }
}
