package com.ftn.PreporukaOdevneKombinacije.repository;

import com.ftn.PreporukaOdevneKombinacije.model.KomadOdece;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface KomadOdeceRepository extends JpaRepository<KomadOdece, Long> {
    Iterable<? extends KomadOdece> findByKorisnikId(long l);

    List<KomadOdece> findByAktivan(boolean aktivnost);

}
