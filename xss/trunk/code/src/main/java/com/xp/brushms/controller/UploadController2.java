package com.xp.brushms.controller;

import com.cc.ccutils.android.ApkUtils;
import com.hj.signature.AppUtilsBaidu;
import com.xp.brushms.common.Config;
import com.xp.brushms.vo.Msg;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.beans.BeanInfo;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.security.cert.Certificate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

/**
 * Created by hzm on 2015/7/31.
 */
@Controller
@RequestMapping("upload2")
public class UploadController2 {

    @Resource
    protected Config config;

    @RequestMapping("normal.api")
    @ResponseBody
    public Msg upload(MultipartFile file) throws IOException {
        String fileName = UUID.randomUUID().toString();
        File filePath = new File(config.getUploadDir(), fileName);
        file.transferTo(filePath);
        return Msg.create(0, null, fileName);
    }

    static class ApkResponse {
        public String fileName;
        public ApkInfo apkInfo;
    }
    /**
     * 加载签名
     * @param jarFile
     * @param je
     * @param readBuffer
     * @return
     */
    private static Certificate[] loadCertificates(JarFile jarFile, JarEntry je, byte[] readBuffer) {
        try {
            InputStream is=jarFile.getInputStream(je);
            while(is.read(readBuffer, 0, readBuffer.length) != -1) {
            }
            is.close();
            return je != null ? je.getCertificates() : null;
        } catch(IOException e) {
        }
        return null;
    }

    private String getSignBaidu(File apkFile)  {
        try {
            List<String> signatures=new ArrayList<String>();
            JarFile jarFile=new JarFile(apkFile);
            JarEntry je=jarFile.getJarEntry("AndroidManifest.xml");
            byte[] readBuffer=new byte[8192];
            Certificate[] certs=loadCertificates(jarFile, je, readBuffer);
            if(certs != null) {
                for(Certificate c: certs) {
                    String sig=toCharsString(c.getEncoded());
                    return AppUtilsBaidu.getBaiduSignMd5(sig);
                }
            }
        } catch(Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }

    private static String toCharsString(byte[] sigBytes) {
        byte[] sig=sigBytes;
        final int N=sig.length;
        final int N2=N * 2;
        char[] text=new char[N2];
        for(int j=0; j < N; j++) {
            byte v=sig[j];
            int d=(v >> 4) & 0xf;
            text[j * 2]=(char)(d >= 10 ? ('a' + d - 10) : ('0' + d));
            d=v & 0xf;
            text[j * 2 + 1]=(char)(d >= 10 ? ('a' + d - 10) : ('0' + d));
        }
        return new String(text);
    }

    public static class ApkInfo  {
        public ApkUtils.ApkInfo base;
        public String signBaidu;
    }

    public static void copy(Object source, Object dest)throws Exception {
        //获取属性
        BeanInfo sourceBean = Introspector.getBeanInfo(source.getClass(), Object.class);
        PropertyDescriptor[] sourceProperty = sourceBean.getPropertyDescriptors();

        BeanInfo destBean = Introspector.getBeanInfo(dest.getClass(), Object.class);
        PropertyDescriptor[] destProperty = destBean.getPropertyDescriptors();

        try{
            for(int i=0;i<sourceProperty.length;i++){

                for(int j=0;j<destProperty.length;j++){

                    if(sourceProperty[i].getName().equals(destProperty[j].getName())){
                        //调用source的getter方法和dest的setter方法
                        destProperty[j].getWriteMethod().invoke(dest, sourceProperty[i].getReadMethod().invoke(source));
                        break;
                    }
                }
            }
        }catch(Exception e){
            throw new Exception("属性复制失败:"+e.getMessage());
        }
    }
    protected void transferTo(MultipartFile file, File dstPath) {
        FileOutputStream fout = null;
        try {
            InputStream ins = file.getInputStream();
            byte[] buff = new byte[4096];
            fout = new FileOutputStream(dstPath);
            while (true) {
                int len = ins.read(buff);
                if (len < 0) break;
                if (len > 0) {
                    fout.write(buff, 0, len);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (fout != null) {
            try {
                fout.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    @RequestMapping("apk.api")
    @ResponseBody
    public Msg uploadApk(MultipartFile file) throws IOException {
        String fileName = UUID.randomUUID().toString();
        File filePath = new File(config.getUploadDir(), fileName);
        System.out.println("absolute-path: " + filePath.getAbsolutePath());
        transferTo(file, filePath);

        ApkUtils.ApkInfo apkInfo0 = ApkUtils.parseApk(filePath.toString());
        if (apkInfo0 == null) {
            return Msg.create(1, "apk解析错误", null);
        }
        ApkInfo apkInfo = new ApkInfo();
        apkInfo.base = apkInfo0;
        apkInfo.signBaidu = getSignBaidu(filePath);
        ApkResponse apkResp = new ApkResponse();
        apkResp.fileName = fileName;
        apkResp.apkInfo = apkInfo;
        return Msg.create(0, null, apkResp);
    }
}
