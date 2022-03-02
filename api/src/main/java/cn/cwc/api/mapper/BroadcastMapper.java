package cn.cwc.api.mapper;

import cn.cwc.api.entity.Broadcast;
import cn.cwc.api.entity.Film;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface BroadcastMapper {

    @Select("SELECT * FROM broadcast WHERE id=#{broadcastId}")
    Broadcast findById(Integer broadcastId);

    @Select("SELECT * FROM broadcast WHERE film_id=#{filmId} AND is_main_page=1 AND type=1")
    Broadcast findMainByFilmId(Integer filmId);

    @Select("SELECT * FROM broadcast WHERE film_id=#{filmId}")
    List<Broadcast> findByFilmId(Integer filmId);

    int insert(List<Broadcast> broadcasts);

    int update(Broadcast broadcast);

    int delete(Integer broadcastId);
}
