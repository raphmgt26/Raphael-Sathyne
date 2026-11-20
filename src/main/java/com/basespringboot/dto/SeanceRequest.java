package com.basespringboot.dto;

import java.time.LocalDateTime;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SeanceRequest {

    @NotBlank
    private String salle;

    @NotNull
    private LocalDateTime horaire;

    @Min(1)
    private int nombrePlaces;

    private Long filmId;
}