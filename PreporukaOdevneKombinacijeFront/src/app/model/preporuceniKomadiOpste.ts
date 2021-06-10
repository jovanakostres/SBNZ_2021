import { PreporuceniKomad } from "./preporuceniKomad";

export class PreporuceniKomadiOpste{
    preporuceniGornjiDeoDTO: PreporuceniKomad[];
    preporuceniDonjiDeoDTO: PreporuceniKomad[];
    preporucenaJaknaDTO: PreporuceniKomad[];
    preporucenaObucaDTO: PreporuceniKomad[];
    image : string;
    tips : string;

    constructor(preporuceniGornjiDeoDTO: PreporuceniKomad[], preporuceniDonjiDeoDTO: PreporuceniKomad[], preporucenaJaknaDTO: PreporuceniKomad[],preporucenaObucaDTO: PreporuceniKomad[]){
        this.preporuceniGornjiDeoDTO = preporuceniGornjiDeoDTO;
        this.preporuceniDonjiDeoDTO = preporuceniDonjiDeoDTO;
        this.preporucenaJaknaDTO = preporucenaJaknaDTO;
        this.preporucenaObucaDTO = preporucenaObucaDTO;
    }
}