package com.tenero.blog.config;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author kesong
 * @description oss配置
 * @date 2022/3/17 14:51
 */

@Component
@ConfigurationProperties(prefix = "oss")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class OSSConnectConfiguration {
    public String accessKeyID;
    public String accessKeySecret;
    public String endpoint;
    public String bucket;
    public String musicfirstKey;
    public String imagefirstKey;

}
