import { NgModule } from '@angular/core';
import { Routes, RouterModule, Router } from '@angular/router';
import { Aktivnost24SataComponent } from './components/aktivnost24-sata/aktivnost24-sata.component';
import { Aktivnost7DanaComponent } from './components/aktivnost7-dana/aktivnost7-dana.component';
import { HomeLoggedinUserComponent } from './components/home-loggedin-user/home-loggedin-user.component';
import { HomeComponent } from './components/home/home.component';
import { LoginComponent } from './components/login/login.component';
import { PrikazOdeceComponent } from './components/prikaz-odece/prikaz-odece.component';
import { RegisterFormComponent } from './components/register-form/register-form.component';
import { UnregFormComponent } from './components/unreg-form/unreg-form.component';
import { AuthentificationService } from './service/authentification.service';
import { AddKomadOdeceComponent } from './components/add-komad-odece/add-komad-odece.component';
import { HomeAdminComponent } from './components/home-admin/home-admin.component';
import { AddKomadOdeceLoggedinUserComponent } from './components/add-komad-odece-loggedin-user/add-komad-odece-loggedin-user.component';
import { ResultsOpsteComponent } from './components/results-opste/results-opste.component';
import { RegisterComponent } from './components/register/register.component';
import { PreporukaUlogovanComponent } from './components/preporuka-ulogovan/preporuka-ulogovan.component';

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
        path: 'activity24', // child route path
        component: Aktivnost24SataComponent, // child route component that the router renders
      },
      {
        path: 'activity7', // child route path
        component: Aktivnost7DanaComponent, // child route component that the router renders
      },
      {
        path: 'clothes', // child route path
        component: PrikazOdeceComponent, // child route component that the router renders
      },
      {
        path: 'add-clothes', // child route path
        component: AddKomadOdeceLoggedinUserComponent, // child route component that the router renders
      },
    ],
  },
  { path: "login", 
    component : LoginComponent
  }, //canActivate: [AuthGuard], component: LoginComponent},
  { path: "register-form", component: RegisterFormComponent },
  { path: "opsta-preporuka", component: UnregFormComponent },
  { path: "home-admin", component: HomeAdminComponent },
  { path: "add-odeca", component: AddKomadOdeceComponent },
  { path: "home-admin/prikaz", component: PrikazOdeceComponent },
  { path : "register", component : RegisterComponent},
  { path : "home-user/preporuka-opste", component: PreporukaUlogovanComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule {}
