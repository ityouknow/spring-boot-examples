package com.neo.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class Configurations {
    
    @Value("${fastdfs.base.url}")
    private String fdfsUrl;

    public String getFdfsUrl() {
        return fdfsUrl;
    }

    public void setFdfsUrl(String fdfsUrl) {
        this.fdfsUrl = fdfsUrl;
    }
}