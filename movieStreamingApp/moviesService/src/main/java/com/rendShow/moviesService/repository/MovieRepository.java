package com.rendShow.moviesService.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.rendShow.moviesService.pojo.Movies;


@Repository
public interface MovieRepository extends JpaRepository<Movies, Long> {




}
