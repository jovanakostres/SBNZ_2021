import { PreporuceniKomad } from "./preporuceniKomad";
import { PreporuceniKomadi } from "./preporuceniKomadi";

export class Kombinacija{
    gornjiDeo: PreporuceniKomad;
    donjiDeo: PreporuceniKomad;
    jakna: PreporuceniKomad;
    obuca: PreporuceniKomad;
    cnt : number;
    vreme : Date;

    constructor(gornjiDeo : PreporuceniKomad, donjiDeo : PreporuceniKomad, jakna : PreporuceniKomad, obuca : PreporuceniKomad, cnt?:number, vreme?:number){
        this.gornjiDeo = gornjiDeo;
        this.donjiDeo = donjiDeo;
        this.jakna = jakna
        this.obuca = obuca;
        this.cnt = cnt;
        this.vreme = new Date(vreme);
    }
    
}