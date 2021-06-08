import { PreporuceniKomadi } from "./preporuceniKomadi";

export class PodaciIzvestaj{
    preporuceniKomadiDTO: PreporuceniKomadi;
    vreme: string[];

    constructor(preporuceniKomadi : PreporuceniKomadi, vreme : string[]){
        this.preporuceniKomadiDTO = preporuceniKomadi;
        this.vreme = vreme;
    }
    
}