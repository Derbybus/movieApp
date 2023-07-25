package com.rendShow.genreService.controller;

import java.io.IOException;

import com.rendShow.genreService.pojo.Genre;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.*;

import com.rendShow.genreService.service.GenreService;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/api/genre")
public class GenreController {

    @Autowired
	private GenreService genreService;

    @PostMapping("/add")
    public String addVideo(@RequestParam("title") String title, @RequestParam("file")MultipartFile file, Model model) throws IOException {
        return genreService.addVideo(title, file);

    }

    @GetMapping("/videos/{id}")
    public String getVideo(@PathVariable String id, Model model) throws Exception{
        Genre video = genreService.getVideo(id);
        model.addAttribute("title", video.getTitle());
        model.addAttribute("url","/videos/stream/" + id);
        return "videos";
    }

    //Add streamVideo() method to create a streaming url from the video input stream
    @GetMapping("/videos/stream/{id}")
    public void streamVideo(@PathVariable String id, HttpServletResponse response) throws Exception{
        Genre video = genreService.getVideo(id);
        FileCopyUtils.copy(video.getStream(), response.getOutputStream());
    }

//	@Autowired
//	private GenreService genreService;
//
//	@PostMapping
//	public Genre createGenre(@RequestBody Genre genre) {
//		return genreService.createGenre(genre);
//
//	}
//
//	@GetMapping
//	public List<Genre> getAllGenre(){
//		return genreService.getAllGenre();
//
//	}

}
