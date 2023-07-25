package com.rendShow.moviesService.controller;

import com.rendShow.moviesService.pojo.Review;
import com.rendShow.moviesService.service.ReviewServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/api/movies/reviews")
public class ReviewController {

    @Autowired
    private ReviewServiceImp reviewService;

    @PostMapping
      public ResponseEntity<Review> createReviews(@RequestBody Map<String, String> payload) {
        Review review = reviewService.createReview(payload.get("reviewBody"), Long.valueOf(payload.get("id")));
        return ResponseEntity.ok(review);
    }

}
