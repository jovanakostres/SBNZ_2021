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

    @Column(name = "bojaint")
    private BojaIntenzitet bojaIntenzitet;

    @Column(nullable = false)
    private Materijal materijal;

    @Column()
    private Vreme vreme;

    @Column(nullable = false)
    private int prioritet;

    @Column(nullable = false, name = "koefod")
    private double koeficijentOdabira;

    @Column(nullable = false, length = 1000000)
    private String image;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="korisnik_id", nullable=false)
    private User korisnik;

    public KomadOdece(Boja boja, BojaIntenzitet bojaIntenzitet, Materijal materijal, Vreme vreme, int prioritet, String image) {
        this.boja = boja;
        this.bojaIntenzitet = bojaIntenzitet;
        this.materijal = materijal;
        this.vreme = vreme;
        this.prioritet = prioritet;
        this.image = image;
    }

    public KomadOdece(Boja boja, Materijal materijal, Vreme vreme, int prioritet, double koeficijentOdabira, String image) {
        this.boja = boja;
        this.materijal = materijal;
        this.prioritet = prioritet;
        this.vreme  = vreme;
        this.koeficijentOdabira = koeficijentOdabira;
        this.image = image;
    }

    public KomadOdece(Long id, Boja boja, Materijal materijal, Vreme vreme, int prioritet, double koeficijentOdabira, String image) {
        this.id = id;
        this.boja = boja;
        this.materijal = materijal;
        this.prioritet = prioritet;
        this.vreme  = vreme;
        this.koeficijentOdabira = koeficijentOdabira;
        this.image = image;
    }

    public KomadOdece(){

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Boja getBoja() {
        return boja;
    }

    public void setBoja(Boja boja) {
        this.boja = boja;
    }

    public BojaIntenzitet getBojaIntenzitet() {
        return bojaIntenzitet;
    }

    public void setBojaIntenzitet(BojaIntenzitet bojaIntenzitet) {
        this.bojaIntenzitet = bojaIntenzitet;
    }

    public Materijal getMaterijal() {
        return materijal;
    }

    public void setMaterijal(Materijal materijal) {
        this.materijal = materijal;
    }

    public Vreme getVreme() {
        return vreme;
    }

    public void setVreme(Vreme vreme) {
        this.vreme = vreme;
    }

    public int getPrioritet() {
        return prioritet;
    }

    public void setPrioritet(int prioritet) {
        this.prioritet = prioritet;
    }

    public double getKoeficijentOdabira() {
        return koeficijentOdabira;
    }

    public void setKoeficijentOdabira(double koeficijentOdabira) {
        this.koeficijentOdabira = koeficijentOdabira;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public User getKorisnik() {
        return korisnik;
    }

    public void setKorisnik(User korisnik) {
        this.korisnik = korisnik;
    }
}
