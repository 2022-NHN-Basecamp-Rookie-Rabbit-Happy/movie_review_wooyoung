package com.example.movie_review_wooyoung.repository;

import com.example.movie_review_wooyoung.entity.Movie;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MovieRepository extends JpaRepository<Movie, Long> {

}
