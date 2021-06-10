export class AddKomadUser{
    boja : string;
    materijal: string;
    vreme: string;
    image: string;
    pol: string;
    odecaPodTip: string;
    tipKomad: string;
    tip: string;

    constructor(boja:string, materijal:string, vreme:string,image: string, odecaTip : string, tip : string, odecaPodTip?: string){
        this.boja = boja;
        this.materijal = materijal;
        this.vreme = vreme;
        this.image = image;
        this.odecaPodTip = odecaPodTip;
        this.tipKomad = odecaTip;
        this.tip = tip;
    }
    
}