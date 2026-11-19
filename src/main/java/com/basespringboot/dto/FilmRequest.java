package com.basespringboot.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FilmRequest {

    @NotBlank
    private String titre;

    @NotBlank
    private String realisateur;

    @Min(1)
    private int dureeMinutes;

    @NotBlank
    private String genre;
}