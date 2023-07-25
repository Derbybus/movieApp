package com.rendShow.moviesService.service;

import com.rendShow.moviesService.pojo.Review;

public interface ReviewService {
    Review createReview(String reviewBody, Long id);
}
