package com.example.movie_review_wooyoung.repository;

import static org.junit.jupiter.api.Assertions.*;

import com.example.movie_review_wooyoung.entity.Movie;
import com.example.movie_review_wooyoung.entity.MovieImage;
import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import java.util.stream.IntStream;
import javax.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.test.annotation.Commit;

@SpringBootTest
public class MovieRepositoryTest {

    @Autowired
    private MovieRepository movieRepository;

    @Autowired
    private MovieImageRepository movieImageRepository;

    @Commit
    @Transactional
    @Test
    public void insertMovies() {

        IntStream.rangeClosed(1, 100).forEach(i -> {
            Movie movie = Movie.builder()
                .title("Movie...." + i)
                .build();

            System.out.println("-----------------------------");

            movieRepository.save(movie);

            int count = (int) (Math.random() * 5) + 1;

            for (int j = 0; j < count; j++) {
                MovieImage movieImage = MovieImage.builder()
                    .uuid(UUID.randomUUID().toString())
                    .movie(movie)
                    .imgName("test" + j + ".jpg").build();

                movieImageRepository.save(movieImage);
            }
            System.out.println("=============================");

        });
    }

    @Test
    public void testListPage() {

        PageRequest pageRequest = PageRequest.of(0, 10, Sort.by(Direction.DESC, "mno"));

        Page<Object[]> result = movieRepository.getListPage(pageRequest);

        for (Object[] objects : result.getContent()) {
            System.out.println(Arrays.toString(objects));
        }
    }

    @Test
    public void testGetMovieWithAll() {

        List<Object[]> result = movieRepository.getMovieWithAll(94L);

        System.out.println(result);

        for (Object[] arr : result) {
            System.out.println(Arrays.toString(arr));
        }

    }
}