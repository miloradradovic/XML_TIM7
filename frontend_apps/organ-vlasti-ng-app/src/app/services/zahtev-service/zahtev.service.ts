import { Injectable } from '@angular/core';
import {HttpClient, HttpHeaders} from '@angular/common/http';
import {Observable} from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class ZahtevService {
  private headers = new HttpHeaders({'Content-Type': 'application/xml'});

  constructor(private http: HttpClient) { }
  getZahtevList(): Observable<any> {
    return this.http.get('http://localhost:8090/zahtev',
      {headers: this.headers, responseType: 'text'});
  }
  getNeobradjenZahtevList(): Observable<any> {
    return this.http.get('http://localhost:8090/zahtev/neobradjen',
      {headers: this.headers, responseType: 'text'});
  }
  getZahtevListByUser(): Observable<any> {
    return this.http.get('http://localhost:8090/zahtev/by-user',
      {headers: this.headers, responseType: 'text'});
  }
  getPretragaTekst(input): Observable<any> {
    return this.http.get('http://localhost:8090/zahtev/search-text?input=' + input,
    {headers: this.headers, responseType: 'text'});
  }
  getPretragaMetadata(datumAfter, datumBefore, mesto, organVlasti, userEmail): Observable<any> {
    return this.http.get('http://localhost:8090/zahtev/search-metadata?datumAfter=' + datumAfter + '&datumBefore=' + datumBefore + '&mesto=' + mesto + '&organ_vlasti=' + organVlasti + '&userEmail=' + userEmail,
      {headers: this.headers, responseType: 'text'});
  }

  posaljiOdbijanje(toSend: string): Observable<any> {
    return this.http.post('http://localhost:8090/zahtev/odbij', toSend,
      {headers: this.headers, responseType: 'text'});
  }
}
