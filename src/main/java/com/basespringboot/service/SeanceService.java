package com.basespringboot.service;

import java.util.List;

import com.basespringboot.dto.SeanceRequest;
import com.basespringboot.dto.SeanceResponse;

public interface SeanceService {
    List<SeanceResponse> findAll();
    SeanceResponse findById(Long id);
    SeanceResponse create(SeanceRequest request);
    SeanceResponse update(Long id, SeanceRequest request);
    void delete(Long id);
}