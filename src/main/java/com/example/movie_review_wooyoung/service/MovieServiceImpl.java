package com.example.movie_review_wooyoung.service;

import com.example.movie_review_wooyoung.dto.MovieDTO;
import com.example.movie_review_wooyoung.entity.Movie;
import com.example.movie_review_wooyoung.entity.MovieImage;
import com.example.movie_review_wooyoung.repository.MovieImageRepository;
import com.example.movie_review_wooyoung.repository.MovieRepository;
import java.util.List;
import java.util.Map;
import javax.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

@Service
@Log4j2
@RequiredArgsConstructor
public class MovieServiceImpl implements MovieService {

    private final MovieRepository movieRepository;

    private final MovieImageRepository imageRepository;

    @Transactional
    @Override
    public Long register(MovieDTO movieDTO) {

        Map<String, Object> entityMap = dtoToEntity(movieDTO);
        Movie movie = (Movie) entityMap.get("movie");
        List<MovieImage> movieImageList = (List<MovieImage>) entityMap.get("imgList");

        movieRepository.save(movie);

        movieImageList.forEach(movieImage -> {
            imageRepository.save(movieImage);
        });

        return movie.getMno();
    }
}
