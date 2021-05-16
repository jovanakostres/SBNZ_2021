package com.ftn.PreporukaOdevneKombinacije.dto;

import java.util.List;

public class PreporuceniKomadiDTO {

    private List<PreporuceniGornjiDeoDTO> preporuceniGornjiDeoDTO;

    public PreporuceniKomadiDTO(List<PreporuceniGornjiDeoDTO> preporuceniGornjiDeoDTO) {
        this.preporuceniGornjiDeoDTO = preporuceniGornjiDeoDTO;
    }

    public PreporuceniKomadiDTO() {}

    public List<PreporuceniGornjiDeoDTO> getPreporuceniGornjiDeoDTO() {
        return preporuceniGornjiDeoDTO;
    }

    public void setPreporuceniGornjiDeoDTO(List<PreporuceniGornjiDeoDTO> preporuceniGornjiDeoDTO) {
        this.preporuceniGornjiDeoDTO = preporuceniGornjiDeoDTO;
    }
}
