package com.rendShow.moviesService.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;
import java.util.List;


@AllArgsConstructor
@Data
public class ApiError {
    private String message;
    private List<String> details;
    private HttpStatus status;
    private LocalDateTime time;
}
