import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { AddDonji } from 'src/app/model/addDonjiDeo';
import { AddGornji } from 'src/app/model/addGornjiDeo';
import { AddJakna } from 'src/app/model/addJakna';
import { AddObuca } from 'src/app/model/addObuca';
import { KomadOpsteService } from 'src/app/service/komad-opste.service';

@Component({
  selector: 'app-add-komad-odece',
  templateUrl: './add-komad-odece.component.html',
  styleUrls: ['./add-komad-odece.component.scss']
})
export class AddKomadOdeceComponent implements OnInit {

  gornji: boolean;
  donji: boolean;
  jakna: boolean;
  obuca: boolean;
  invalid = true;
  tip = true;
  step = 1;
  myForm: FormGroup;
  image : string;

  tip_odece = [
    {value: 'gornji_deo', viewValue: 'Gornji deo'},
    {value: 'donji_deo', viewValue: 'Donji deo'},
    {value: 'jakna', viewValue: 'Jakna'},
    {value: 'obuca', viewValue: 'Obuca'}
  ];

  colors = [
    {value: 'BELA', viewValue: 'BELA'},
    {value: 'ZUTA', viewValue: 'ZUTA'},
    {value: 'NARANDZASTA', viewValue: 'NARANDZASTA'},
    {value: 'ROZA', viewValue: 'ROZA'},
    {value: 'ZELENA', viewValue: 'ZELENA'},
    {value: 'LJUBICASTA', viewValue: 'LJUBICASTA'},
    {value: 'SIVA', viewValue: 'SIVA'},
    {value: 'CRNA', viewValue: 'CRNA'},
    {value: 'PLAVA', viewValue: 'PLAVA'}
  ];

  bojaint = [{value: 'TAMNA', viewValue: 'TAMNA'},{value: 'SVETLA', viewValue: 'SVETLA'},{value: 'SREDNJE', viewValue: 'SREDNJE'},{value: 'PASTELNA', viewValue: 'PASTELNA'}];

  materijal =[ // LAN, PAMUK, VUNA, POLIESTER, KOZA, SVILA, TEKSAS, KRZNO, PLIS, CIPKA, GUMA
    {value: 'LAN', viewValue: 'LAN'},
    {value: 'PAMUK', viewValue: 'PAMUK'},
    {value: 'VUNA', viewValue: 'VUNA'},
    {value: 'POLIESTER', viewValue: 'POLIESTER'},
    {value: 'KOZA', viewValue: 'KOZA'},
    {value: 'SVILA', viewValue: 'SVILA'},
    {value: 'TEKSAS', viewValue: 'TEKSAS'},
    {value: 'PLIS', viewValue: 'PLIS'},
    {value: 'CIPKA', viewValue: 'CIPKA'},
    {value: 'GUMA', viewValue: 'GUMA'},
    {value: 'KRZNO', viewValue: 'KRZNO'}
  ];

  pol =[{value: 'MUSKI', viewValue: 'MUSKI'},{value: 'ZENSKI', viewValue: 'ZENSKI'}];

  duzinaRukava = [{value: 'DUGI', viewValue: 'DUGI'},{value: 'SREDNJI', viewValue: 'SREDNJI'},{value: 'KRATKI', viewValue: 'KRATKI'},{value: 'BRETELE', viewValue: 'BRETELE'},{value: 'NONE', viewValue: 'NONE'}];

  izrez = [{value: 'DUBOK', viewValue: 'DUBOK'},{value: 'PLITAK', viewValue: 'PLITAK'},{value: 'NONE', viewValue: 'NONE'}];

  dubina = [{value: 'MAXI', viewValue: 'MAXI'},{value: 'MIDI', viewValue: 'MIDI'},{value: 'MINI', viewValue: 'MINI'},{value: 'NONE', viewValue: 'NONE'}];
  
  duzina = [{value: 'MAXI', viewValue: 'MAXI'},{value: 'MIDI', viewValue: 'MIDI'},{value: 'MINI', viewValue: 'MINI'}];

  odecaTip = [{value: 'BLUZA', viewValue: 'BLUZA'},{value: 'KOSULJA', viewValue: 'KOSULJA'},{value: 'MAJICA', viewValue: 'MAJICA'},{value: 'DZEMPER', viewValue: 'DZEMPER'},{value: 'DUKS', viewValue: 'DUKS'},{value: 'TUNIKA', viewValue: 'TUNIKA'}];

  //SANDALE, CIZME, CIPELE, PATIKE, PAPUCE
  obucaTip = [{value: 'SANDALE', viewValue: 'SANDALE'},{value: 'CIZME', viewValue: 'CIZME'},{value: 'CIPELE', viewValue: 'CIPELE'},
  {value: 'PATIKE', viewValue: 'PATIKE'},{value: 'PAPUCE', viewValue: 'PAPUCE'}];

  tipDonjegDela = [ //    PLEATED, TULIP, PENCIL, ALINE, WRAP,TRUMPET, SKINY, BOOTCUT, FLARED, STRAIGHT, BAGGY
    {value: 'PLEATED', viewValue: 'PLEATED'},
    {value: 'TULIP', viewValue: 'TULIP'},
    {value: 'PENCIL', viewValue: 'PENCIL'},
    {value: 'POLIESTER', viewValue: 'POLIESTER'},
    {value: 'ALINE', viewValue: 'ALINE'},
    {value: 'WRAP', viewValue: 'WRAP'},
    {value: 'TRUMPET', viewValue: 'TRUMPET'},
    {value: 'SKINY', viewValue: 'SKINY'},
    {value: 'BOOTCUT', viewValue: 'BOOTCUT'},
    {value: 'FLARED', viewValue: 'FLARED'},
    {value: 'BAGGY', viewValue: 'BAGGY'},
    {value: 'STRAIGHT', viewValue: 'STRAIGHT'},
    {value: 'NONE', viewValues: 'NONE'}
  ];

  stikla = [{value: 'VISOKA', viewValue: 'VISOKA'},{value: 'NISKA', viewValue: 'NISKA'},{value: 'SREDNJA', viewValue: 'SREDNJA'},{value: 'NONE', viewValue: 'NONE'}];

  //PANTALONE, SUKNJA, TRENERKA, HELANKE, SORC
  odecadTip = [{value: 'PANTALONE', viewValue: 'PANTALONE'},{value: 'SUKNJA', viewValue: 'SUKNJA'},{value: 'TRENERKA', viewValue: 'TRENERKA'},{value: 'HELANKE', viewValue: 'HELANKE'},{value: 'SORC', viewValue: 'SORC'}];

  //JAKNA, KAPUT, MONTON, SAKO, PRSLUK, ZIMSKA_JAKNA, JAKNA_PRELAZNI, BUNDA, KARDIGAN, TRENERKA
  odecajTip = [
    {value: 'JAKNA', viewValue: 'JAKNA'},
    {value: 'KAPUT', viewValue: 'KAPUT'},
    {value: 'MONTON', viewValue: 'MONTON'},
    {value: 'SAKO', viewValue: 'SAKO'},
    {value: 'PRSLUK', viewValue: 'PRSLUK'},
    {value: 'ZIMSKA_JAKNA', viewValue: 'ZIMSKA_JAKNA'},
    {value: 'JAKNA_PRELAZNI', viewValue: 'JAKNA_PRELAZNI'},
    {value: 'BUNDA', viewValue: 'BUNDA'},
    {value: 'KARDIGAN', viewValue: 'KARDIGAN'},
    {value: 'TRENERKA', viewValue: 'TRENERKA'}
  ];
  
  constructor(private fb : FormBuilder, private service: KomadOpsteService) {
    this.myForm = this.fb.group({
      'boja': [''],
      'bojaIntenzitet': [''],
      'materijal': [''],
      'pol': [''],  
      'duzinaRukava': [''],
      'izrez': [''],
      'odecaTip': [''],
      'dubina': [''],
      'duzina': [''],
      'tipDonjegDela': [''],
      'obucaTip': [''],
      'stikla': [''],
      'odecadTip': [''],
      'odecajTip': ['']
    })
  }

  ngOnInit(): void {
  }

  changedKomad(event){    
    if(event.value == "gornji_deo")
      this.gornji = true;
    if(event.value == "donji_deo")
      this.donji = true;
    if(event.value == "jakna")
      this.jakna = true;
    if(event.value == "obuca")
      this.obuca = true;
    this.invalid = false;
  }

  changed(){
    console.log("PROSLO");
    this.invalid = false;
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

  onSubmit(form){
    var boja = this.myForm.get('boja').value;
    var bojaIntenzitet = this.myForm.get('bojaIntenzitet').value;
    var materijal = this.myForm.get('materijal').value;
    var pol = this.myForm.get('pol').value;
    var duzinaRukava = this.myForm.get('duzinaRukava').value;
    var izrez = this.myForm.get('izrez').value;
    var odecaTip = this.myForm.get('odecaTip').value;
    var dubina = this.myForm.get('dubina').value;
    var duzina = this.myForm.get('duzina').value;
    var tipDonjegDela = this.myForm.get('tipDonjegDela').value;
    var obucaTip = this.myForm.get('obucaTip').value;
    var stikla = this.myForm.get('stikla').value;
    var odecadTip = this.myForm.get('odecadTip').value;
    var odecajTip = this.myForm.get('odecajTip').value;
    var komad;
    var ind = true;

    if(boja=="" || bojaIntenzitet=="" || materijal==""  || pol==""){
      alert("Nisu sva polja popunjena");
      return;
    }
    if(this.gornji && (duzinaRukava!="" || izrez!="" || odecaTip!=""))
    {
      komad = new AddGornji(boja, bojaIntenzitet, materijal,this.image,pol,duzinaRukava,izrez,odecaTip);
      ind = false;
    }
    if(this.donji && (dubina!="" || duzina!="" || tipDonjegDela!="" || odecadTip!=""))
    {
      komad = new AddDonji(boja, bojaIntenzitet, materijal,this.image,pol,odecadTip, dubina,duzina,tipDonjegDela);
      ind = false;
    }
    if(this.obuca && (obucaTip!="" || stikla!=""))
    {
      komad = new AddObuca(boja, bojaIntenzitet, materijal,this.image,pol,obucaTip, stikla);
      ind = false;
    }
    if(this.jakna && odecajTip!="")
    {
      komad = new AddJakna(boja, bojaIntenzitet, materijal,this.image,pol,odecajTip);
      ind = false;
    }
    if(ind){
      alert("Nisu sva polja popunjena");
      return;
    }

    this.service.addKomad(komad).subscribe(
      result => {
        console.log(result);
        console.log("OK");
      },
      err => {
        console.log("ERROR");
      }
    )

  }

}
