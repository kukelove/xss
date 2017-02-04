package com.xp.brushms.controller;



import com.xp.brushms.common.Config;
import com.xp.brushms.util.DateUtils;
import com.xp.brushms.vo.Msg;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import java.io.File;
import java.io.IOException;
import java.util.Date;

/**
 * Created by hzm on 2015/7/31.
 */
@Controller
@RequestMapping("upload")
public class UploadController {

    @Autowired
    private Config config;

    @RequestMapping("upload.api")
    @ResponseBody
    public Msg upload(MultipartFile file) throws IOException {
        String fileName = DateUtils.getDateInt(new Date())+"@#"+file.getOriginalFilename();
        File filePath = new File(config.getUploadDir()+fileName);
        file.transferTo(filePath);
        return Msg.create(0, null, fileName);
    }
}
