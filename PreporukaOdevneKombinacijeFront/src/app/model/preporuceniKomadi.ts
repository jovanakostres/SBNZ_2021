import { PreporuceniKomad } from "./preporuceniKomad";

export class PreporuceniKomadi{
    preporuceniGornjiDeoDTO: PreporuceniKomad[];
    preporuceniDonjiDeoDTO: PreporuceniKomad[];
    preporucenaJaknaDTO: PreporuceniKomad[];
    preporucenaObucaDTO: PreporuceniKomad[];

    constructor(preporuceniGornjiDeoDTO: PreporuceniKomad[], preporuceniDonjiDeoDTO: PreporuceniKomad[], preporucenaJaknaDTO: PreporuceniKomad[],preporucenaObucaDTO: PreporuceniKomad[]){
        this.preporuceniGornjiDeoDTO = preporuceniGornjiDeoDTO;
        this.preporuceniDonjiDeoDTO = preporuceniDonjiDeoDTO;
        this.preporucenaJaknaDTO = preporucenaJaknaDTO;
        this.preporucenaObucaDTO = preporucenaObucaDTO;
    }
}