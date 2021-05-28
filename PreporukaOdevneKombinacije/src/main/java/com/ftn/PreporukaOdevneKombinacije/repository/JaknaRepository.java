package com.ftn.PreporukaOdevneKombinacije.repository;

import com.ftn.PreporukaOdevneKombinacije.model.DonjiDeo;
import com.ftn.PreporukaOdevneKombinacije.model.Jakna;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JaknaRepository   extends JpaRepository<Jakna, Long> {
}
