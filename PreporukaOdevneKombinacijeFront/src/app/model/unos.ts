export class Unos{
    mesto: string;
    dressCode: string;
    boje : string[];

    constructor(dresscode:string, mesto: string, boje :string[]){
        this.dressCode = dresscode;
        this.mesto = mesto;
        this.boje = boje;
    }
}