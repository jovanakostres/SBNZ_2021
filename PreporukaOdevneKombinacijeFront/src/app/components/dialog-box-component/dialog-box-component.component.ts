import { Component, Inject, OnInit, Optional } from '@angular/core';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material/dialog';
import { PreporuceniKomad } from 'src/app/model/preporuceniKomad';
import { PreporuceniKomadi } from 'src/app/model/preporuceniKomadi';

@Component({
  selector: 'app-dialog-box-component',
  templateUrl: './dialog-box-component.component.html',
  styleUrls: ['./dialog-box-component.component.scss']
})
export class DialogBoxComponentComponent implements OnInit {

  // action:string;
  local_data:any;
  gornjiDeo : PreporuceniKomad;
  donjiDeo : PreporuceniKomad;
  jakna : PreporuceniKomad;
  obuca : PreporuceniKomad;

  constructor(public dialogRef: MatDialogRef<DialogBoxComponentComponent>,
    //@Optional() is used to prevent error if no data is passed
    @Optional() @Inject(MAT_DIALOG_DATA) public data: PreporuceniKomadi) {
    console.log(data);
    this.local_data = {...data};
    // this.action = this.local_data.action; 
    
    
  }

  ngOnInit(): void {
    this.gornjiDeo = this.local_data?.preporuceniGornjiDeoDTO[0];
  
    this.donjiDeo = this.local_data.preporuceniDonjiDeoDTO[0];
   
    this.jakna = this.local_data?.preporucenaJaknaDTO[0];
    this.obuca = this.local_data?.preporucenaObucaDTO[0];
  }

  closeDialog(){
    this.dialogRef.close({event:'Cancel'});
  }
}
