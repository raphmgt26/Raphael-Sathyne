package com.basespringboot.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.basespringboot.entity.FilmEntity;

@Repository
public interface FilmRepository extends JpaRepository<FilmEntity, Long> {
}