package com.rendShow.moviesService.controller;

import com.google.common.net.HttpHeaders;
import com.rendShow.moviesService.ResponseData;
import com.rendShow.moviesService.pojo.Movies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.rendShow.moviesService.service.MovieServiceImp;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.io.File;
import java.io.IOException;
import java.util.List;


@RestController
//@CrossOrigin
@RequestMapping("/api/movies")
public class MovieController {
	
	@Autowired
	private MovieServiceImp service;

	@PostMapping("/upload")
	public ResponseEntity<?> saveMovies(@RequestBody Movies movies){
		Movies movie = service.uploadMovie(movies);
		return ResponseEntity.status(HttpStatus.OK).body(movie);
	}
	@GetMapping("/all")
	public ResponseEntity<?> getMovies(){
		List<Movies> movieList = service.getAllMovies();
		return ResponseEntity.ok(movieList);
	}

	@GetMapping("/{id}")
	public ResponseEntity<?> getMoviesById(@PathVariable("id") Long id){
		return ResponseEntity.ok(service.getMovieById(id));
	}



	//uploads to database
//	@PostMapping("/uploads")
//	public ResponseData uploadFile(@RequestParam("file") MultipartFile file) throws Exception {
//		Movies movie = null;
//		String downloadURl = "";
//		movie = service.savedMovie(file);
//		downloadURl = ServletUriComponentsBuilder.fromCurrentContextPath()
//				.path("/download/")
//				.path(movie.getFileId())
//				.toUriString();
//		return new ResponseData(movie.getFileName(),
//				downloadURl,
//				file.getContentType(),
//				file.getSize());
//
//	}

//	@GetMapping("/download/{fileId}")
//	public ResponseEntity<Resource> downloadFile(@PathVariable String fileId) throws Exception{
//		Movies movie = null;
//		movie = service.getMovie(fileId);
//		return ResponseEntity.ok()
//				.contentType(MediaType.parseMediaType(movie.getFileType()))
//				.header(HttpHeaders.CONTENT_DISPOSITION,
//						"movie; filename=\"" + movie.getFileName() + "\"")
//				.body(new ByteArrayResource(movie.getData()));
//
//	}


	//uploads into a directory
//	@PostMapping("/upload")
//	public ResponseEntity<?> handleFileUpload(@RequestParam("file") MultipartFile file){
//		String fileName = file.getOriginalFilename();
//		try {
//			file.transferTo(new File("C:\\upload\\" + fileName));
//		} catch (Exception e) {
//			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
//		}
//		return ResponseEntity.ok("File uploaded successfully");
//	}


}
