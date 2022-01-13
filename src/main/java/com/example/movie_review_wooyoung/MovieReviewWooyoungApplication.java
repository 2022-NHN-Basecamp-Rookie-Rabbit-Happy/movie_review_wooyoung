package com.example.movie_review_wooyoung;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class MovieReviewWooyoungApplication {

    public static void main(String[] args) {
        SpringApplication.run(MovieReviewWooyoungApplication.class, args);
    }

}
