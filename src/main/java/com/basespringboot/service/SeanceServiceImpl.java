package com.basespringboot.service;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.basespringboot.dto.SeanceRequest;
import com.basespringboot.dto.SeanceResponse;
import com.basespringboot.entity.FilmEntity;
import com.basespringboot.entity.SeanceEntity;
import com.basespringboot.repository.FilmRepository;
import com.basespringboot.repository.SeanceRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class SeanceServiceImpl implements SeanceService {

    private final SeanceRepository repository;
    private final FilmRepository filmRepository;

    @Override
    public List<SeanceResponse> findAll() {
        return repository.findAll().stream().map(this::toResponse).toList();
    }

    @Override
    public SeanceResponse findById(Long id) {
        return toResponse(getOrThrow(id));
    }

    @Override
    public SeanceResponse create(SeanceRequest request) {
        SeanceEntity s = new SeanceEntity();
        s.setFilm(resolveFilm(request.getFilmId()));
        fill(s, request);
        return toResponse(repository.save(s));
    }

    @Override
    public SeanceResponse update(Long id, SeanceRequest request) {
        SeanceEntity s = getOrThrow(id);
        s.setFilm(resolveFilm(request.getFilmId()));
        fill(s, request);
        return toResponse(repository.save(s));
    }

    @Override
    public void delete(Long id) {
        repository.delete(getOrThrow(id));
    }

    private FilmEntity resolveFilm(Long filmId) {
        if (filmId == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "filmId obligatoire");
        }
        return filmRepository.findById(filmId).orElseThrow(() ->
            new ResponseStatusException(HttpStatus.NOT_FOUND, "Film introuvable"));
    }

    private void fill(SeanceEntity s, SeanceRequest request) {
        s.setSalle(request.getSalle());
        s.setHoraire(request.getHoraire());
        s.setNombrePlaces(request.getNombrePlaces());
    }

    private SeanceEntity getOrThrow(Long id) {
        return repository.findById(id).orElseThrow(() ->
            new ResponseStatusException(HttpStatus.NOT_FOUND, "Seance introuvable"));
    }

    private SeanceResponse toResponse(SeanceEntity s) {
        return new SeanceResponse(
            s.getId(),
            s.getSalle(),
            s.getHoraire(),
            s.getNombrePlaces(),
            s.getFilm().getId()
        );
    }
}