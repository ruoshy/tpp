package cn.cwc.api.entity;

import java.util.List;

public class CinemaFilm {

    private Integer id;

    private Integer filmId;

    private Integer cinemaId;

    private Film film;

    private Cinema cinema;

    private List<Arrange> arrangeList;

    public CinemaFilm() {
    }

    public CinemaFilm(Integer cinemaId, Integer filmId) {
        this.cinemaId = cinemaId;
        this.filmId = filmId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getFilmId() {
        return filmId;
    }

    public void setFilmId(Integer filmId) {
        this.filmId = filmId;
    }

    public Integer getCinemaId() {
        return cinemaId;
    }

    public void setCinemaId(Integer cinemaId) {
        this.cinemaId = cinemaId;
    }

    public Film getFilm() {
        return film;
    }

    public void setFilm(Film film) {
        this.film = film;
    }

    public Cinema getCinema() {
        return cinema;
    }

    public void setCinema(Cinema cinema) {
        this.cinema = cinema;
    }

    public List<Arrange> getArrangeList() {
        return arrangeList;
    }

    public void setArrangeList(List<Arrange> arrangeList) {
        this.arrangeList = arrangeList;
    }

}
