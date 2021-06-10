import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { FilterOdeca } from 'src/app/model/filter';
import { PreporuceniKomad } from 'src/app/model/preporuceniKomad';
import { PreporuceniKomadi } from 'src/app/model/preporuceniKomadi';
import { AuthentificationService } from 'src/app/service/authentification.service';
import { KomadOdeceLogedinService } from 'src/app/service/komad-odece-logedin.service';

@Component({
  selector: 'app-prikaz-odece',
  templateUrl: './prikaz-odece.component.html',
  styleUrls: ['./prikaz-odece.component.scss']
})
export class PrikazOdeceComponent implements OnInit {
  listaOdece : PreporuceniKomad[];
  myForm: FormGroup;

  invalid = true;
  selektovanTip : number = 0;
  praviadmin = false;

  delovi = [
    {value: 'SVE', viewValue: 'SVE'},
    {value: 'GORNJIDEO', viewValue: 'GORNJI DEO'},
    {value: 'DONJIDEO', viewValue: 'DONJI DEO'},
    {value: 'JAKNA', viewValue: 'JAKNA'},
    {value: 'OBUCA', viewValue: 'OBUĆA'}
  ]

  colors = [
    {value: 'SVE', viewValue: 'SVE'},
    {value: 'BELA', viewValue: 'BELA'},
    {value: 'ZUTA', viewValue: 'ŽUTA'},
    {value: 'NARANDZASTA', viewValue: 'NARANDŽASTA'},
    {value: 'PLAVA', viewValue: 'PLAVA'},
    {value: 'ROZA', viewValue: 'ROZA'},
    {value: 'CRVENA', viewValue: 'CRVENA'},
    {value: 'ZELENA', viewValue: 'ZELENA'},
    {value: 'LJUBICASTA', viewValue: 'LJUBIČASTA'},
    {value: 'SIVA', viewValue: 'SIVA'},
    {value: 'BRAON', viewValue: 'BRAON'},
    {value: 'CRNA', viewValue: 'CRNA'}
    //BELA, ZUTA, NARANDZASTA, PLAVA, ROZA, CRVENA, ZELENA, LJUBICASTA, SIVA, BRAON, CRNA
  ];

  materijali = [
    {value: 'SVE', viewValue: 'SVE'},
    {value: 'LAN', viewValue: 'LAN'},
    {value: 'PAMUK', viewValue: 'PAMUK'},
    {value: 'VUNA', viewValue: 'VUNA'},
    {value: 'POLIESTER', viewValue: 'POLIESTER'},
    {value: 'SOMOT', viewValue: 'SOMOT'},
    {value: 'KOZA', viewValue: 'KOŽA'},
    {value: 'SVILA', viewValue: 'SVILA'},
    {value: 'TEKSAS', viewValue: 'TEKSAS'},
    {value: 'KRZNO', viewValue: 'KRZNO'},
    {value: 'PLIS', viewValue: 'PLIŠ'},
    {value: 'CIPKA', viewValue: 'ČIPKA'},
    {value: 'GUMA', viewValue: 'GUMA'}
    //LAN, PAMUK, VUNA, POLIESTER, SOMOT, KOZA, SVILA, TEKSAS, KRZNO, PLIS, CIPKA, GUMA
  ];

  tipovi = [[{value: 'SVE', viewValue: 'SVE'},
  {value: 'BLUZA', viewValue: 'BLUZA'},
  {value: 'KOSULJA', viewValue: 'KOŠULJA'},
  {value: 'MAJICA_DUGI', viewValue: 'MAJICA DUGI RUKAVI'},
  {value: 'MAJICA_KRATKI', viewValue: 'MAJICA KRATKI RUKAVI'},
  {value: 'MAJICA_BRETELE', viewValue: 'MAJICA NA BRETELE'},
  {value: 'DZEMPER', viewValue: 'DŽEMPER'},
  {value: 'DUKS', viewValue: 'DUKS'},
  {value: 'TUNIKA', viewValue: 'TUNIKA'}
],
[{value: 'SVE', viewValue: 'SVE'},
{value: 'PANTALONE', viewValue: 'PANTALONE'},
{value: 'SUKNJA', viewValue: 'SUKNJA'},
{value: 'TRENERKA', viewValue: 'TRENERKA'},
{value: 'HELANKE', viewValue: 'HELANKE'},
{value: 'SORC', viewValue: 'ŠORC'},
{value: 'KRATKE_PANTALONE', viewValue: 'KRATKE PANTALONE'},
{value: 'KRATKA_SUKNJA', viewValue: 'KRATKA SUKNJA'},
{value: 'MAXI_SUKNJA', viewValue: 'MAXI SUKNJA'}
],
[{value: 'SVE', viewValue: 'SVE'},
{value: 'MONTON', viewValue: 'MONTON'},
{value: 'SAKO', viewValue: 'SAKO'},
{value: 'PRSLUK', viewValue: 'PRSLUK'},
{value: 'ZIMSKA_JAKNA', viewValue: 'ZIMSKA JAKNA'},
{value: 'JAKNA_PRELAZNI', viewValue: 'JAKNA PRELAZNI'},
{value: 'BUNDA', viewValue: 'BUNDA'},
{value: 'KARDIGAN', viewValue: 'KARDIGAN'},
{value: 'TRENERKA', viewValue: 'TRENERKA'}
],
[{value: 'SVE', viewValue: 'SVE'},
{value: 'SANDALE', viewValue: 'SANDALE'},
{value: 'CIZME', viewValue: 'ČIZME'},
{value: 'CIPELE', viewValue: 'CIPELE'},
{value: 'PATIKE', viewValue: 'PATIKE'},
{value: 'PAPUCE', viewValue: 'PAPUCE'},
],
  ];

  podtipovi = [
    {value: 'SVE', viewValue: 'SVE'},
    {value: 'SIROKA', viewValue: 'ŠIROKA'},
    {value: 'USKA', viewValue: 'USKA'},
    //LAN, PAMUK, VUNA, POLIESTER, SOMOT, KOZA, SVILA, TEKSAS, KRZNO, PLIS, CIPKA, GUMA
  ];

  pol = [
    {value: 'SVE', viewValue: 'SVE'},
    {value: 'MUSKI', viewValue: 'MUSKI'},
    {value: 'ZENSKI', viewValue: 'ZENSKI'},
  ];

  constructor(private combService: KomadOdeceLogedinService, private fb : FormBuilder, private service: AuthentificationService) {
    this.myForm = this.fb.group({
      'delovi': ['SVE', [Validators.required]],
      'colors': ['SVE', [Validators.required]],
      'materijali': ['SVE', [Validators.required]],
      'tipovi': [{value: "SVE",disabled: true}, [Validators.required] ],
      'pol': [{value: "SVE",disabled: false}, [Validators.required] ],
      'podtipovi': [{value: "SVE",disabled: true}, [Validators.required] ],
    })
    //this.myForm.get('delovi').setValue(this.delovi[0].value);
    //this.myForm.get('colors').setValue(this.colors[0].value);
    //this.myForm.get('materijali').setValue(this.materijali[0].value);
   }

  ngOnInit(): void {
    console.log(this.service.isPraviAdmin());
    if(this.service.isPraviAdmin() == "praviadmin@admin.com") 
    {
      console.log("OKK");
      this.praviadmin = true;
    }
    this.combService.getAll().subscribe(
      result => {
        console.log(result.body)
        this.listaOdece = result.body; 
        console.log("OK");
      },
      err => {
        console.log("ERROR");
      }
    )

  }

  onSubmit(form) {
    
    var unos = new FilterOdeca(this.myForm.get('delovi').value, this.myForm.get('colors').value, this.myForm.get('materijali').value, this.myForm.get('tipovi').value, this.myForm.get('podtipovi').value, this.myForm.get('pol').value);
    console.log(unos);


    this.combService.postFilter(unos).subscribe(
      result => {
        this.listaOdece = result.body; 
        console.log(this.listaOdece);
        console.log("OK");
      },
      err => {
        console.log("ERROR");
      }
    )
    
  } 


  changed(){
    this.invalid = false;
  }

  changedTip(value : any){
    if(value === "GORNJIDEO"){
      this.selektovanTip = 0;
      console.log(this.tipovi[0][0].value);
      this.myForm.get('tipovi').setValue(this.tipovi[0][0].value);
      this.myForm.get('tipovi').enable();

      this.myForm.get('podtipovi').setValue(this.podtipovi[0].value);
      this.myForm.get('podtipovi').enable();
    }
    else if(value === "DONJIDEO"){
      this.selektovanTip = 1;
      this.myForm.get('tipovi').setValue(this.tipovi[1][0].value);
      this.myForm.get('tipovi').enable();

      this.myForm.get('podtipovi').setValue(this.podtipovi[0].value);
      this.myForm.get('podtipovi').enable();
    }
    else if(value === "JAKNA"){
      this.selektovanTip = 2;
      this.myForm.get('tipovi').setValue(this.tipovi[2][0].value);
      this.myForm.get('tipovi').enable();

      this.myForm.get('podtipovi').setValue(this.podtipovi[0].value);
      this.myForm.get('podtipovi').enable();
    }
    else if(value === "OBUCA"){
      this.selektovanTip = 3;
      this.myForm.get('tipovi').setValue(this.tipovi[3][0].value);
      this.myForm.get('tipovi').enable();

      this.myForm.get('podtipovi').setValue(this.podtipovi[0].value);
      this.myForm.get('podtipovi').disable();
    }
    else if(value === "SVE"){
      this.selektovanTip = 0;
      this.myForm.get('tipovi').setValue(this.tipovi[0][0].value);
      this.myForm.get('tipovi').disable();

      this.myForm.get('podtipovi').setValue(this.podtipovi[0].value);
      this.myForm.get('podtipovi').disable();
    }
  }

}
