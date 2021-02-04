import { Injectable } from '@angular/core';
import {HttpClient, HttpHeaders} from '@angular/common/http';
import {Observable} from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class IzvestajService {
  private headers = new HttpHeaders({'Content-Type': 'application/xml'});
  constructor(private http: HttpClient) { }
  getIzvestajListByStatus(procitano: string): Observable<any> {
    return this.http.get('http://localhost:8085/izvestaj/' + procitano,
      {headers: this.headers, responseType: 'text'});
  }
}
