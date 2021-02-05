import { Injectable } from '@angular/core';
import {HttpClient, HttpHeaders} from '@angular/common/http';
import {Observable} from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class ZahtevService {
  private headers = new HttpHeaders({'Content-Type': 'application/xml'});

  constructor(private http: HttpClient) { }

  convertZahtevXHTML(s: string): Observable<any> {
    return this.http.get('http://localhost:8085/zahtev/download/' + s,
      {headers: this.headers, responseType: 'blob'});
  }
}
