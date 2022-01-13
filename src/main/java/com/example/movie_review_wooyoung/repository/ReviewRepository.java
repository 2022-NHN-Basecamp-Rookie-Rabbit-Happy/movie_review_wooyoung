package com.example.movie_review_wooyoung.repository;

import com.example.movie_review_wooyoung.entity.Movie;
import com.example.movie_review_wooyoung.entity.Review;
import java.util.List;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.EntityGraph.EntityGraphType;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReviewRepository extends JpaRepository<Review, Long> {

    @EntityGraph(attributePaths = {"member"}, type = EntityGraphType.FETCH)
    List<Review> findByMovie(Movie movie);

}
