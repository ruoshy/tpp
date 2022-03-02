package cn.cwc.api.mapper;

import cn.cwc.api.entity.Cinema;
import cn.cwc.api.entity.City;
import cn.cwc.api.entity.Film;
import com.github.pagehelper.Page;
import org.apache.ibatis.annotations.Select;

import java.util.Date;
import java.util.List;

public interface FilmMapper {

    @Select("SELECT * FROM film WHERE id=#{filmId}")
    Film findById(Integer filmId);

    List<Film> findByCinema(Cinema cinema, Boolean select);

    default List<Film> findByCinema(Cinema cinema) {
        return findByCinema(cinema, null);
    }

    Page<Film> findAll(Film film);

    @Select("SELECT DISTINCT film.* FROM film " +
            "INNER JOIN cinema_film ON film.id = cinema_film.film_id " +
            "INNER JOIN cinema ON cinema_film.id = cinema.id " +
            "INNER JOIN region ON cinema.region_id = region.id " +
            "WHERE region.city_id = #{city.id}")
    List<Film> findByCity(City city);

    List<Film> findByDate(Date startTime, Date endTime);

    int insert(Film film);

    int update(Film film);
}
