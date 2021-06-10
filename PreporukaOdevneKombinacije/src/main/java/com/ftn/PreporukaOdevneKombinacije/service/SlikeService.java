package com.ftn.PreporukaOdevneKombinacije.service;

import com.ftn.PreporukaOdevneKombinacije.model.SlikeBojaKoze;
import com.ftn.PreporukaOdevneKombinacije.repository.SlikeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SlikeService {

    @Autowired
    SlikeRepository slikeRepository;

    public SlikeBojaKoze findById(long l){
        return slikeRepository.findById(l);
    }

}
