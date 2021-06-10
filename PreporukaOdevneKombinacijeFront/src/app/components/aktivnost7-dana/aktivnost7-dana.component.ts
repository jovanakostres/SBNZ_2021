import { Component, OnInit } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { Kombinacija } from 'src/app/model/kombinacija';
import { PodaciIzvestaj } from 'src/app/model/podaciIzvestaj';
import { PreporuceniKomad } from 'src/app/model/preporuceniKomad';
import { PreporuceniKomadi } from 'src/app/model/preporuceniKomadi';
import { KomadOdeceLogedinService } from 'src/app/service/komad-odece-logedin.service';
import { DialogBoxComponentComponent } from '../dialog-box-component/dialog-box-component.component';

@Component({
  selector: 'app-aktivnost7-dana',
  templateUrl: './aktivnost7-dana.component.html',
  styleUrls: ['./aktivnost7-dana.component.scss']
})
export class Aktivnost7DanaComponent implements OnInit {
  
  listaKombinacija : Kombinacija[];
  data : PodaciIzvestaj;

  dataKomb : PreporuceniKomadi;

  gornjiDeoNajvise : PreporuceniKomad;
  donjiDeoNajvise : PreporuceniKomad;
  jaknaNajvise : PreporuceniKomad;
  obucaNajvise : PreporuceniKomad;

  constructor(private combService: KomadOdeceLogedinService, public dialog: MatDialog) { }

  ngOnInit(): void {
    this.combService.get7MostUsed().subscribe(
      result => {
        console.log(result.body)
        this.gornjiDeoNajvise = result.body.preporuceniGornjiDeoDTO[0];
        this.donjiDeoNajvise = result.body.preporuceniDonjiDeoDTO[0];
        this.jaknaNajvise = result.body.preporucenaJaknaDTO[0];
        this.obucaNajvise = result.body.preporucenaObucaDTO[0];
        console.log("OK");
        // this.obradiKombinacije(this.data); 
        this.combService.get7Combinations().subscribe(
          result => {
            console.log(result.body)
            this.data = result.body; 
            console.log("OK 7");
            this.obradiKombinacije(this.data);
          },
          err => {
            console.log("ERROR");
          }
        )
      },
      err => {
        console.log("ERROR");
      }
    )

    

  }

  obradiKombinacije(dataPodaci: PodaciIzvestaj) {
    this.listaKombinacija = [];
    for (let i = 0; i < dataPodaci.preporuceniKomadiDTO.preporuceniGornjiDeoDTO.length; i++){
      this.listaKombinacija.push(new Kombinacija(dataPodaci.preporuceniKomadiDTO.preporuceniGornjiDeoDTO[i], dataPodaci.preporuceniKomadiDTO.preporuceniDonjiDeoDTO[i],dataPodaci.preporuceniKomadiDTO.preporucenaJaknaDTO[i], dataPodaci.preporuceniKomadiDTO.preporucenaObucaDTO[i], i + 1, parseInt(dataPodaci.vreme[i*4])));
    }
  }

  otvoriDijalog(Id : number){
    this.combService.getCombinationsWithId(Id).subscribe(
      result => {
        console.log(result.body)
        this.dataKomb = result.body; 
        console.log("OK Comb");

        const dialogRef = this.dialog.open(DialogBoxComponentComponent, {
          width: '800px',
          data:this.dataKomb
        });

      },
      err => {
        console.log("ERROR");
      }
    )
  }

}
