package com.ftn.PreporukaOdevneKombinacije.model;

import com.ftn.PreporukaOdevneKombinacije.model.enums.*;

import javax.persistence.*;

@Entity
@Table(name = "komadiOdece")
public abstract class KomadOdece {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Boja boja;

    @Column(nullable = false, name = "bojaint")
    private BojaIntenzitet bojaIntenzitet;

    @Column(nullable = false)
    private Materijal materijal;

    @Column(nullable = false)
    private Vreme vreme;

    @Column(nullable = false)
    private int prioritet;

    @Column(nullable = false, name = "koefod")
    private double koeficijentOdabira;

    @Column(nullable = false, length = 1000000)
    private String image;

    public KomadOdece(Boja boja, BojaIntenzitet bojaIntenzitet, Materijal materijal, Vreme vreme, int prioritet, String image) {
        this.boja = boja;
        this.bojaIntenzitet = bojaIntenzitet;
        this.materijal = materijal;
        this.vreme = vreme;
        this.prioritet = prioritet;
        this.image = image;
    }

    public KomadOdece(){

    }

}
