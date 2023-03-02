package com.rendShow.moviesService.pojo;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Movies {
	
	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	private Long id;
	
	@Column(unique = true)
	private String title;
	
	@JsonFormat(pattern="yyyy-MM-dd")
	private Date releaseDate;
	private String rating;
	private String filePath;
	
	//the Lob annotation simply means when saved to the database, it will take on a type of binary large object(BLOB) in the database
	//Also data field type byte array will map the video byte data to form a readable using java code
	@Lob
	private byte[] movie;

	
	
	
   
    
   
	
	
	

}
