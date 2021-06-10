export class AddDonji{
    boja : string;
    bojaIntenzitet: string;
    materijal: string;
    vreme: string;
    prioritet: number;
    koeficijentOdabira: number;
    image: string;
    aktivan: boolean;
    pol: string;
    duzinaRukava: string;
    izrez: string;
    odecaPodTip: string;
    odecaTip: string;
    odecadTip: string;
    dubina: string;
    duzina: string;
    tipDonjegDela: string;
    odecajTip: string;
    obucaTip: string;
    stikla: string;
    tip: string;

    constructor(boja:string, bojaIntenzitet:string, materijal:string,image: string,pol: string,odecadTip: string, dubina: string,duzina: string,tipDonjegDela: string){
        this.boja = boja;
        this.bojaIntenzitet = bojaIntenzitet;
        this.materijal = materijal;
        this.vreme = "SUVO";
        this.prioritet = 0;
        this.koeficijentOdabira = 1;
        this.image = image;
        this.aktivan = true;
        this.pol = pol;
        this.odecadTip = odecadTip;
        this.dubina = dubina;
        this.duzina = duzina;
        this.tipDonjegDela = tipDonjegDela;
        this.tip = "donji_deo";
    }
    
}