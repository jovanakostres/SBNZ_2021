import { Component, OnInit } from '@angular/core';
import { Validators } from '@angular/forms';
import { FormGroup } from '@angular/forms';
import { FormBuilder } from '@angular/forms';
import { PreporuceniKomadi } from 'src/app/model/preporuceniKomadi';
import { PreporuceniKomadiOpste } from 'src/app/model/preporuceniKomadiOpste';
import { UnosNeul } from 'src/app/model/unosNeulogovan';
import { KomadOpsteService } from 'src/app/service/komad-opste.service';

@Component({
  selector: 'app-unreg-form',
  templateUrl: './unreg-form.component.html',
  styleUrls: ['./unreg-form.component.scss']
})
export class UnregFormComponent implements OnInit {

  myForm: FormGroup;
  step = 1;
  invalid = true;
  dataSource: PreporuceniKomadiOpste = null;
  dataSourceActive : number = 0;
  checkedNumber = 0;
  dresscode = [
    {value: 'LEZERAN', viewValue: 'LEZERAN'},
    {value: 'SPORTSKI', viewValue: 'SPORTSKI'},
    {value: 'BLACKTIE', viewValue: 'BLACKTIE'},
    {value: 'IZLAZAK', viewValue: 'IZLAZAK'},
    {value: 'FORMALAN', viewValue: 'FORMALAN'}
  ];//["LEZERAN", "SPORTSKI", "FORMALAN", "BLACKTIE", "IZLAZAK", "", "ODMOR"];
  bojakoze = [
    {value: 'SUMMER', viewValue: 'SUMMER'},
    {value: 'WINTER', viewValue: 'WINTER'},
    {value: 'SPRING', viewValue: 'SPRING'},
    {value: 'AUTUMN', viewValue: 'AUTUMN'},
    {value: 'SUMMER_DARK', viewValue: 'SUMMER_DARK'},
    {value: 'WINTER_DARK', viewValue: 'WINTER_DARK'},
    {value: 'SPRING_DARK', viewValue: 'SPRING_DARK'},
    {value: 'AUTUMN_DARK', viewValue: 'AUTUMN_DARK'}
  ];
  godisnjedoba = [
    {value: 'leto', viewValue: 'leto'},
    {value: 'prolece', viewValue: 'prolece'},
    {value: 'jesen', viewValue: 'jesen'},
    {value: 'zima', viewValue: 'zima'}
  ]; 
  pol = [
    {value: 'MUSKI', viewValue: 'muski'},
    {value: 'ZENSKI', viewValue: 'zenski'}
  ];  

  boja: string;
  //  SPRING_DARK, AUTUMN_DARK
  //int ramena, int kukovi, int struk, int visina, DressCode dressCode, BojaKoze bojaKoze, String vreme, Pol pol
  
  constructor(private fb : FormBuilder, private unosService: KomadOpsteService) { 
    //this.dresscode 
    this.myForm = this.fb.group({
      'dresscode': ['', [Validators.required]],
      'ramena' : ['', [Validators.required, Validators.pattern("^[0-9]*$")]],
      'kukovi' : ['', [Validators.required, Validators.pattern("^[0-9]*$")]],
      'struk' : ['', [Validators.required, Validators.pattern("^[0-9]*$")]],
      'visina' : ['', [Validators.required, Validators.pattern("^[0-9]*$")]],
      'vreme' : ['', [Validators.required]],
      'pol' : ['', [Validators.required]]
    })
  }

  allComplete: boolean = false;

  ngOnInit(): void {  }


  onSubmit(form) {   
    console.log(form);
    var unos = new UnosNeul(this.myForm.get('ramena').value, this.myForm.get('kukovi').value, this.myForm.get('struk').value, this.myForm.get('visina').value, this.myForm.get('dresscode').value, this.boja, this.myForm.get('vreme').value,this.myForm.get('pol').value  ); 
    console.log(unos);
    this.unosService.getCombination(unos).subscribe(
      result => {
        this.dataSource = result.body; 
        this.dataSourceActive = 1;
        console.log("OK");
      },
      err => {
        console.log("ERROR");
      }
    )
  } 
  
  onKey(event: any) { // without type info
    var r = this.myForm.get('ramena').value;
    var k = this.myForm.get('kukovi').value;
    var s = this.myForm.get('struk').value;
    var v = this.myForm.get('visina').value;
    if(r!="" && k!="" && s!="" && v!="" )
      this.invalid = false;
    else
      this.invalid = true;
  }

  changed(){
    console.log("PROSLO");
    this.invalid = false;
  }

  check(boja){
    this.boja = boja;
    this.invalid = false;
  }

}
