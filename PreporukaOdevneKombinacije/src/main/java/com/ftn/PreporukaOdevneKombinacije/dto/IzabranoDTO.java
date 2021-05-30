package com.ftn.PreporukaOdevneKombinacije.dto;

public class IzabranoDTO {

    private Long idGornjiDeo;
    private Long idDonjiDeo;
    private Long idJakna;
    private Long idObuca;

    public IzabranoDTO(Long idGornjiDeo, Long idDonjiDeo, Long idJakna, Long idObuca) {
        this.idGornjiDeo = idGornjiDeo;
        this.idDonjiDeo = idDonjiDeo;
        this.idJakna = idJakna;
        this.idObuca = idObuca;
    }

    public IzabranoDTO() {
    }

    public Long getIdGornjiDeo() {
        return idGornjiDeo;
    }

    public void setIdGornjiDeo(Long idGornjiDeo) {
        this.idGornjiDeo = idGornjiDeo;
    }

    public Long getIdDonjiDeo() {
        return idDonjiDeo;
    }

    public void setIdDonjiDeo(Long idDonjiDeo) {
        this.idDonjiDeo = idDonjiDeo;
    }

    public Long getIdJakna() {
        return idJakna;
    }

    public void setIdJakna(Long idJakna) {
        this.idJakna = idJakna;
    }

    public Long getIdObuca() {
        return idObuca;
    }

    public void setIdObuca(Long idObuca) {
        this.idObuca = idObuca;
    }
}
