package com.ftn.PreporukaOdevneKombinacije.dto;

public class PrikazDTO {

    private Long id;
    private String image;

    public PrikazDTO() {
    }

    public PrikazDTO(Long id, String image) {
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
