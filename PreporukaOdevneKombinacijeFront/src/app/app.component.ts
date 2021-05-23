import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { AuthentificationService } from './service/authentification.service';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.scss']
})
export class AppComponent {
  title = 'PreporukaOdevneKombinacijeFront';

  constructor(private authService: AuthentificationService,
    private router: Router){}

  loggedIn():boolean{
    if(this.authService.isLoggedIn()){
      return true;
    }
    return false;
  }

  logout():void{
    this.authService.logout();
  }
}
