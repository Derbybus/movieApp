package com.rendShow.moviesService.service;

import java.io.IOException;
import java.util.List;

import com.rendShow.moviesService.exception.VideoAlreadyExistsException;
import com.rendShow.moviesService.exception.VideoNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import com.rendShow.moviesService.pojo.Movies;
import com.rendShow.moviesService.repository.MovieRepository;

@Service
public class MovieServiceImp implements MovieService {
	
	@Autowired
	private MovieRepository movieRepository;


	@Override
	public Movies uploadMovie(Movies movies) {
		Movies movie = Movies.builder()
				.title(movies.getTitle())
				.releaseDate(movies.getReleaseDate())
				.trailer(movies.getTrailer())
				.poster(movies.getPoster())
				.backdrops(movies.getBackdrops())
				.reviews(movies.getReviews())
				.genres(movies.getGenres())
				.build();
		return movieRepository.save(movie);

	}

	@Override
	public Movies getMovieById(Long id) {
		return movieRepository.findById(id)
				.stream()
				.filter(movie -> movie.getId() == id)
				.findAny()
				.orElseThrow(() -> new VideoNotFoundException("Movie not found"));
	}

	@Override
	public Movies getMovieByName(String title) {
		return null;
	}

	@Override
	public List<Movies> getAllMovies() {
		return movieRepository.findAll();
	}

//	@Override
//	public Movies savedMovie(MultipartFile file) throws Exception {
//		String fileName = StringUtils.cleanPath(file.getOriginalFilename());
//		try {
//			if(fileName.contains("..")){
//				throw new Exception("Filename contains invalid path sequence " + fileName);
//			}
//			Movies movie = new Movies(fileName, file.getContentType(), file.getBytes());
//			return movieRepository.save(movie);
//		} catch (Exception e){
//			throw  new Exception("Could not save file: " + fileName);
//		}
//
//	}


//	@Override
//	public Movies getMovie(String fileId) throws Exception{
//		return movieRepository.findById(fileId)
//				.orElseThrow(() -> new Exception("File not found with id: " + fileId));
//
//	}
}
