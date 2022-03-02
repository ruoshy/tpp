package cn.cwc.api.mapper;

import cn.cwc.api.entity.Cinema;
import cn.cwc.api.entity.City;
import cn.cwc.api.entity.Film;
import cn.cwc.api.entity.Region;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface CinemaMapper {

    @Select("SELECT * FROM cinema WHERE id=#{cinemaId}")
    Cinema findById(Integer cinemaId);

    List<Cinema> findByRegion(Region region);

    int insert(Cinema cinema);

    int update(Cinema cinema);

    List<Cinema> findAllByFilm(Film film);
}
