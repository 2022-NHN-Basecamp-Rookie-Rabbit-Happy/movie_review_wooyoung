package com.example.movie_review_wooyoung.repository;

import com.example.movie_review_wooyoung.entity.Movie;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface MovieRepository extends JpaRepository<Movie, Long> {

    @Query("SELECT m, mi, AVG(coalesce(r.grade, 0)), COUNT(DISTINCT r) FROM Movie m "
        + "LEFT OUTER JOIN MovieImage mi ON mi.movie = m "
        + "LEFT OUTER JOIN Review r ON r.movie = m GROUP BY m")
    Page<Object[]> getListPage(Pageable pageable);
}
