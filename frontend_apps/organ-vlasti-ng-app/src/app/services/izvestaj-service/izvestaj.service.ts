import { Injectable } from '@angular/core';
import {HttpClient, HttpHeaders} from '@angular/common/http';
import {Observable} from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class IzvestajService {
  private headers = new HttpHeaders({'Content-Type': 'application/xml'});
  constructor(private http: HttpClient) { }
  generisiIzvestaj(): Observable<any> {
    return this.http.post('http://localhost:8090/izvestaji', '',
      {headers: this.headers, responseType: 'text'});
  }
  getIzvestajList(): Observable<any> {
    return this.http.get('http://localhost:8090/izvestaji',
      {headers: this.headers, responseType: 'text'});
  }
}