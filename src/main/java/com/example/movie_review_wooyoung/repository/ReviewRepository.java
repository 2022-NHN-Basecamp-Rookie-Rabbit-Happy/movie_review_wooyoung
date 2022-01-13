package com.example.movie_review_wooyoung.repository;

import com.example.movie_review_wooyoung.entity.Review;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReviewRepository extends JpaRepository<Review, Long> {

}
