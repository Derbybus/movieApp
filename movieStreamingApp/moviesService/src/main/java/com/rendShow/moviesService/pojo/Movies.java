package com.rendShow.moviesService.pojo;



import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Movies {
	
	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	private Long id;
	private String title;
	private String releaseDate;
//	@Column(length = 200)
//	private String description;
//	private String videoName;
	private String trailer;
	private String poster;
	@ElementCollection
	private List<String> genres;
    @ElementCollection
	private List<String> backdrops;
	@OneToMany(fetch = FetchType.EAGER,cascade = CascadeType.ALL)
	private List<Review> reviews;



}
