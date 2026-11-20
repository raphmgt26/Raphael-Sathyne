package com.basespringboot.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.basespringboot.dto.FilmRequest;
import com.basespringboot.dto.FilmResponse;
import com.basespringboot.entity.FilmEntity;
import com.basespringboot.repository.FilmRepository;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/films")
@RequiredArgsConstructor
public class FilmController {

    private final FilmRepository filmRepository;

    @GetMapping
    public List<FilmResponse> getAll() {
        return filmRepository.findAll().stream()
                .map(this::toResponse)
                .toList();
    }

    @GetMapping("/{id}")
    public FilmResponse getById(@PathVariable Long id) {
        FilmEntity film = filmRepository.findById(id).orElseThrow();
        return toResponse(film);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public FilmResponse create(@Valid @RequestBody FilmRequest request) {
        FilmEntity film = new FilmEntity();
        film.setTitre(request.getTitre());
        film.setRealisateur(request.getRealisateur());
        film.setDureeMinutes(request.getDureeMinutes());
        film.setGenre(request.getGenre());

        FilmEntity saved = filmRepository.save(film);
        return toResponse(saved);
    }

    private FilmResponse toResponse(FilmEntity film) {
        return new FilmResponse(
                film.getId(),
                film.getTitre(),
                film.getRealisateur(),
                film.getDureeMinutes(),
                film.getGenre()
        );
    }
}