package com.ftn.PreporukaOdevneKombinacije.dto;

import java.util.List;

public class PreporuceniKomadiOpsteDTO {
    private List<PreporuceniGornjiDeoDTO> preporuceniGornjiDeoDTO;
    private List<PreporuceniDonjiDeoDTO> preporuceniDonjiDeoDTO;
    private List<PreporucenaJaknaDTO> preporucenaJaknaDTO;
    private List<PreporucenaObucaDTO> preporucenaObucaDTO;
    private String image;
    private String tips;


    public PreporuceniKomadiOpsteDTO(List<PreporuceniGornjiDeoDTO> preporuceniGornjiDeoDTO) {
        this.preporuceniGornjiDeoDTO = preporuceniGornjiDeoDTO;
    }

    public PreporuceniKomadiOpsteDTO(List<PreporuceniGornjiDeoDTO> preporuceniGornjiDeoDTO, List<PreporuceniDonjiDeoDTO> preporuceniDonjiDeoDTO, List<PreporucenaJaknaDTO> preporucenaJaknaDTO, List<PreporucenaObucaDTO> preporucenaObucaDTO) {
        this.preporuceniGornjiDeoDTO = preporuceniGornjiDeoDTO;
        this.preporuceniDonjiDeoDTO = preporuceniDonjiDeoDTO;
        this.preporucenaJaknaDTO = preporucenaJaknaDTO;
        this.preporucenaObucaDTO = preporucenaObucaDTO;
    }

    public PreporuceniKomadiOpsteDTO() {}

    public List<PreporuceniGornjiDeoDTO> getPreporuceniGornjiDeoDTO() {
        return preporuceniGornjiDeoDTO;
    }

    public void setPreporuceniGornjiDeoDTO(List<PreporuceniGornjiDeoDTO> preporuceniGornjiDeoDTO) {
        this.preporuceniGornjiDeoDTO = preporuceniGornjiDeoDTO;
    }

    public List<PreporuceniDonjiDeoDTO> getPreporuceniDonjiDeoDTO() {
        return preporuceniDonjiDeoDTO;
    }

    public void setPreporuceniDonjiDeoDTO(List<PreporuceniDonjiDeoDTO> preporuceniDonjiDeoDTO) {
        this.preporuceniDonjiDeoDTO = preporuceniDonjiDeoDTO;
    }

    public List<PreporucenaJaknaDTO> getPreporucenaJaknaDTO() {
        return preporucenaJaknaDTO;
    }

    public void setPreporucenaJaknaDTO(List<PreporucenaJaknaDTO> preporucenaJaknaDTO) {
        this.preporucenaJaknaDTO = preporucenaJaknaDTO;
    }

    public List<PreporucenaObucaDTO> getPreporucenaObucaDTO() {
        return preporucenaObucaDTO;
    }

    public void setPreporucenaObucaDTO(List<PreporucenaObucaDTO> preporucenaObucaDTO) {
        this.preporucenaObucaDTO = preporucenaObucaDTO;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getTips() {
        return tips;
    }

    public void setTips(String tips) {
        this.tips = tips;
    }
}
