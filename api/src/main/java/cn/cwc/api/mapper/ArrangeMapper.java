package cn.cwc.api.mapper;

import cn.cwc.api.entity.Arrange;
import cn.cwc.api.entity.Cinema;
import org.apache.ibatis.annotations.Select;

import java.util.ArrayList;
import java.util.List;

public interface ArrangeMapper {

    Arrange findById(Integer arrangeId);

    /**
     * in ArrangeMapper.xml
     * find Arrange by Cinema Id
     *
     * @param cinema
     * @return
     */
    List<Arrange> findByCinema(Cinema cinema);

    List<Arrange> findAll(Arrange arrange);

    default int insert(Arrange arrange) {
        List<Arrange> arranges = new ArrayList<>();
        arranges.add(arrange);
        return insert(arranges);
    }

    /**
     * in ArrangeMapper.xml
     * insert Arrangr info
     *
     * @param arranges
     * @return
     */
    int insert(List<Arrange> arranges);

    /**
     * in ArrangeMapper.xml
     * update Arrange info
     *
     * @param arrange
     * @return
     */
    int update(Arrange arrange);
}
