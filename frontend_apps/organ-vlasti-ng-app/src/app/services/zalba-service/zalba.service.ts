import { Injectable } from '@angular/core';
import {HttpClient, HttpHeaders} from '@angular/common/http';
import {Observable} from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class ZalbaService {
  private headers = new HttpHeaders({'Content-Type': 'application/xml'});

  constructor(private http: HttpClient) { }

  convertZalbaCutanjeXHTML(s: string): Observable<any> {
    return this.http.get('http://localhost:8090//zalbe/cutanjeDownload/' + s,
      {headers: this.headers, responseType: 'blob'});
  }

  convertZalbaOdlukaXHTML(s: string): Observable<any> {
    return this.http.get('http://localhost:8085/zalbe/odlukaDownload/' + s,
      {headers: this.headers, responseType: 'blob'});
  }

}
