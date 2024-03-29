package com.rendShow.genreService.pojo;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.InputStream;

//import javax.persistence.Entity;
//import javax.persistence.GeneratedValue;
//import javax.persistence.GenerationType;
//import javax.persistence.Id;

//@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Document(collection = "movies")
public class Genre {
	@Id
//	@GeneratedValue(strategy= GenerationType.IDENTITY)
	private String id;
	private String title;
//	private String type;
	private InputStream stream;
//	private String category;

}
