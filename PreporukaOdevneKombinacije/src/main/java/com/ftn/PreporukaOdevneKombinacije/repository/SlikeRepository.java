package com.ftn.PreporukaOdevneKombinacije.repository;

import com.ftn.PreporukaOdevneKombinacije.model.SlikeBojaKoze;
import com.ftn.PreporukaOdevneKombinacije.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SlikeRepository extends JpaRepository<SlikeBojaKoze, Long> {
    SlikeBojaKoze findById(long l);
}
