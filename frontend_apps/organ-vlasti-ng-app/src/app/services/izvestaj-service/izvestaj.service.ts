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
  getPretragaMetadata(datumAfter, datumBefore): Observable<any> {
    return this.http.get('http://localhost:8090/izvestaji/search-metadata?datumAfter=' + datumAfter + '&datumBefore=' + datumBefore,
      {headers: this.headers, responseType: 'text'});
  }

  convertIzvestajPDF(broj: string): Observable<any> {
    return this.http.get('http://localhost:8090/izvestaji/toPdf/' + broj,
      {headers: this.headers, responseType: 'blob'});
  }
  convertIzvestajXHTML(s: string): Observable<any> {
    return this.http.get('http://localhost:8090/izvestaji/toXhtml/' + s,
      {headers: this.headers, responseType: 'blob'});
  }
  convertIzvestajiRDF(broj: string): Observable<any> {
    return this.http.get('http://localhost:8090/izvestaji/toRdf/' + broj,
      {headers: this.headers, responseType: 'blob'});
  }
  convertIzvestajiJSON(s: string): Observable<any> {
    return this.http.get('http://localhost:8090/izvestaji/toJson/' + s,
      {headers: this.headers, responseType: 'blob'});
  }
}
