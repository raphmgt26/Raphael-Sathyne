package com.basespringboot.service;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.basespringboot.dto.SeanceRequest;
import com.basespringboot.dto.SeanceResponse;
import com.basespringboot.entity.SeanceEntity;
import com.basespringboot.repository.FilmRepository;
import com.basespringboot.repository.SeanceRepository;

@Service
public class SeanceServiceImpl implements SeanceService {

    private final SeanceRepository repository;
    private final FilmRepository filmRepository;

    public SeanceServiceImpl(SeanceRepository repository, FilmRepository filmRepository) {
        this.repository = repository;
        this.filmRepository = filmRepository;
    }

    public List<SeanceResponse> findAll() {
        return repository.findAll().stream().map(this::toResponse).toList();
    }

    public SeanceResponse findById(Long id) {
        return toResponse(getOrThrow(id));
    }

    public SeanceResponse create(SeanceRequest request) {
        if (request.getFilmId() == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "filmId obligatoire");
        }
        SeanceEntity s = new SeanceEntity();
        s.setFilm(filmRepository.findById(request.getFilmId()).orElseThrow(() ->
            new ResponseStatusException(HttpStatus.NOT_FOUND, "Film introuvable")));
        fill(s, request);
        return toResponse(repository.save(s));
    }

    public SeanceResponse update(Long id, SeanceRequest request) {
        SeanceEntity s = getOrThrow(id);
        fill(s, request);
        return toResponse(repository.save(s));
    }

    public void delete(Long id) {
        repository.delete(getOrThrow(id));
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
        return new SeanceResponse(s.getId(), s.getSalle(), s.getHoraire(),
            s.getNombrePlaces(), s.getFilm().getId());
    }
}