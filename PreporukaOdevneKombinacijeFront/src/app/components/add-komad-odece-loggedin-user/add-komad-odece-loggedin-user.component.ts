import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { AddKomadUser } from 'src/app/model/addKomadUser';
import { KomadOdeceLogedinService } from 'src/app/service/komad-odece-logedin.service';

@Component({
  selector: 'app-add-komad-odece-loggedin-user',
  templateUrl: './add-komad-odece-loggedin-user.component.html',
  styleUrls: ['./add-komad-odece-loggedin-user.component.scss']
})
export class AddKomadOdeceLoggedinUserComponent implements OnInit {
  myForm: FormGroup;

  invalid = true;
  selektovanTip : number = 0;
  image : string;

  delovi = [
    {value: 'GORNJIDEO', viewValue: 'GORNJI DEO'},
    {value: 'DONJIDEO', viewValue: 'DONJI DEO'},
    {value: 'JAKNA', viewValue: 'JAKNA'},
    {value: 'OBUCA', viewValue: 'OBUĆA'}
  ]

  colors = [
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

  tipovi = [[
  {value: 'BLUZA', viewValue: 'BLUZA'},
  {value: 'KOSULJA', viewValue: 'KOŠULJA'},
  {value: 'MAJICA_DUGI', viewValue: 'MAJICA DUGI RUKAVI'},
  {value: 'MAJICA_KRATKI', viewValue: 'MAJICA KRATKI RUKAVI'},
  {value: 'MAJICA_BRETELE', viewValue: 'MAJICA NA BRETELE'},
  {value: 'DZEMPER', viewValue: 'DŽEMPER'},
  {value: 'DUKS', viewValue: 'DUKS'},
  {value: 'TUNIKA', viewValue: 'TUNIKA'}
],
[
{value: 'PANTALONE', viewValue: 'PANTALONE'},
{value: 'SUKNJA', viewValue: 'SUKNJA'},
{value: 'TRENERKA', viewValue: 'TRENERKA'},
{value: 'HELANKE', viewValue: 'HELANKE'},
{value: 'SORC', viewValue: 'ŠORC'},
{value: 'KRATKE_PANTALONE', viewValue: 'KRATKE PANTALONE'},
{value: 'KRATKA_SUKNJA', viewValue: 'KRATKA SUKNJA'},
{value: 'MAXI_SUKNJA', viewValue: 'MAXI SUKNJA'}
],
[
{value: 'MONTON', viewValue: 'MONTON'},
{value: 'SAKO', viewValue: 'SAKO'},
{value: 'PRSLUK', viewValue: 'PRSLUK'},
{value: 'ZIMSKA_JAKNA', viewValue: 'ZIMSKA JAKNA'},
{value: 'JAKNA_PRELAZNI', viewValue: 'JAKNA PRELAZNI'},
{value: 'BUNDA', viewValue: 'BUNDA'},
{value: 'KARDIGAN', viewValue: 'KARDIGAN'},
{value: 'TRENERKA', viewValue: 'TRENERKA'}
],
[
{value: 'SANDALE', viewValue: 'SANDALE'},
{value: 'CIZME', viewValue: 'ČIZME'},
{value: 'CIPELE', viewValue: 'CIPELE'},
{value: 'PATIKE', viewValue: 'PATIKE'},
{value: 'PAPUCE', viewValue: 'PAPUCE'},
]
  ];

  podtipovi = [
    {value: 'SIROKA', viewValue: 'ŠIROKA'},
    {value: 'USKA', viewValue: 'USKA'},
    //LAN, PAMUK, VUNA, POLIESTER, SOMOT, KOZA, SVILA, TEKSAS, KRZNO, PLIS, CIPKA, GUMA
  ];

  vremena = [
    {value: 'SUVO', viewValue: 'SUVO'},
    {value: 'VLAZNO', viewValue: 'VLAŽNO'},
    //LAN, PAMUK, VUNA, POLIESTER, SOMOT, KOZA, SVILA, TEKSAS, KRZNO, PLIS, CIPKA, GUMA
  ];

  constructor(private combService: KomadOdeceLogedinService, private fb : FormBuilder, private router: Router) {
    this.myForm = this.fb.group({
      'delovi': ['', [Validators.required]],
      'colors': ['', [Validators.required]],
      'materijali': ['', [Validators.required]],
      'tipovi': [{value: "",disabled: true}, [Validators.required] ],
      'podtipovi': [{value: "",disabled: true}, [Validators.required] ],
      'vremena': ["", [Validators.required] ],
      
    })
    //this.myForm.get('delovi').setValue(this.delovi[0].value);
    //this.myForm.get('colors').setValue(this.colors[0].value);
    //this.myForm.get('materijali').setValue(this.materijali[0].value);
   }

  ngOnInit(): void {
  }

  onSubmit(form) {    
    var unos = new AddKomadUser(this.myForm.get('colors').value,
    this.myForm.get('materijali').value, 
    this.myForm.get('vremena').value,
    this.image,
    this.myForm.get('tipovi').value, 
    this.myForm.get('delovi').value, 
    this.myForm.get('podtipovi').value);
    console.log(unos);


    this.combService.postUnosKomad(unos).subscribe(
      result => {
        console.log("OK");
        this.router.navigate(['/home-user/prikaz']);
      },
      err => {
        console.log("ERROR");
      }
    )
    
  } 


  handleUpload(event) {
    const file = event.target.files[0];
    const reader = new FileReader();
    reader.readAsDataURL(file);
    reader.onload = () => {
        console.log(reader.result);
        this.image = reader.result.toString();
    };
  }

  changedTip(value : any){
    if(value === "GORNJIDEO"){
      this.selektovanTip = 0;
      console.log(this.tipovi[0][0].value);
      this.myForm.get('tipovi').enable();

      this.myForm.get('podtipovi').enable();
    }
    else if(value === "DONJIDEO"){
      this.selektovanTip = 1;
      this.myForm.get('tipovi').enable();

      this.myForm.get('podtipovi').enable();
    }
    else if(value === "JAKNA"){
      this.selektovanTip = 2;
      this.myForm.get('tipovi').enable();

      this.myForm.get('podtipovi').enable();
    }
    else if(value === "OBUCA"){
      this.selektovanTip = 3;
      this.myForm.get('tipovi').enable();

      this.myForm.get('podtipovi').disable();
      this.myForm.get('podtipovi').setValue("");
    }
    else if(value === ""){
      this.selektovanTip = 0;
      this.myForm.get('tipovi').disable();

      this.myForm.get('podtipovi').disable();
      this.myForm.get('podtipovi').setValue("");
    }
  }

}
