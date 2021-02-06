import { Injectable } from '@angular/core';
import {HttpClient, HttpHeaders} from '@angular/common/http';
import {Observable} from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class ResenjeService {
  private headers = new HttpHeaders({'Content-Type': 'application/xml'});

  constructor(private http: HttpClient) { }
  getResenjeListByStatus(status: string): Observable<any> {
    return this.http.get('http://localhost:8090/resenje/' + status,
      {headers: this.headers, responseType: 'text'});
  }
  getPretragaTekst(status, input): Observable<any> {
    return this.http.get('http://localhost:8090/resenje/search-text/'+ status +'?input=' + input,
    {headers: this.headers, responseType: 'text'});
  }
  getPretragaMetadata(status, poverenik, trazilac, zalba, datumAfter, datumBefore, tip, organVlasti, mesto): Observable<any> {
    return this.http.get('http://localhost:8090/resenje/search-metadata/' + status + '?poverenik=' + poverenik + '&trazilac=' + trazilac + '&zalba=' + zalba + '&datumAfter=' + datumAfter + '&datumBefore=' + datumBefore + '&tip=' + tip + '&organVlasti=' + organVlasti + '&mesto=' + mesto,
      {headers: this.headers, responseType: 'text'});
  }

  convertResenjeXHTML(s: string): Observable<any> {
    return this.http.get('http://localhost:8090/resenje/download/' + s,
      {headers: this.headers, responseType: 'blob'});
  }

  convertResenjePDF(broj: string): Observable<any> {
    return this.http.get('http://localhost:8090/resenje/download/' + broj,
      {headers: this.headers, responseType: 'blob'});
  }

}
