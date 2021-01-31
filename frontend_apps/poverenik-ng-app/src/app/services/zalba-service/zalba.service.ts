import { Injectable } from '@angular/core';
import {HttpClient, HttpHeaders} from '@angular/common/http';
import {Observable} from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class ZalbaService {
  private headers = new HttpHeaders({'Content-Type': 'application/xml'});

  constructor(private http: HttpClient) { }

  getZalbaCutanjeList(): Observable<any> {
    console.log('USAO SAAAM');
    return this.http.get('http://localhost:8085/zalba-odluka',
      {headers: this.headers, responseType: 'text'});
  }
}
