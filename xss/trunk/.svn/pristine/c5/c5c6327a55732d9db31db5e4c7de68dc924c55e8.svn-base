package com.xp.brushms;

import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.web.SpringBootServletInitializer;
import org.springframework.core.io.ClassPathResource;

import java.util.List;

/**
 * Created by huangzhimin on 16/5/25.
 */
public class ServletInitializer extends SpringBootServletInitializer {

  /*  @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(MainApplication.class);
    }*/

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        application = application.sources(MainApplication.class);
        List<ClassPathResource> extraClassPathResource = MainApplication.fetchExtraClassPathResource();
        if(extraClassPathResource != null && !extraClassPathResource.isEmpty()) {
            application = application.sources(extraClassPathResource.toArray());
        }
        return application;
    }
}

