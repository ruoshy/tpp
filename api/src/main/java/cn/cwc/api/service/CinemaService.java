package cn.cwc.api.service;

import cn.cwc.api.entity.*;

import java.util.List;

public interface CinemaService {

    /**
     * 上线影片
     *
     * @param film
     */
    void addFilm(Integer cinemaId, Integer filmId);

    /**
     * 添加影片放映安排
     *
     * @param arrange
     */
    void addArrange(Arrange arrange);

    /**
     * 更新影片放映安排
     *
     * @param arrange
     */
    void updateArrange(Arrange arrange);

    /**
     * 添加放映厅信息
     *
     * @param room
     */
    void addRoom(Room room);

    /**
     * 修改放映厅信息
     *
     * @param room
     */
    void updateRoom(Room room);

    List<Film> findByCinema(Cinema cinema, Boolean select);

    List<Room> getRoomList(Cinema cinema);

    List<Arrange> getArrangeList(Arrange arrange);

    Arrange getArrange(Integer arrangeId);

    List<Cinema> getCinemaList(Film film);
}
