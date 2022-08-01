package com.hs.gatekeeper.configuration;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

/**
 * @author zzx
 * @version 1.0
 * @date 2022/8/1 10:34
 */
@Configuration
@MapperScan("com.hs.gatekeeper.service.mapper")
public class MybatisPusConfiguration {

}
