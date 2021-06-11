import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators} from '@angular/forms'
import { Router } from '@angular/router';
import { UserReg } from 'src/app/model/userregister';
import { AuthentificationService } from 'src/app/service/authentification.service';

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.scss']
})
export class RegisterComponent implements OnInit {
  user: UserReg;
  registrationForm: FormGroup;
  wrong: boolean;
  pol = [
    {value: 'MUSKI', viewValue: 'muski'},
    {value: 'ZENSKI', viewValue: 'zenski'}
  ];  

  constructor(
    private fb: FormBuilder,
    private route : Router,
    private regService : AuthentificationService
  ) {
    this.wrong = false; 
    this.user = new UserReg();
    this.registrationForm = this.fb.group({
      'email':['',[Validators.required, Validators.email]],
      'password' : ['', Validators.required],
      'ramena' : ['', [Validators.required, Validators.pattern("^[0-9]*$")]],
      'kukovi' : ['', [Validators.required, Validators.pattern("^[0-9]*$")]],
      'struk' : ['', [Validators.required, Validators.pattern("^[0-9]*$")]],
      'visina' : ['', [Validators.required, Validators.pattern("^[0-9]*$")]],
      'pol' : ['', [Validators.required]]
    });
  }

  ngOnInit(): void {
  }

  
  onSubmit(){
      this.user = new UserReg();
      this.user.email = this.registrationForm.controls['email'].value;
      this.user.password = this.registrationForm.controls['password'].value;
      this.user.pol = this.registrationForm.controls['pol'].value;
      this.user.visina = this.registrationForm.controls['visina'].value;
      this.user.ramena = this.registrationForm.controls['ramena'].value;
      this.user.kukovi = this.registrationForm.controls['kukovi'].value;
      this.user.struk = this.registrationForm.controls['struk'].value;
      console.log(this.user);
      this.regService.signup(this.user).subscribe( 
        result => {
          this.route.navigate(['/']);
        });      
  }

}
