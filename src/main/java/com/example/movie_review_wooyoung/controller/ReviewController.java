package com.example.movie_review_wooyoung.controller;

import com.example.movie_review_wooyoung.dto.ReviewDTO;
import com.example.movie_review_wooyoung.service.ReviewService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/reviews")
@Log4j2
@RequiredArgsConstructor
public class ReviewController {

    private final ReviewService reviewService;

    @GetMapping("/{mno}/all")
    public ResponseEntity<List<ReviewDTO>> getList(@PathVariable("mno") Long mno) {
        log.info("-------------list-------------");
        log.info("MNO: " + mno);

        List<ReviewDTO> reviewDTOList = reviewService.getListOfMovie(mno);

        return new ResponseEntity<>(reviewDTOList, HttpStatus.OK);
    }

    @PostMapping("/{mno}")
    public ResponseEntity<Long> addReview(@RequestBody ReviewDTO movieReviewDTO) {
        log.info("-------------add MovieReview-------------");
        log.info("reviewDTO: " + movieReviewDTO);

        Long reviewNum = reviewService.register(movieReviewDTO);

        return new ResponseEntity<>(reviewNum, HttpStatus.OK);
    }

    @PutMapping("/{mno}/{reviewNum}")
    public ResponseEntity<Long> modifyReview(@PathVariable Long reviewNum,
        @RequestBody ReviewDTO movieReviewDTO) {
        log.info("-------------modify MovieReview-------------");
        log.info("reviewDTO: " + movieReviewDTO);

        reviewService.modify(movieReviewDTO);

        return new ResponseEntity<>(reviewNum, HttpStatus.OK);
    }

    @DeleteMapping("/{mno}/{reviewNum}")
    public ResponseEntity<Long> removeReview(@PathVariable Long reviewNum) {
        log.info("-------------remove MovieReview-------------");
        log.info("reviewNum: " + reviewNum);

        reviewService.remove(reviewNum);

        return new ResponseEntity<>(reviewNum, HttpStatus.OK);
    }
}
