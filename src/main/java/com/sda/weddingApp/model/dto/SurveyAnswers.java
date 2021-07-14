package com.sda.weddingApp.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.time.LocalTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SurveyAnswers {

    @DateTimeFormat(pattern = "HH:mm")
    private LocalTime weddingTime;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate weddingDate;

    private boolean taskDJ;
    private boolean taskBand;
    private boolean taskVenue;
}
