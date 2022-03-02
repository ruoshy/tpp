package cn.cwc.api.mapper;

import cn.cwc.api.entity.City;
import cn.cwc.api.entity.Region;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface CityMapper {

    @Select("SELECT * FROM city WHERE city_id=#{cityId}")
    City findById(Integer cityId);

    /**
     * find all city
     *
     * @return city list
     */
    @Select("SELECT * FROM city")
    List<City> findAll();

    /**
     * in CityMapper.xml
     *
     * @param region
     * @return
     */
    City findByRegion(Region region);

    /**
     * in CityMapper.xml
     *
     * @param cities
     * @return
     */
    int insert(List<City> cities);
}
