package com.laoji.message;


import com.laoji.message.sink.AdminLoginLogSink;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cloud.stream.annotation.EnableBinding;

@SpringBootApplication(exclude= {DataSourceAutoConfiguration.class})
@EnableBinding({AdminLoginLogSink.class})
public class MessageAdminLoginLogApplication {
    public static void main(String[] args) {
        SpringApplication.run(MessageAdminLoginLogApplication.class, args);
    }
}
