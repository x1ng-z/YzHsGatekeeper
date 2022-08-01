package com.hs.gatekeeper.configuration;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * @author zzx
 * @version 1.0
 * @date 2022/8/1 10:30
 */
@Configuration()
@ConfigurationProperties(prefix = "netty")
@Data
public class NettyConfiguration {
    private int port;
}
