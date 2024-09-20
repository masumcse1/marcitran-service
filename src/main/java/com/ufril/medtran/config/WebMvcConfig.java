package com.ufril.medtran.config;

import com.ufril.medtran.util.Utils;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.servlet.config.annotation.ContentNegotiationConfigurer;
import org.springframework.web.servlet.config.annotation.PathMatchConfigurer;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * Created by moin on 12/8/15.
 */
@Configuration
public class WebMvcConfig extends WebMvcConfigurerAdapter {

    @Override
    public void configurePathMatch(PathMatchConfigurer configurer) {
        configurer.setUseSuffixPatternMatch(false);
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        // Add upload file path in relative path
        String filepath = "file:" + Utils.getFilePath() + "/";
        registry.addResourceHandler("/files/**").addResourceLocations(filepath);
        super.addResourceHandlers(registry);
    }

    @Override
    public void configureContentNegotiation(ContentNegotiationConfigurer configurer) {
        configurer.favorPathExtension(true)
                .favorParameter(false)
                .parameterName("mediaType")
                .ignoreAcceptHeader(true)
                .useJaf(false)
                .defaultContentType(MediaType.APPLICATION_JSON)
                .mediaType("image/*", MediaType.IMAGE_JPEG)
                .mediaType("pdf", MediaType.APPLICATION_OCTET_STREAM)
                .mediaType("doc", MediaType.APPLICATION_OCTET_STREAM)
                .mediaType("docx", MediaType.APPLICATION_OCTET_STREAM)
        ;
    }
}