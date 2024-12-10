package com.busanit501.bootpractice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing // 베이스 엔티티를 이용 할 수 있게 전역에 설정 주입이라고 봐도 됨
public class BootPracticeApplication {

    public static void main(String[] args) {
        SpringApplication.run(BootPracticeApplication.class, args);
    }

}
