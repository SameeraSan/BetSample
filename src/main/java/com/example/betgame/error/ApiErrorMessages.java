package com.example.betgame.error;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.http.HttpStatus;
import java.time.LocalDateTime;

@Data
public class ApiErrorMessages {

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy hh:mm:ss")
    private LocalDateTime timestamp;
    private HttpStatus status;
    private String message;

    private ApiErrorMessages() {
        timestamp = LocalDateTime.now();
    }

    ApiErrorMessages(HttpStatus status, String message) {
        this();
        this.status = status;
        this.message = message;
    }
}