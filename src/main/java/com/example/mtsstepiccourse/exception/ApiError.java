package com.example.mtsstepiccourse.exception;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.OffsetDateTime;

@AllArgsConstructor
@Data
public class ApiError {
    private OffsetDateTime dateOccurred;
    private String message;
}
