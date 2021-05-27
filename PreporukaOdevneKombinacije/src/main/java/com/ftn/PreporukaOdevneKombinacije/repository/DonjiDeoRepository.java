package com.ftn.PreporukaOdevneKombinacije.repository;

import com.ftn.PreporukaOdevneKombinacije.model.DonjiDeo;
import com.ftn.PreporukaOdevneKombinacije.model.GornjiDeo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DonjiDeoRepository extends JpaRepository<DonjiDeo, Long> {
}
