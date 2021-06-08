import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { PreporuceniKomad } from '../model/preporuceniKomad';
import { UnosNeul } from '../model/unosNeulogovan';

@Injectable({
  providedIn: 'root'
})
export class KomadOpsteService {

    private headers = new HttpHeaders({'Content-Type': 'application/json'});

    private readonly newsPath = 'https://localhost:8080/clothes-unlogged';

    constructor(private http: HttpClient) { }

    getCombination(unos : UnosNeul) : Observable<any>{
        let httpOptions = {};
    
            httpOptions = {
                headers: this.headers,
                observe: 'response',
    
            };
    
            return this.http.post<UnosNeul>( this.newsPath + "/recommendation/", unos, httpOptions);
      }
}
