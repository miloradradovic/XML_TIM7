import { Injectable } from '@angular/core';
import {HttpClient, HttpHeaders} from '@angular/common/http';
import {Observable} from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class IzjasnjavanjeService {
  private headers = new HttpHeaders({'Content-Type': 'application/xml'});

  constructor(private http: HttpClient) { }

  getIzjasnjavanjeList(): Observable<any> {
    return this.http.get('http://localhost:8090/izjasnjavanje',
      {headers: this.headers, responseType: 'text'});
  }
}
