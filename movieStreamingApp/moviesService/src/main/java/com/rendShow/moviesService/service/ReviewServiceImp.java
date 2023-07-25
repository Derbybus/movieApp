package com.rendShow.moviesService.service;


import com.rendShow.moviesService.exception.VideoNotFoundException;
import com.rendShow.moviesService.pojo.Movies;
import com.rendShow.moviesService.pojo.Review;
import com.rendShow.moviesService.repository.MovieRepository;
import com.rendShow.moviesService.repository.ReviewRepository;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ReviewServiceImp implements ReviewService{

    @Autowired
    private ReviewRepository reviewRepository;

    @Autowired
    private MovieRepository movieRepository;

    //Add reviews to movies
    @Override
    public Review createReview(String reviewBody, Long id) {
       Review review = new Review(reviewBody);
       reviewRepository.save(review);
       //Gets the movie then updates the review to the movie
       Optional<Movies> optionalMovies = movieRepository.findById(id);
       if (optionalMovies.isPresent()){
           Movies movie = optionalMovies.get();
           movie.getReviews().add(review);
           movieRepository.save(movie);
           return review;
       } else{
           throw new VideoNotFoundException("Movie unavailable");
       }

    }



}
