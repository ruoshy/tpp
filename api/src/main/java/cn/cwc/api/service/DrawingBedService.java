package cn.cwc.api.service;

import cn.cwc.api.entity.Broadcast;
import org.springframework.web.multipart.MultipartFile;

public interface DrawingBedService {

    /**
     * 添加海报至图床
     *
     * @param multipartFile
     * @return
     */
    String add(MultipartFile multipartFile, Broadcast broadcast);

    /**
     * 删除海报
     *
     * @param broadcast
     * @return
     */
    boolean remove(Broadcast broadcast);

    /**
     * 更新海报信息
     *
     * @param broadcast
     * @return
     */
    boolean update(Broadcast broadcast);

    /**
     * 查询海报信息
     *
     * @param broadcastId
     * @return
     */
    Broadcast search(Integer broadcastId, Integer filmId);
}
