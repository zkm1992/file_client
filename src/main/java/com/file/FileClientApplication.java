package com.file;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.context.annotation.PropertySource;

/**
 * SpringBoot启动类入口
 *
 * @author zkm on 2018-09-15
 * @version 1.0
 */
@ServletComponentScan
@MapperScan("com.file.mapper")
@PropertySource(value = "classpath:file_client.yml")
@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
public class FileClientApplication {

    public static void main(String[] args) {
        SpringApplication.run(FileClientApplication.class, args);
    }

}