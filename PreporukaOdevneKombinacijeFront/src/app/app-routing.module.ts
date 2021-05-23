import { NgModule } from '@angular/core';
import { Routes, RouterModule, Router } from '@angular/router';
import { LoginComponent } from './components/login/login.component';
import { RegisterFormComponent } from './components/register-form/register-form.component';
import { AuthentificationService } from './service/authentification.service';

const routes: Routes = [
  { path: "login", component : LoginComponent}, //canActivate: [AuthGuard], component: LoginComponent},
  { path: "register-form", component: RegisterFormComponent }

];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule {}
