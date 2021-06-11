package com.ftn.PreporukaOdevneKombinacije.dto;

import com.ftn.PreporukaOdevneKombinacije.model.User;
import com.ftn.PreporukaOdevneKombinacije.model.enums.Pol;
import com.ftn.PreporukaOdevneKombinacije.model.enums.TipTela;

import javax.persistence.Column;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class UserDTO {
    @NotBlank
    @Email
    private String email;
    @NotBlank
    @Size(min = 4, message = "Password must be at least 4 characters long")
    private String password;

    private Pol pol;
    private int visina;
    private int kilaza;

    private int ramena, kukovi, struk;

    public UserDTO(User u){
        this.email = u.getUsername();
        this.password = u.getPassword();
        this.pol = u.getPol();
        this.visina = u.getVisina();
        this.ramena = u.getRamena();
        this.kukovi = u.getKukovi();
        this.struk = u.getStruk();
    }

    public UserDTO(@NotBlank @Email String email, @NotBlank @Size(min = 4, message = "Password must be at least 4 characters long") String password) {
        this.email = email;
        this.password = password;
    }

    public UserDTO(@NotBlank @Email String email, @NotBlank @Size(min = 4, message = "Password must be at least 4 characters long") String password, Pol pol, int visina, int ramena, int kukovi, int struk) {
        this.email = email;
        this.password = password;
        this.pol = pol;
        this.visina = visina;
        this.ramena = ramena;
        this.kukovi = kukovi;
        this.struk = struk;
    }

    public UserDTO(){}

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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
}
