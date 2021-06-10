package com.ftn.PreporukaOdevneKombinacije.model;

import javax.persistence.*;

@Entity
@Table(name = "slike_boja")
public class SlikeBojaKoze {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 1000000)
    private String image;

    public SlikeBojaKoze() {
    }

    public SlikeBojaKoze(Long id, String image) {
        this.id = id;
        this.image = image;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
