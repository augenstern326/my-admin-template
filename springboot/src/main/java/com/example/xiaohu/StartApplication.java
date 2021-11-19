package com.example.xiaohu;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@Slf4j
@SpringBootApplication
public class StartApplication {

    public static void main(String[] args) {
        SpringApplication.run(StartApplication.class, args);
        log.info("\n============================启动成功============================\n"
                +"接口文档: http://localhost:9000/myAdminTemplate/doc.html\n"
                + "druid监控后台: http://localhost:9000/myAdminTemplate/druid\n");
    }

}
