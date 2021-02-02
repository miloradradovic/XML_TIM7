import { Injectable } from '@angular/core';
import {HttpClient, HttpHeaders} from '@angular/common/http';
import {Observable} from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class ZahtevService {
  private headers = new HttpHeaders({'Content-Type': 'application/xml'});

  constructor(private http: HttpClient) { }
  getZahtevList(): Observable<any> {
    return this.http.get('http://localhost:8090/zahtev',
      {headers: this.headers, responseType: 'text'});
  }
  getNeobradjenZahtevList(): Observable<any> {
    return this.http.get('http://localhost:8090/zahtev/neobradjen',
      {headers: this.headers, responseType: 'text'});
  }
  getZahtevListByUser(): Observable<any> {
    return this.http.get('http://localhost:8090/zahtev/by-user',
      {headers: this.headers, responseType: 'text'});
  }
}
