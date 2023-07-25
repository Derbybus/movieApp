package com.rendShow.genreService.respository;

//import org.springframework.data.jpa.repository.JpaRepository;

import com.rendShow.genreService.pojo.Genre;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GenreRepository extends MongoRepository<Genre, Long> {

}
