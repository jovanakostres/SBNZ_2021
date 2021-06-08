import { HttpClient, HttpErrorResponse, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Router } from '@angular/router';
import { JwtHelperService } from '@auth0/angular-jwt';
import { BehaviorSubject, Observable, throwError } from 'rxjs';
import { catchError, map, tap } from 'rxjs/operators';
import { JwtUtilsService } from './jwt-utils.service';

export interface AuthResponseData {
  id: string;
  accessToken: string;
  expiresIn: string;
}
//ng serve --ssl --ssl-key C:\Users\Jovana\Desktop\SBNZ_2021\PreporukaOdevneKombinacijeFront\localhost.key --ssl-cert C:\Users\Jovana\Desktop\SBNZ_2021\PreporukaOdevneKombinacijeFront\localhost.crt
export class User {
  constructor(
    private _token: string,
    private _tokenExpirationDate: Date
  ) {}

  get token() {
    if (!this._tokenExpirationDate || new Date() > this._tokenExpirationDate) {
      return null;
    }
    return this._token;
  }
}

@Injectable({
  providedIn: 'root'
})
export class AuthentificationService {
  
  private readonly loginPath = 'https://localhost:8080/auth/login';
  private readonly registrationPath = 'https://localhost:8080/auth/sign-up';
  private readonly registerAdminPath = 'https://localhost:8080/auth/sign-up-admin';
  private readonly usersPath = 'https://localhost:8080/users';
 
  private headers = new HttpHeaders({'Content-Type': 'application/json'});

  constructor(private http: HttpClient, private jwtUtilsService: JwtUtilsService, private jwtHelper : JwtHelperService) { }

  login(username: string, password: string) {
    var headers: HttpHeaders = new HttpHeaders({ 'Content-Type': 'application/json' });
    return this.http.post(this.loginPath, JSON.stringify({ username, password }), { headers }).pipe(
      map((res: any) => {
        let token = res && res['accessToken'];
        let id = res && res['id'];
        if (token) {
          localStorage.setItem('currentUser', JSON.stringify({
            id: id,
            username: username,
            //roles: this.jwtUtilsService.getRoles(token),
            token: token
          }));
          return true;
        }
        else {
          return false;
        }
      }))
      .pipe(catchError((error: any) => {
        if (error.status === 401) {
          return throwError('Ilegal login');
        }
        else {
          return throwError('Server error');
        }
      }));
  }

  getToken(): String {
    var currentUser = JSON.parse(localStorage.getItem('currentUser') || '{}');
    //console.log(currentUser.username == null);
    var token = currentUser && currentUser.token;
    return token ? token : "";
  }

  logout(): void {
    localStorage.removeItem('currentUser');
  }

  isLoggedIn(): boolean {
    //console.log(this.getToken())
    var currentUser = JSON.parse(localStorage.getItem('currentUser') || '{}');
    if(currentUser=="praviadmin@admin.com") {
      console.log("PROSLO");
      return false;
    }
    if (this.getToken() != '' ) return true;
    else return false;
  }

  getCurrentUser() {
    if (localStorage.currentUser) {
      return JSON.parse(localStorage.currentUser);
    }
    else {
      return undefined;
    }
  }

  getUser() : Observable<User> {
    let httpOptions = {};
    let id = JSON.parse(localStorage.getItem('currentUser') || "")?.id;

		httpOptions = {
			headers: this.headers,
			observe: 'body'
    	};
    
		return this.http.get<User>(
			this.usersPath + `/${id}`,
			httpOptions);		
  }
  
  getUserByUsername() : Observable<User> {
    let httpOptions = {};
    let email = JSON.parse(localStorage.getItem('currentUser') || "")?.username;
		httpOptions = {
			headers: this.headers,
			observe: 'body'
    	};
    
		return this.http.get<User>(
			this.usersPath + `/profile/${email}`,
			httpOptions);		
  }

  updateUser(user: User, username: string) : Observable<User> {
    let httpOptions = {};

		httpOptions = {
			headers: this.headers,
			observe: 'body'
    	};
    
		return this.http.put<User>(
			this.usersPath + `/profile/${username}`,user,
			httpOptions);		
  }

  updateUser2(user: User, id: number) : Observable<User> {
    let httpOptions = {};

		httpOptions = {
			headers: this.headers,
			observe: 'body'
    	};
    
		return this.http.put<User>(
			this.usersPath + `/${id}`,user,
			httpOptions);		
  }
  

  public isAuthenticated(): boolean {
    const token = JSON.parse(localStorage?.getItem('currentUser') || "")?.token;
    // Check whether the token is expired and return
    // true or false
    return !this.jwtHelper.isTokenExpired(token);
  }

  

  public isAdmin(): any{
    return JSON.parse(localStorage.getItem('currentUser') || '{}');
  }

  public nesto() : any{
    return JSON.parse(localStorage.getItem('currentUser') || '{}').username;
  }
}
