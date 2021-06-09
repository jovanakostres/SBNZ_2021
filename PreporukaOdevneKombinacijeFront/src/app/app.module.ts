import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { LoginComponent } from './components/login/login.component';
import { RegisterFormComponent } from './components/register-form/register-form.component';
import { UnregFormComponent } from './components/unreg-form/unreg-form.component';
import { BrowserAnimationsModule, NoopAnimationsModule } from '@angular/platform-browser/animations';
import { MatToolbarModule } from '@angular/material/toolbar';
import { MatTableModule } from '@angular/material/table';
import { GooglePlaceModule } from "ngx-google-places-autocomplete";
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { MatSelectModule } from '@angular/material/select'; 
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatInputModule } from '@angular/material/input';
import { MatCheckboxModule } from '@angular/material/checkbox';
import { MatButtonModule } from '@angular/material/button';
import { MatCardModule} from '@angular/material/card';   
import { MatGridListModule} from '@angular/material/grid-list';   
import { AuthentificationService } from './service/authentification.service';
import { HttpClientModule, HTTP_INTERCEPTORS } from '@angular/common/http';
import { CommonModule } from '@angular/common';  
import { TokenInterceptorService } from './service/token-interceptor.service';
import { KomadOdeceLogedinService } from './service/komad-odece-logedin.service';
import { JwtUtilsService } from './service/jwt-utils.service';
import { JwtHelperService, JwtModule, JWT_OPTIONS } from '@auth0/angular-jwt';
import { HomeComponent } from './components/home/home.component';
import { HomeLoggedinUserComponent } from './components/home-loggedin-user/home-loggedin-user.component';
import { ResultsPersonalizedComponent } from './components/results-personalized/results-personalized.component';
import { MatIconModule} from '@angular/material/icon';
import { Aktivnost24SataComponent } from './components/aktivnost24-sata/aktivnost24-sata.component';
import { AddKomadOdeceComponent } from './components/add-komad-odece/add-komad-odece.component';
import { MatSnackBarModule } from '@angular/material/snack-bar';
import { Aktivnost7DanaComponent } from './components/aktivnost7-dana/aktivnost7-dana.component';
import { MatTooltipModule } from '@angular/material/tooltip';
import { MatDividerModule } from '@angular/material/divider';
import { HomeAdminComponent } from './components/home-admin/home-admin.component';

@NgModule({
  declarations: [
    AppComponent,
    LoginComponent,
    RegisterFormComponent,
    UnregFormComponent,
    HomeComponent,
    HomeLoggedinUserComponent,
    ResultsPersonalizedComponent,
    Aktivnost24SataComponent,
    AddKomadOdeceComponent,
    HomeAdminComponent,
    Aktivnost7DanaComponent
  ],
  imports: [
    CommonModule,
    BrowserModule,
    BrowserAnimationsModule,
    AppRoutingModule,
    NoopAnimationsModule,
    MatToolbarModule,
    MatTableModule,
    GooglePlaceModule,
    FormsModule,
    ReactiveFormsModule,
    MatSelectModule,
    MatFormFieldModule,
    MatInputModule,
    MatCheckboxModule,
    MatButtonModule,
    HttpClientModule,
    MatCardModule,
    MatGridListModule,
    MatIconModule,
    MatSnackBarModule,
    MatTooltipModule,
    MatDividerModule,
    JwtModule.forRoot({
      config: {
        tokenGetter: JSON.parse(localStorage.getItem('currentUser') || '{"token":""}')?.token,
        allowedDomains: ["localhost:4200"],
      },
    }),
  ],
  providers: [AuthentificationService, KomadOdeceLogedinService,JwtUtilsService,
    {
      provide: HTTP_INTERCEPTORS,
      useClass: TokenInterceptorService,
      multi: true
    }],
  bootstrap: [AppComponent]
})
export class AppModule { }
