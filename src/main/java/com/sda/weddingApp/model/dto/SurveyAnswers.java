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

    @DateTimeFormat(pattern = "HH:mm")
    private LocalTime weddingPartyTime;

    private String person1;
    private String person2;

    private boolean taskDJ;
    private boolean taskBand;
    private boolean taskVenue;
    private boolean taskAuto;
    private boolean taskAlcohol;
    private boolean taskInvitations;
    private boolean taskPhotographer;
    private boolean taskCameraman;
    private boolean taskRingsTogether;
    private boolean taskRingsSeparately;

    private boolean taskOutfit1;
    private boolean taskHairdresser1;
    private boolean taskBarber1;
    private boolean taskMakeupArtist1;
    private boolean taskManiPediCure1;

    private boolean taskOutfit2;
    private boolean taskHairdresser2;
    private boolean taskBarber2;
    private boolean taskMakeupArtist2;
    private boolean taskManiPediCure2;
}
