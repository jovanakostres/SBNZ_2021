package com.ftn.PreporukaOdevneKombinacije.repository;

import com.ftn.PreporukaOdevneKombinacije.model.KomadOdece;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface KomadOdeceRepository extends JpaRepository<KomadOdece, Long> {
}
