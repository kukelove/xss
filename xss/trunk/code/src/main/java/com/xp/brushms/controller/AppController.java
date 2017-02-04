package com.xp.brushms.controller;

import com.cc.ccutils.CryptUtils;
import com.xp.brushms.common.APConfig;
import com.xp.brushms.common.Config;
import com.xp.brushms.dao.mongo.Pagination;
import com.xp.brushms.entity.App;
import com.xp.brushms.service.AppService;
import com.xp.brushms.vo.Msg;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.io.File;
import java.util.Date;
import java.util.UUID;

/**
 * Created by huangzhimin on 16/5/19.
 */
@Controller
@RequestMapping("/app")
public class AppController {

    @Resource
    protected APConfig apConfig;

    @Resource
    protected AppService appService;

    @RequestMapping("/index.html")
    public String index() {
        return "pages/apps";
    }

    @RequestMapping("/apps.api")
    @ResponseBody
    public Pagination<App> getApps(String name, String packageName, int pageNo, int pageSize) {
        return appService.getApps(name, packageName, pageNo, pageSize);
    }

    @RequestMapping("app_update.api")
    @ResponseBody
    public Msg updateApp(@RequestBody App app, String apkFileName) {
        if (StringUtils.isEmpty(app.getPackageName()) || StringUtils.isEmpty(app.getMd5())) {
            return Msg.create(-1, "APK未上传", null);
        }
        if (StringUtils.isEmpty(apkFileName) == false) {
            File f = new File(apConfig.getUploadDir(), apkFileName);
            if (f.exists() == false) {
                return Msg.create(1, "文件'" + apkFileName + "' 不存在", null);
            }
            File dstFile = new File(apConfig.getAppDir(), app.getId() + ".apk");
            if (dstFile.exists()) {
                if (!dstFile.delete()) {
                    return Msg.create(2, "apk更新失败", null);
                }
            }
            if (!f.renameTo(dstFile)) {
                return Msg.create(3, "apk更新失败", null);
            }
            String md5 = CryptUtils.getFileMd5(dstFile.toString());
            if (app.getMd5().equals(md5) == false) {
                return Msg.create(4, "apk MD5校验失败", null);
            }
            app.setUploaded(true);
        }
        if (StringUtils.isEmpty(app.getId())) {
            app.setId(UUID.randomUUID().toString());
            app.setCreated(new Date());
        }
        app.setUpdateTime(new Date());
        appService.saveApp(app);
        return Msg.create(0, null, app);
    }

    @RequestMapping("app_enable.api")
    @ResponseBody
    public Msg enableApp(String appId, boolean enable) {
        App app = appService.getApp(appId);
        app.setEnable(enable);
        appService.saveApp(app);
        return Msg.create();
    }
}
