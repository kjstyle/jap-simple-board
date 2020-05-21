package com.kjstyle.jpaboard;

import com.kjstyle.jpaboard.domain.user.User;
import com.kjstyle.jpaboard.domain.user.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class JpaBoardApplication {

    public static void main(String[] args) {
        SpringApplication.run(JpaBoardApplication.class, args);
    }


    @Bean
    public CommandLineRunner initData(UserRepository repository) {
        return (args) -> {
            repository.save(User.builder().userId("kj").email("kjstyle79@naver.com").name("이길주").build());
            repository.save(User.builder().userId("ejoin312").email("eojin312@naver.com").name("이어진").build());
        };
    }
}
