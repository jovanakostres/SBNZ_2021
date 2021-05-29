package com.ftn.PreporukaOdevneKombinacije.dto;

import java.util.List;

public class PreporuceniKomadiDTO {

    private List<PreporuceniGornjiDeoDTO> preporuceniGornjiDeoDTO;
    private List<PreporuceniDonjiDeoDTO> preporuceniDonjiDeoDTO;
    private List<PreporucenaJaknaDTO> preporucenaJaknaDTO;
    private List<PreporucenaObucaDTO> preporucenaObucaDTO;


    public PreporuceniKomadiDTO(List<PreporuceniGornjiDeoDTO> preporuceniGornjiDeoDTO) {
        this.preporuceniGornjiDeoDTO = preporuceniGornjiDeoDTO;
    }

    public PreporuceniKomadiDTO(List<PreporuceniGornjiDeoDTO> preporuceniGornjiDeoDTO, List<PreporuceniDonjiDeoDTO> preporuceniDonjiDeoDTO, List<PreporucenaJaknaDTO> preporucenaJaknaDTO, List<PreporucenaObucaDTO> preporucenaObucaDTO) {
        this.preporuceniGornjiDeoDTO = preporuceniGornjiDeoDTO;
        this.preporuceniDonjiDeoDTO = preporuceniDonjiDeoDTO;
        this.preporucenaJaknaDTO = preporucenaJaknaDTO;
        this.preporucenaObucaDTO = preporucenaObucaDTO;
    }

    public PreporuceniKomadiDTO() {}

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
}
