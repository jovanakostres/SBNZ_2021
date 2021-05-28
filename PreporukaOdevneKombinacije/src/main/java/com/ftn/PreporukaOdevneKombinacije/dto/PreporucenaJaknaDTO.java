package com.ftn.PreporukaOdevneKombinacije.dto;

public class PreporucenaJaknaDTO {

    private Long id;
    private String image;
    private Double poeni;

    public PreporucenaJaknaDTO(Long id, String image, Double poeni) {
        this.id = id;
        this.image = image;
        this.poeni = poeni;
    }

    public PreporucenaJaknaDTO() {}

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

    public Double getPoeni() {
        return poeni;
    }

    public void setPoeni(Double poeni) {
        this.poeni = poeni;
    }
}
