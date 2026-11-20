package com.basespringboot.dto;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class SeanceResponse {

    private Long id;
    private String salle;
    private LocalDateTime horaire;
    private int nombrePlaces;
    private Long filmId;
}