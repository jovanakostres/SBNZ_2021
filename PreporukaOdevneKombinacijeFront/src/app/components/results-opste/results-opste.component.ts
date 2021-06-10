import { Component, Input, OnInit } from '@angular/core';
import { MatSnackBar } from '@angular/material/snack-bar';
import { Router } from '@angular/router';
import { IzabranaKombinacija } from 'src/app/model/izabranaKombinacija';
import { PreporuceniKomad } from 'src/app/model/preporuceniKomad';
import { PreporuceniKomadi } from 'src/app/model/preporuceniKomadi';
import { PreporuceniKomadiOpste } from 'src/app/model/preporuceniKomadiOpste';
import { KomadOdeceLogedinService } from 'src/app/service/komad-odece-logedin.service';

@Component({
  selector: 'app-results-opste',
  templateUrl: './results-opste.component.html',
  styleUrls: ['./results-opste.component.scss']
})
export class ResultsOpsteComponent implements OnInit {

  @Input() data: PreporuceniKomadiOpste;
  gornjiDeo : PreporuceniKomad;
  donjiDeo : PreporuceniKomad;
  jakna : PreporuceniKomad;
  obuca : PreporuceniKomad;

  countGD : number = 0;
  countDD : number = 0;
  countJakna : number = 0;
  countObuca : number = 0;

  izabrano : IzabranaKombinacija;

  constructor(private komadService: KomadOdeceLogedinService, private router: Router, private _snackBar: MatSnackBar) { }

  ngOnInit(): void {
    this.countGD = 0;
    this.countDD = 0;
    this.countJakna = 0;
    this.countObuca = 0;
    this.gornjiDeo = this.data.preporuceniGornjiDeoDTO[this.countGD];
    this.donjiDeo = this.data.preporuceniDonjiDeoDTO[this.countDD];
    this.jakna = this.data.preporucenaJaknaDTO[this.countJakna];
    this.obuca = this.data.preporucenaObucaDTO[this.countObuca];
    console.log(this.data);
  }

  odbijKomad(komad : PreporuceniKomad): void{
    if(komad.id == this.gornjiDeo.id){
      this.countGD += 1;
      if(this.countGD >= this.data.preporuceniGornjiDeoDTO.length){
        this.countGD = this.data.preporuceniGornjiDeoDTO.length + 1;
        this.gornjiDeo.image = "";
        this.gornjiDeo.id = -1;
      }
      else{
        this.gornjiDeo = this.data.preporuceniGornjiDeoDTO[this.countGD];
      }
    }
    else if(komad.id == this.donjiDeo.id){
      this.countDD += 1;
      if(this.countDD >= this.data.preporuceniDonjiDeoDTO.length){
        this.countDD = this.data.preporuceniDonjiDeoDTO.length + 1;
        this.donjiDeo.id = -1;
        this.donjiDeo.image = "";
      }
      else{
        this.donjiDeo = this.data.preporuceniDonjiDeoDTO[this.countDD];
      }
    }
    else if(komad.id == this.jakna.id){
      this.countJakna += 1;
      if(this.countJakna >= this.data.preporucenaJaknaDTO.length){
        this.countJakna = this.data.preporucenaJaknaDTO.length + 1;
        this.jakna.id = -1;
        this.jakna.image = "";
      }
      else{
        this.jakna = this.data.preporucenaJaknaDTO[this.countJakna];
      }
    }
    else if(komad.id == this.obuca.id){
      this.countObuca += 1;
      if(this.countObuca >= this.data.preporucenaObucaDTO.length){
        this.countObuca = this.data.preporucenaObucaDTO.length + 1;
        this.obuca.id = -1;
        this.obuca.image = "";
      }
      else{
        this.obuca = this.data.preporucenaObucaDTO[this.countObuca];
      }
    }
    this.komadService.postRejected(komad).subscribe(
      result => {
        console.log(result.body);
        console.log("OK");
      },
      err => {
        console.log("ERROR");
      }
    )
  }

  prihvatiKombinaciju(){
    this.izabrano = new IzabranaKombinacija(this.gornjiDeo.id, this.donjiDeo.id, this.jakna.id, this.obuca.id);
    this.komadService.postAccepted(this.izabrano).subscribe(
      result => {
        console.log("OK");
        this._snackBar.open("Uspešno izabrana kombinacija!", "Close");
        this.router.navigate(['/home-user']);
      },
      err => {
        console.log("ERROR");
      }
    )
  }

}
