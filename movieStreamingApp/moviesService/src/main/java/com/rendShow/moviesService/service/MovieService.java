package com.rendShow.moviesService.service;

import java.io.IOException;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.rendShow.moviesService.pojo.Movies;

public interface MovieService {
	
	Movies uploadMovie(Movies movies);

	Movies getMovieById(Long id);
	Movies getMovieByName(String title);

	List<Movies> getAllMovies();

//	Movies savedMovie(MultipartFile file) throws Exception;
//	Movies getMovie(String fileId) throws Exception;


	

}







