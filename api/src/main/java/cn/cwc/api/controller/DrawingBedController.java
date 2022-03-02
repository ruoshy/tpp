package cn.cwc.api.controller;

import cn.cwc.api.entity.Broadcast;
import cn.cwc.api.mapper.BroadcastMapper;
import cn.cwc.api.model.Action;
import cn.cwc.api.model.Result;
import cn.cwc.api.service.DrawingBedService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.UUID;


@RestController
@RequestMapping("/bed")
public class DrawingBedController {

    @Value("${drawing.bed.path}")
    private String path;

    @Resource
    private DrawingBedService drawingBedService;

    @RequestMapping("/add")
//    public Result<String> add(Broadcast broadcast, @RequestParam("file") MultipartFile[] multipartFiles) {
//
//        return null;
//    }
    public Result<String> add() {
        String id = UUID.randomUUID().toString().replace("-", "");
        return new Result<>(id, Action.OK);
    }

    @RequestMapping("/search")
    public void search(HttpServletResponse response,
                       @RequestParam(required = false) String url,
                       @RequestParam(value = "film_id",required = false) Integer filmId) {
        OutputStream os = null;
        try {
            if (filmId != null) {
                Broadcast broadcast = drawingBedService.search(null, filmId);
                url = broadcast.getUrl();
            }
            BufferedImage bi = ImageIO.read(new FileInputStream(path + "/" + url));
            response.setContentType("image/png");
            os = response.getOutputStream();
            if (bi != null)
                ImageIO.write(bi, "png", os);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (os != null) {
                    os.flush();
                    os.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
