import { Injectable } from '@angular/core';
import {HttpClient, HttpHeaders} from '@angular/common/http';
import {Observable} from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class IzjasnjavanjaService {
  private headers = new HttpHeaders({'Content-Type': 'application/xml'});

  constructor(private http: HttpClient) { }
  getIzjasnjavanjaList(): Observable<any> {
    return this.http.get('http://localhost:8085/izjasnjavanje',
      {headers: this.headers, responseType: 'text'});
  }
  sendMessage(id: string): Observable<any> {
    return this.http.post('http://localhost:8085/izjasnjavanje', id,
      {headers: this.headers, responseType: 'text'});
  }
}
