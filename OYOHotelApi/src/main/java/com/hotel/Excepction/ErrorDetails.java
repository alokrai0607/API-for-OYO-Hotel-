package com.hotel.Excepction;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class ErrorDetails {

    private String message;
    private String description;
    private LocalDateTime time;
}
