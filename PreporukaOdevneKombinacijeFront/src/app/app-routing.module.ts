import { NgModule } from '@angular/core';
import { Routes, RouterModule, Router } from '@angular/router';
import { Aktivnost24SataComponent } from './components/aktivnost24-sata/aktivnost24-sata.component';
import { Aktivnost7DanaComponent } from './components/aktivnost7-dana/aktivnost7-dana.component';
import { HomeLoggedinUserComponent } from './components/home-loggedin-user/home-loggedin-user.component';
import { HomeComponent } from './components/home/home.component';
import { LoginComponent } from './components/login/login.component';
import { RegisterFormComponent } from './components/register-form/register-form.component';
import { UnregFormComponent } from './components/unreg-form/unreg-form.component';
import { AuthentificationService } from './service/authentification.service';

const routes: Routes = [
  { 
    path: '', 
    component: HomeComponent,
  },
  { 
    path: 'home-user', 
    component: HomeLoggedinUserComponent,
    children: [
      {
        path: 'aktivnost24', // child route path
        component: Aktivnost24SataComponent, // child route component that the router renders
      },
      {
        path: 'aktivnost7', // child route path
        component: Aktivnost7DanaComponent, // child route component that the router renders
      },
    ],
  },
  { path: "login", 
    component : LoginComponent
  }, //canActivate: [AuthGuard], component: LoginComponent},
  { path: "register-form", component: RegisterFormComponent },
  { path: "opsta-preporuka", component: UnregFormComponent }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule {}
