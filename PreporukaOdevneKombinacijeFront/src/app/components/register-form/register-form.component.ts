import { Component, OnInit } from '@angular/core';
import { FormArray, Validators } from '@angular/forms';
import { FormBuilder, FormControl, FormGroup } from '@angular/forms';
import { Unos } from 'src/app/model/unos';
import { KomadOdeceLogedinService } from 'src/app/service/komad-odece-logedin.service';
//import { PlaceResult } from '@google.maps.places.PlaceResult';

@Component({
  selector: 'app-register-form',
  templateUrl: './register-form.component.html',
  styleUrls: ['./register-form.component.scss']
})
export class RegisterFormComponent implements OnInit {

  myForm: FormGroup;
  step = 1;
  invalid = true;
  checkedNumber = 0;
  dresscode = [
    {value: 'LEZERAN', viewValue: 'LEZERAN'},
    {value: 'SPORTSKI', viewValue: 'SPORTSKI'},
    {value: 'BLACKTIE', viewValue: 'BLACKTIE'},
    {value: 'SPORTSKI', viewValue: 'SPORTSKI'},
    {value: 'IZLAZAK', viewValue: 'IZLAZAK'},
    {value: 'ODMOR', viewValue: 'ODMOR'},
    {value: 'FORMALAN', viewValue: 'FORMALAN'}
  ];//["LEZERAN", "SPORTSKI", "FORMALAN", "BLACKTIE", "IZLAZAK", "", "ODMOR"];
  colors = [
    {name: 'BELA', checked: false, color: 'BELA'},
    {name: 'ZUTA', checked: false, color: 'ZUTA'},
    {name: 'NARANDZASTA', checked: false, color: 'NARANDZASTA'},
    {name: 'PLAVA', checked: false, color: 'PLAVA'},
    {name: 'ROZA', checked: false, color: 'ROZA'},
    {name: 'CRVENA', checked: false, color: 'CRVENA'},
    {name: 'ZELENA', checked: false, color: 'ZELENA'},
    {name: 'LJUBICASTA', checked: false, color: 'LJUBICASTA'},
    {name: 'SIVA', checked: false, color: 'SIVA'},
    {name: 'BRAON', checked: false, color: 'BRAON'},
    {name: 'CRNA', checked: false, color: 'CRNA'}
    //BELA, ZUTA, NARANDZASTA, PLAVA, ROZA, CRVENA, ZELENA, LJUBICASTA, SIVA, BRAON, CRNA
  ];
  
  
  constructor(private fb : FormBuilder, private unosService: KomadOdeceLogedinService) { 
    //this.dresscode 
    this.myForm = this.fb.group({
      'dresscode': ['', [Validators.required]],
      'mesto': ['', [Validators.required]],
      'boje': [[]]
    })
  }

  allComplete: boolean = false;

  ngOnInit(): void {  }


  onSubmit(form) {
    var fa = ["",""];
    var z = 0;
    length = this.colors.length;
    console.log(this.colors)
    for(let i=0; i<length; i++)
      if(this.colors[i].checked == true){
        fa[z] = this.colors[i].color;
        z++;
      }
    
    var unos = new Unos(this.myForm.get('dresscode').value, this.myForm.get('mesto').value, fa);
    console.log(unos);

    this.unosService.getCombination(unos).subscribe(
      result => {
        console.log("OK");
      },
      err => {
        console.log("ERROR");
      }
    )
    
  } 
  
  onKey(event: any) { // without type info
    if(this.myForm.get('mesto').value!="")
      this.invalid = false;
    else
      this.invalid = true;
  }

  changed(){
    this.invalid = false;
  }

  check(item, i){
    console.log(item);
    console.log(i);
    this.colors[i].checked = !this.colors[i].checked;
    var num = 0;
    length = this.colors.length;
    for(i=0; i<length; i++){
      if(this.colors[i].checked == true)
        num++;
    }
    this.checkedNumber = num;
  }

}
