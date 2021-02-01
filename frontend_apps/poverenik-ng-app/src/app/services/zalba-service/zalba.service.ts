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
  getZalbaCutanjeListByUser(): Observable<any> {
    return this.http.get('http://localhost:8085/zalba-cutanje/by-user',
      {headers: this.headers, responseType: 'text'});
  }
  getZalbaOdlukaListByUser(): Observable<any> {
    return this.http.get('http://localhost:8085/zalba-odluka/by-user',
      {headers: this.headers, responseType: 'text'});
  }
  getNeobradjeneAndUObradiZalbeCutanje(): Observable<any> {
    return this.http.get('http://localhost:8085/zalba-cutanje/not-all',
      {headers: this.headers, responseType: 'text'});
  }
  getNeobradjeneAndUObradiZalbeOdluka(): Observable<any> {
    return this.http.get('http://localhost:8085/zalba-odluka/not-all',
      {headers: this.headers, responseType: 'text'});
  }
}
