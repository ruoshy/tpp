package cn.cwc.api.entity;

import java.util.List;

public class Cinema {

    private Integer id;

    private String name;

    private String address;

    private String phone;

    private Integer regionId;

    private String lal;

    private Region region;

    private List<Room> roomList;

    private List<CinemaFilm> cinemaFilmList;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Integer getRegionId() {
        return regionId;
    }

    public void setRegionId(Integer regionId) {
        this.regionId = regionId;
    }

    public String getLal() {
        return lal;
    }

    public void setLal(String lal) {
        this.lal = lal;
    }

    public Region getRegion() {
        return region;
    }

    public void setRegion(Region region) {
        this.region = region;
    }

    public List<Room> getRoomList() {
        return roomList;
    }

    public void setRoomList(List<Room> roomList) {
        this.roomList = roomList;
    }

    public List<CinemaFilm> getCinemaFilmList() {
        return cinemaFilmList;
    }

    public void setCinemaFilmList(List<CinemaFilm> cinemaFilmList) {
        this.cinemaFilmList = cinemaFilmList;
    }
}
