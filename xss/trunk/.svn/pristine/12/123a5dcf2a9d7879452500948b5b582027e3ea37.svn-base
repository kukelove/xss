package com.xp.brushms.common;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.io.File;

/**
 * Created by huangzhimin on 16/5/20.
 */
@Component
public class APConfig {

    public String resRoot = "/home/tmp";

    public void setResRoot(String resRoot) {
        this.resRoot = resRoot;
    }

    public File getUploadDir() {
        File ret = new File(resRoot, "upload");
        if (ret.exists() == false) {
            ret.mkdirs();
        }
        return ret;
    }
    public File getAppDir() {
        File ret = new File(resRoot, "app");
        if (ret.exists() == false) {
            ret.mkdirs();
        }
        return ret;
    }
}
