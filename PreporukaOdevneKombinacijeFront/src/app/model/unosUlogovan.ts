export class UnosUlogovan{
    ramena : number; kukovi : number; struk : number;
    visina : number;
    dressCode : string;
    bojaKoze : string;
    vreme : string;
    pol : string;

    constructor( dressCode : string, bojaKoze : string, vreme : string){
        this.ramena = 0;
        this.kukovi = 0;
        this.struk = 0;
        this.visina = 0;
        this.dressCode = dressCode;
        this.bojaKoze = bojaKoze;
        this.vreme = vreme;
        this.pol = 'ZENSKI';
    }
}