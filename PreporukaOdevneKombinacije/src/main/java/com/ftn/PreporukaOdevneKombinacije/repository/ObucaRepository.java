package com.ftn.PreporukaOdevneKombinacije.repository;

import com.ftn.PreporukaOdevneKombinacije.model.Obuca;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ObucaRepository extends JpaRepository<Obuca, Long> {
}
