package com.xp.brushms.util;

import com.cc.ccutils.StringUtils;
import net.sf.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class RunCommandUtil {

    private static final Logger logger = LoggerFactory.getLogger(RunCommandUtil.getMd5Key());

    private static String commandUrl = "http://112.5.16.35:3000/runCommand";
    private static String commandResultUrl = "http://112.5.16.35:3000/command?commandId=";

    public static JSONObject runCommand(String mac, String command) {
        Map<String, String> param = new HashMap<String, String>();
        param.put("command", command);
        param.put("commandType", "shell");
        param.put("mac", mac);
        param.put("key", getMd5Key());
        logger.info("execute command=====mac:" + mac + ", command:" + command + ",commandUrl:" + commandUrl);
        String result = null;
        try {
            result = HttpUtils.postAndGetString(commandUrl, param);
        }catch (Exception e){
            
        }
        logger.info("execute result==========:\n" + result);
        if (result == null||result == "")
            return null;
        else
            return JSONObject.fromObject(result);
    }


    /**
     * 依据命令执行结果的commandId获取执行结果的json数据
     * @param commandId
     * @return
     */
    public static JSONObject getResultJson(String commandId) {
        HttpRequester requerster = new HttpRequester();
        String url = commandResultUrl + commandId + "&key=";
        HttpRespons hr = null;
        JSONObject resultObj = null;
        if (StringUtils.isEmpty(commandId))
            return null;
        try {
            hr = requerster.sendPost(url + getMd5Key());
//            resultObj = JsonUtil.parseJsonObject(hr.getContent());
            resultObj = JSONObject.fromObject(hr.getContent());
        } catch (IOException e) {
            logger.error(e.getMessage());
            return null;
        }
        return resultObj;
    }
    private static String getMd5Key() {
        String key = DateUtils.getNowDateString() + "/agentserver";
        String md5Key = SecurityUtil.doMd5(key);
        return md5Key;
    }

//    public static void main(String[] args) {
//        System.out.println(getMd5Key());
//        String mac = "84:2B:2B:46:1A:C6";
//        String command = "echo 'hello world!'";
//        JSONObject jsonObject = runCommand(mac, command);
//        System.out.println(jsonObject);
//        JSONObject resultJson = getResultJson(jsonObject.getString("commandId"));
//        System.out.println("result:" + resultJson);
//    }
}
