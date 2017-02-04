package com.xp.brushms.controller;

import com.xp.brushms.auth.NoAuth;
import com.xp.brushms.util.GzipUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Created by huangzhimin on 16/5/27.
 */
@Controller
@RequestMapping("/test")
@NoAuth
public class TestController {

    @RequestMapping("/test.api")
    public String test() throws InterruptedException {
        System.out.println("test.api>>>>>>>>>>>>>");
        return "pages/test";
    }

    @RequestMapping("/testResponse.api")
    public void testResponse(HttpServletRequest request,HttpServletResponse response) throws InterruptedException, IOException {
        System.out.println("testResponse.api>>>>>>>>>>>>>");

        String content = "" +
                "responseBody>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>" +
                "responseBody>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>" +
                "responseBody>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>" +
                "responseBody>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>" +
                "responseBody>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>" +
                "responseBody>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>" +
                "responseBody>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>" +
                "responseBody>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>" +
                "responseBody>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>" +
                "responseBody>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>" +
                "responseBody>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>" +
                "responseBody>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>";
        System.out.println("response size:" + content.getBytes().length);
        ServletOutputStream outputStream = response.getOutputStream();
        outputStream.write(content.getBytes());
        outputStream.flush();
        outputStream.close();
    }

    @RequestMapping("/testGzipResponse.api")
    public void testGzipResponse(HttpServletRequest request,HttpServletResponse response) throws Exception {
        System.out.println("testGzipResponse.api>>>>>>>>>>>>>");
        response.addHeader("Content-Encoding", "gzip");
        String content = "" +
                "responseBody>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>" +
                "responseBody>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>" +
                "responseBody>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>" +
                "responseBody>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>" +
                "responseBody>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>" +
                "responseBody>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>" +
                "responseBody>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>" +
                "responseBody>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>" +
                "responseBody>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>" +
                "responseBody>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>" +
                "responseBody>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>" +
                "responseBody>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>";

        byte[] data = content.getBytes();
        System.out.println("before compress,data size:" + data.length);
        byte[] compressData = GzipUtils.compress(data);
        System.out.println("after compress,data size:" + compressData.length);
        response.addHeader("Content-Length",compressData.length + "");
        ServletOutputStream outputStream = response.getOutputStream();
        outputStream.write(compressData);
        outputStream.flush();
        outputStream.close();
    }

}
