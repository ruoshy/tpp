package cn.cwc.api.mapper;

import cn.cwc.api.entity.City;
import cn.cwc.api.entity.Region;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface RegionMapper {

    @Select("SELECT * FROM region WHERE id=#{regionId}")
    Region findById(Integer regionId);

    @Select("SELECT * FROM region WHERE city_id=#{id}")
    List<Region> findByCity(City city);

    /**
     * find region by city_id
     *
     * @param cityId city_id
     * @return region list
     */
    @Select("SELECT * FROM region WHERE city_id=#{cityId}")
    List<Region> findByCityId(Integer cityId);

    int insert(List<Region> regions);

    int update(Region region);

}
