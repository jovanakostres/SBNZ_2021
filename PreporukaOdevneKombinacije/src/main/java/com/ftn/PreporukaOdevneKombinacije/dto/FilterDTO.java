package com.ftn.PreporukaOdevneKombinacije.dto;

public class FilterDTO {

    private String deo;
    private String boja;
    private String materijal;
    private String tip;
    private String podtip;
    private String pol ;

    public FilterDTO() {
    }

    public FilterDTO(String deo) {
        this.deo = deo;
    }

    public FilterDTO(String deo, String boja) {
        this.deo = deo;
        this.boja = boja;
    }

    public FilterDTO(String deo, String boja, String materijal) {
        this.deo = deo;
        this.boja = boja;
        this.materijal = materijal;
    }

    public FilterDTO(String deo, String boja, String materijal, String tip) {
        this.deo = deo;
        this.boja = boja;
        this.materijal = materijal;
        this.tip = tip;
    }

    public FilterDTO(String deo, String boja, String materijal, String tip, String podtip) {
        this.deo = deo;
        this.boja = boja;
        this.materijal = materijal;
        this.tip = tip;
        this.podtip = podtip;
    }

    public FilterDTO(String deo, String boja, String materijal, String tip, String podtip, String pol) {
        this.deo = deo;
        this.boja = boja;
        this.materijal = materijal;
        this.tip = tip;
        this.podtip = podtip;
        this.pol = pol;
    }

    public String getDeo() {
        return deo;
    }

    public void setDeo(String deo) {
        this.deo = deo;
    }

    public String getBoja() {
        return boja;
    }

    public void setBoja(String boja) {
        this.boja = boja;
    }

    public String getMaterijal() {
        return materijal;
    }

    public void setMaterijal(String materijal) {
        this.materijal = materijal;
    }

    public String getTip() {
        return tip;
    }

    public void setTip(String tip) {
        this.tip = tip;
    }

    public String getPodtip() {
        return podtip;
    }

    public void setPodtip(String podtip) {
        this.podtip = podtip;
    }

    public String getPol() {
        return pol;
    }

    public void setPol(String pol) {
        this.pol = pol;
    }
}
