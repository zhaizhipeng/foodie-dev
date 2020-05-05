package com.ysdrzp.resource;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "file")
@PropertySource("classpath:file-upload-prod.properties")
@Data
public class FileUpload {

    /**
     * 用户头像路径
     */
    private String imageUserFaceLocation;

    /**
     * 头像服务地址
     */
    private String imageServerUrl;

}
