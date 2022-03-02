package cn.cwc.api.service.impl;

import cn.cwc.api.entity.Broadcast;
import cn.cwc.api.mapper.BroadcastMapper;
import cn.cwc.api.service.DrawingBedService;
import org.apache.ibatis.annotations.Result;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.*;

@Service
public class DrawingBedServiceImpl implements DrawingBedService {

    @Value("${drawing.bed.path}")
    private String bed_path;

    @Resource
    private BroadcastMapper broadcastMapper;

    @Override
    public String add(MultipartFile multipartFile, Broadcast broadcast) {
        String name = multipartFile.getOriginalFilename();
        String type = multipartFile.getContentType();
        try (BufferedInputStream bis = new BufferedInputStream(multipartFile.getInputStream());
             BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(bed_path + "/" + name))) {
            byte[] b = new byte[1024];
            while (bis.read(b) != -1)
                bos.write(b);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public boolean remove(Broadcast broadcast) {
        return false;
    }

    @Override
    public boolean update(Broadcast broadcast) {
        return false;
    }

    @Override
    public Broadcast search(Integer broadcastId, Integer filmId) {
        Broadcast broadcast;
        if (broadcastId == null) {
            broadcast = broadcastMapper.findMainByFilmId(filmId);
        } else {
            broadcast = broadcastMapper.findById(broadcastId);
        }
        return broadcast;
    }
}
