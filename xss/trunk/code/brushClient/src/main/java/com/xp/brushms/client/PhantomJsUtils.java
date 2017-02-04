package com.xp.brushms.client;

import com.cc.ccutils.CryptUtils;
import com.cc.ccutils.JsonUtils;
import com.cc.ccutils.SystemUtils;

import java.io.*;
import java.util.*;
import java.util.concurrent.TimeUnit;

/**
 * Created by huangzhimin on 16/7/18.
 */
public class PhantomJsUtils {

    private static String phantomjsPath = "D:\\Program Files\\phantomjs\\bin\\phantomjs";
    private static String workDirectory = System.getProperty("user.dir") + "\\brushClient\\script";
    private static final String scriptJs = "brush.js";

    static {
        if (!SystemUtils.isWindows()) { //线上环境
            phantomjsPath = System.getProperty("user.dir") + "/phantomjs/bin/phantomjs";
            workDirectory = System.getProperty("user.dir") + "/script";
        }
    }

    public static class TaskResult {
        public boolean success;
        public String reason;
        private Map<String, String> results = new HashMap<>();

        public void setResult(String result) {
            if (result == null) {
                this.reason = "empty-result";
                return;
            }
            String[] lines = result.trim().split("\n");
            if (lines.length <= 0) {
                this.reason = "empty-result";
                return;
            }
            this.success = lines[lines.length - 1].equals("success");
            if (!this.success) {
                this.reason = lines[lines.length - 1];
            }
            for (String line : lines) {
                line = line.trim();
                if (line.startsWith("result-")) {
                    int i = line.indexOf(":");
                    if (i < 0) continue;
                    String key = line.substring(0, i).trim();
                    String val = line.substring(i + 1).trim();
                    results.put(key, val);
                }
            }
        }

        public String getResult(int actionIndex) {
            String key = "result-" + actionIndex;
            return results.get(key);
        }
    }

    public static TaskResult execute(RunTask task) {
        TaskResult ret = new TaskResult();
        String config = JsonUtils.toJson(task);
        try {
            String content = execute(config, task.timeout, task.loadImages);
            if(task.isWriteLog()) {
                System.out.println("result_"+task.taskId + " : \n" + content);
            }
            ret.setResult(content);
        } catch (Exception exp) {
            System.err.println(exp.getMessage());
//            exp.printStackTrace();
        }
        return ret;
    }

    private static String execute(String param, long timeout, boolean loadImages) throws IOException {
//        ProcessBuilder builder = new ProcessBuilder(new String[]{phantomjsPath,
//                "--web-security=false","--ignore-ssl-errors=true","--ssl-protocol=any","--load-images=false",
//                "--disk-cache=true","--max-disk-cache-size=2097152","--webdriver-loglevel=NONE",
//                "--output-encoding=utf8", "--script-encoding=utf8", scriptJs, CryptUtils.toHexString(param.getBytes())});


        String[] arrs = new String[]{"--web-security=false","--ignore-ssl-errors=true","--ssl-protocol=any",
                "--disk-cache=true","--max-disk-cache-size=2097152","--webdriver-loglevel=NONE",
                "--output-encoding=utf8", "--script-encoding=utf8"};


        List commands = new ArrayList();
        commands.add(phantomjsPath);
        commands.addAll(Arrays.asList(arrs));
        if(!loadImages) {
            commands.add("--load-images=false");
        }
        commands.add(scriptJs);
        commands.add(CryptUtils.toHexString(param.getBytes()));

        ProcessBuilder builder = new ProcessBuilder(commands);

        builder.directory(new File(workDirectory).getAbsoluteFile());
        builder.redirectErrorStream(true);
        Process process = builder.start();

        try {
            boolean tag = process.waitFor(timeout, TimeUnit.MILLISECONDS);
            if (!tag) return "failed: timeout";
        } catch (Exception exp) {
            System.err.println(exp.getMessage());
//            exp.printStackTrace();
            return "failed: wait-exception";
        } finally {
            try {
                if (process.isAlive()) {
                    process.destroy();
                }
            } catch (Exception ee) {
                System.err.println(ee.getMessage());
//                ee.printStackTrace();
            }
        }
        InputStream is = process.getInputStream();
        BufferedReader br = new BufferedReader(new InputStreamReader(is));
        StringBuffer sbf = new StringBuffer();
        String tmp;
        while ((tmp = br.readLine()) != null) {
            sbf.append(tmp + "\n");
        }
        String ret = sbf.toString();
        return ret;
    }

}
