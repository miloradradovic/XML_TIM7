import { Injectable } from '@angular/core';
import {HttpClient, HttpHeaders} from '@angular/common/http';
import {Observable} from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class ZalbaService {
  private headers = new HttpHeaders({'Content-Type': 'application/xml'});

  constructor(private http: HttpClient) { }

  getZalbaOdlukaList(): Observable<any> {
    return this.http.get('http://localhost:8085/zalba-odluka',
      {headers: this.headers, responseType: 'text'});
  }
  getZalbaCutanjeList(): Observable<any> {
    return this.http.get('http://localhost:8085/zalba-cutanje',
      {headers: this.headers, responseType: 'text'});
  }
}
