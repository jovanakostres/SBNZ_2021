import { Component, OnInit } from '@angular/core';
import { Kombinacija } from 'src/app/model/kombinacija';
import { PodaciIzvestaj } from 'src/app/model/podaciIzvestaj';
import { PreporuceniKomadi } from 'src/app/model/preporuceniKomadi';
import { KomadOdeceLogedinService } from 'src/app/service/komad-odece-logedin.service';

@Component({
  selector: 'app-aktivnost24-sata',
  templateUrl: './aktivnost24-sata.component.html',
  styleUrls: ['./aktivnost24-sata.component.scss']
})
export class Aktivnost24SataComponent implements OnInit {

  listaKombinacija : Kombinacija[];
  data : PodaciIzvestaj;

  constructor(private combService: KomadOdeceLogedinService) { }

  ngOnInit(): void {

    this.combService.get24Combinations().subscribe(
      result => {
        console.log(result.body)
        this.data = result.body; 
        console.log("OK");
        this.obradiKombinacije(this.data);
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

}


