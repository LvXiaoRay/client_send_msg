package com.client.client_send_msg;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication(exclude= {DataSourceAutoConfiguration.class})
@EnableScheduling
public class ClientSendMsgApplication {

    public static void main(String[] args) {
        SpringApplication.run(ClientSendMsgApplication.class, args);
    }

}
