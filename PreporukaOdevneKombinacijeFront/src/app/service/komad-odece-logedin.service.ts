import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { IzabranaKombinacija } from '../model/izabranaKombinacija';
import { PreporuceniKomad } from '../model/preporuceniKomad';
import { PreporuceniKomadi } from '../model/preporuceniKomadi';
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


  postRejected(komad : PreporuceniKomad) : Observable<any>{
    let httpOptions = {};

		httpOptions = {
			headers: this.headers,
			observe: 'response',

		};

		return this.http.post<PreporuceniKomad>( this.newsPath + "/reject", komad, httpOptions);
  }

  postAccepted(komad : IzabranaKombinacija) : Observable<any>{
    let httpOptions = {};

		httpOptions = {
			headers: this.headers,
			observe: 'response',

		};

		return this.http.post<IzabranaKombinacija>( this.newsPath + "/accepted", komad, httpOptions);
  }

  get24Combinations() : Observable<any>{
    let httpOptions = {};

		httpOptions = {
			headers: this.headers,
			observe: 'response',

		};

		return this.http.get<PreporuceniKomadi>( this.newsPath + "/report24", httpOptions);
  }

}
