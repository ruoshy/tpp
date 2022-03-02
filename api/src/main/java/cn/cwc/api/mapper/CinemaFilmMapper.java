package cn.cwc.api.mapper;

import cn.cwc.api.entity.Cinema;
import cn.cwc.api.entity.CinemaFilm;
import cn.cwc.api.entity.City;
import cn.cwc.api.entity.Film;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface CinemaFilmMapper {

    @Select("SELECT * FROM cinema_film WHERE id = #{cinemaFilmId}")
    CinemaFilm findById(Integer cinemaFilmId);

    /**
     * in CinemaFilmMapper.xml
     * find CinemaFilm by Cinema id
     *
     * @param cinema
     * @return
     */
    List<CinemaFilm> findByCinema(Cinema cinema);

    /**
     * in CinemaFilmMapper.xml
     * find CinemaFilm by Film id and  City id
     *
     * @param film
     * @param city
     * @return
     */
    List<CinemaFilm> findByFilmAndCity(Film film, City city);

    int insert(CinemaFilm cinemaFilm);

    int update(CinemaFilm cinemaFilm);
}
