package com.basespringboot.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.basespringboot.entity.SeanceEntity;

@Repository
public interface SeanceRepository extends JpaRepository<SeanceEntity, Long> {
}