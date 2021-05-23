import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Unos } from '../model/unos';

@Injectable({
  providedIn: 'root'
})
export class KomadOdeceLogedinService {

  private headers = new HttpHeaders({'Content-Type': 'application/json'});

	private readonly newsPath = 'https://localhost:8080/clothes';

  constructor(private http: HttpClient) { }

  getCombination(unos : Unos) : Observable<any>{
    let httpOptions = {};

		httpOptions = {
			headers: this.headers,
			observe: 'response',

		};

		return this.http.post<Unos>( this.newsPath + "/personalized_recommendation/", unos, httpOptions);
  }
}
