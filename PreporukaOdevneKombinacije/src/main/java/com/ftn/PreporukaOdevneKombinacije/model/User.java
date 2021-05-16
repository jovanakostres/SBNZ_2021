package com.ftn.PreporukaOdevneKombinacije.model;

import com.ftn.PreporukaOdevneKombinacije.model.enums.BojaKoze;
import com.ftn.PreporukaOdevneKombinacije.model.enums.Pol;
import com.ftn.PreporukaOdevneKombinacije.model.enums.TipTela;
import com.ftn.PreporukaOdevneKombinacije.model.enums.Visina;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "platform_user")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String username;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private Pol pol;

    @Column(name="tiptela")
    private TipTela tipTela;

    @Column(nullable = false)
    private int visina;

    @Column(nullable = false)
    private int kilaza;

    @Column(nullable = false)
    private int ramena, kukovi, struk; //za tip tela

    @Column(name="bojakoze")
    private BojaKoze bojaKoze;

    @OneToMany(mappedBy="korisnik")
    private List<KomadOdece> komadi;

    public User(String username, String password, Pol pol, TipTela tipTela, int visina, int kilaza, int ramena, int kukovi, int struk, BojaKoze bojaKoze, List<KomadOdece> komadi) {
        this.username = username;
        this.password = password;
        this.pol = pol;
        this.tipTela = tipTela;
        this.visina = visina;
        this.kilaza = kilaza;
        this.ramena = ramena;
        this.kukovi = kukovi;
        this.struk = struk;
        this.bojaKoze = bojaKoze;
        this.komadi = komadi;
    }

    public User(Long id, String username, String password, Pol pol, TipTela tipTela, int visina, int kilaza, int ramena, int kukovi, int struk, BojaKoze bojaKoze, List<KomadOdece> komadi) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.pol = pol;
        this.tipTela = tipTela;
        this.visina = visina;
        this.kilaza = kilaza;
        this.ramena = ramena;
        this.kukovi = kukovi;
        this.struk = struk;
        this.bojaKoze = bojaKoze;
        this.komadi = komadi;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Pol getPol() {
        return pol;
    }

    public void setPol(Pol pol) {
        this.pol = pol;
    }

    public TipTela getTipTela() {
        return tipTela;
    }

    public void setTipTela(TipTela tipTela) {
        this.tipTela = tipTela;
    }

    public int getVisina() {
        return visina;
    }

    public void setVisina(int visina) {
        this.visina = visina;
    }

    public int getKilaza() {
        return kilaza;
    }

    public void setKilaza(int kilaza) {
        this.kilaza = kilaza;
    }

    public int getRamena() {
        return ramena;
    }

    public void setRamena(int ramena) {
        this.ramena = ramena;
    }

    public int getKukovi() {
        return kukovi;
    }

    public void setKukovi(int kukovi) {
        this.kukovi = kukovi;
    }

    public int getStruk() {
        return struk;
    }

    public void setStruk(int struk) {
        this.struk = struk;
    }

    public BojaKoze getBojaKoze() {
        return bojaKoze;
    }

    public void setBojaKoze(BojaKoze bojaKoze) {
        this.bojaKoze = bojaKoze;
    }

    public List<KomadOdece> getKomadi() {
        return komadi;
    }

    public void setKomadi(List<KomadOdece> komadi) {
        this.komadi = komadi;
    }
}
