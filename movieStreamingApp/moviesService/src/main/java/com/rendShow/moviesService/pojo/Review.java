package com.rendShow.moviesService.pojo;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Review {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String body;
//    @ManyToOne
//    @JoinColumn(name = "movie_id")
//    private Movies movie;

    public Review(String body) {
        this.body = body;
    }
}
