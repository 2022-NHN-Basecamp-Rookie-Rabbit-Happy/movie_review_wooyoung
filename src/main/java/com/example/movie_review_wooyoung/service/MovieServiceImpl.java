package com.example.movie_review_wooyoung.service;

import com.example.movie_review_wooyoung.dto.MovieDTO;
import com.example.movie_review_wooyoung.dto.PageRequestDTO;
import com.example.movie_review_wooyoung.dto.PageResultDTO;
import com.example.movie_review_wooyoung.entity.Movie;
import com.example.movie_review_wooyoung.entity.MovieImage;
import com.example.movie_review_wooyoung.repository.MovieImageRepository;
import com.example.movie_review_wooyoung.repository.MovieRepository;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import javax.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
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

    @Override
    public PageResultDTO<MovieDTO, Object[]> getList(PageRequestDTO pageRequestDTO) {

        Pageable pageable = pageRequestDTO.getPageable(Sort.by("mno").descending());

        Page<Object[]> result = movieRepository.getListPage(pageable);

        Function<Object[], MovieDTO> fn = arr -> entitiesToDTO(
            (Movie) arr[0],
            (List<MovieImage>) Arrays.asList((MovieImage) arr[1]),
            (Double) arr[2],
            (Long) arr[3]
        );

        return new PageResultDTO<>(result, fn);
    }
}
