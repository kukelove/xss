package com.xp.brushms.util;

import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

/**
 * Created by sjh on 2016/3/24.
 */
public class UploadUtils {

    public static String fileTransferTo(MultipartFile file, String targetFolder) {
        String retImgPath = null;
        String fileName = file.getOriginalFilename();
        SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");
        String fileExt = fileName.substring(fileName.lastIndexOf(".") + 1).toLowerCase();

        String newFileName = df.format(new Date()) + "_" + new Random().nextInt(1000) + "." + fileExt;
        if (!file.isEmpty()) {
            try {
                byte[] bytes = file.getBytes();
                File target = new File(targetFolder);
                if (!target.exists()) {
                    target.mkdirs();
                }
                retImgPath = targetFolder + File.separator + newFileName;
                File file1 = new File(retImgPath);
                BufferedOutputStream stream =
                        new BufferedOutputStream(new FileOutputStream(file1));
                stream.write(bytes);
                stream.close();

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return retImgPath;
    }

}
