package com.rendShow.genreService.service;

import java.io.IOException;
import java.util.List;

import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;
import com.mongodb.client.gridfs.model.GridFSFile;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.gridfs.GridFsOperations;
import org.springframework.data.mongodb.gridfs.GridFsTemplate;
import org.springframework.stereotype.Service;

import com.rendShow.genreService.pojo.Genre;
import com.rendShow.genreService.respository.GenreRepository;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.multipart.MultipartFile;

@Service
//@Slf4j
public class GenreService {

    @Autowired
    private GridFsTemplate gridFsTemplate;

    @Autowired
    private GridFsOperations operations;

    public String addVideo(String title, MultipartFile file) throws IOException {
        DBObject metaData = new BasicDBObject();
        metaData.put("type", "video");
        metaData.put("title", title);
        ObjectId id = gridFsTemplate.store(
                file.getInputStream(), file.getName(), file.getContentType(), metaData);
        return id.toString();
    }

    public Genre getVideo(String id) throws IllegalStateException, IOException {
        GridFSFile file = gridFsTemplate.findOne(new Query(Criteria.where("_id").is(id)));
        Genre video = new Genre();
        video.setTitle(file.getMetadata().get("title").toString());
        video.setStream(operations.getResource(file).getInputStream());
        return video;
    }

//	@Autowired
//	private GenreRepository genreRepository;
//
//	public Genre createGenre(Genre genre) {
//		Genre genres = new Genre();
//		genres.setCategory(genre.getCategory());
//		return genreRepository.save(genres);
//
//	}
//
//	public List<Genre> getAllGenre() {
//		return genreRepository.findAll();
//
//	}
//


}
