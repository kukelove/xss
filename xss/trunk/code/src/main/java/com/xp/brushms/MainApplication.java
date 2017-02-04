package com.xp.brushms;

import com.cc.ccutils.StringUtils;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.yaml.snakeyaml.Yaml;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@SpringBootApplication
@EnableScheduling
@Configuration
@EnableConfigurationProperties
public class MainApplication {

    public static void main(String[] args) {
//        SpringApplication.run(MainApplication.class, args);
        List<Object> sources = new ArrayList<>();
        sources.add(MainApplication.class);
        sources.addAll(fetchExtraClassPathResource());
        SpringApplication.run(sources.toArray(), args);
    }

    //从配置文件中获取额外加载资源
    public static List<ClassPathResource> fetchExtraClassPathResource() {
        List<ClassPathResource> resourceList = new ArrayList();
        List<String> extraResource = fetchExtraResourcePath();
        if(extraResource != null && extraResource.size() > 0) {
            for (String path : extraResource) {
                if(path.startsWith("/")) {
                    path = path.substring(1);
                }
                ClassPathResource resource = new ClassPathResource(path, ServletInitializer.class.getClassLoader());
                if(resource.exists()) {
                    resourceList.add(resource);
                } else {
                    System.out.println("extra resource not exist: ===== " + path);
                }
            }
        }
        return resourceList;
    }

    public static List<String> fetchExtraResourcePath() {
        try {
            String profile = "application.yml";
            DefaultResourceLoader resourceLoader = new DefaultResourceLoader();
            Resource resource = resourceLoader.getResource(ResourceLoader.CLASSPATH_URL_PREFIX + profile);
            Yaml yaml = new Yaml();
            Map<String,Map<String,Map<String,String>>> profileObj = yaml.loadAs(resource.getInputStream(), HashMap.class);
            String env = profileObj.get("spring").get("profiles").get("active");
            String envFile = "application-"+env+".yml";
            Resource resource_env = resourceLoader.getResource(ResourceLoader.CLASSPATH_URL_PREFIX + envFile);
            if(resource_env.exists()) {
                Map<String,Map<String,String>> envObj = yaml.loadAs(resource_env.getInputStream(), HashMap.class);
                Map<String, String> cusConfig = envObj.get("cusConfig");
                if(cusConfig == null) {
                    return null;
                }
                String extraResource = cusConfig.get("extraResource");
                if(StringUtils.isEmpty(extraResource)) {
                    return null;
                }
                String[] split = extraResource.split(",|，");
                List<String> extraResources = new ArrayList();
                for (String s : split) {
                    if(s.trim().length() == 0) continue;
                    extraResources.add(s.trim());
                }
                return extraResources;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;

    }
}
