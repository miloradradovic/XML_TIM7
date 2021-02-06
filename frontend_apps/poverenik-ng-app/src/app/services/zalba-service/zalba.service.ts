import { Injectable } from '@angular/core';
import {HttpClient, HttpHeaders} from '@angular/common/http';
import {Observable} from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class ZalbaService {
  private headers = new HttpHeaders({'Content-Type': 'application/xml'});

  constructor(private http: HttpClient) { }

  getOneZalbaCutanje(id: string): Observable<any> {
    return this.http.get('http://localhost:8085/zalba-cutanje/',
      {headers: this.headers, responseType: 'text'});
  }

  getOneZalbaOdluka(id: string): Observable<any> {
    return this.http.get('http://localhost:8085/zalba-odluka/',
      {headers: this.headers, responseType: 'text'});
  }

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
    return this.http.get('http://localhost:8085/zalba-cutanje/by-status',
      {headers: this.headers, responseType: 'text'});
  }
  getNeobradjeneAndUObradiZalbeOdluka(): Observable<any> {
    return this.http.get('http://localhost:8085/zalba-odluka/by-status',
      {headers: this.headers, responseType: 'text'});
  }
    posaljiPonistavanje(toSend: string): Observable<any> {
    return this.http.post('http://localhost:8085/users/ponisti', toSend,
    {headers: this.headers, responseType: 'text'});
  }
  ponisti($event: string): Observable<any> {
    return this.http.put('http://localhost:8085/users/ponisti', $event,
      {headers: this.headers, responseType: 'text'});
  }
  getPretragaTekstZalbeCutanje(input): Observable<any> {
    return this.http.get('http://localhost:8085/zalba-cutanje/search-text?input=' + input,
      {headers: this.headers, responseType: 'text'});
  }
  getPretragaTekstZalbeOdluka(input): Observable<any> {
    return this.http.get('http://localhost:8085/zalba-odluka/search-text?input=' + input,
    {headers: this.headers, responseType: 'text'});
  }
  getPretragaMetadataZalbeCutanje(datumAfter, datumBefore, status, organVlasti, mesto, userEmail, zahtevId): Observable<any> {
    return this.http.get('http://localhost:8085/zalba-cutanje/search-metadata?datumAfter=' + datumAfter + '&datumBefore=' + datumBefore + '&status=' + status + '&mesto=' + mesto + '&organ_vlasti=' + organVlasti + '&userEmail=' + userEmail + '&zahtevId=' + zahtevId,
      {headers: this.headers, responseType: 'text'});
  }
  getPretragaMetadataZalbeOdluka(datumAfter, datumBefore, status, organVlasti, mesto, userEmail, zahtevId): Observable<any> {
    return this.http.get('http://localhost:8085/zalba-odluka/search-metadata?datumAfter='+ datumAfter + '&datumBefore=' + datumBefore + '&status=' + status + '&mesto=' + mesto + '&organ_vlasti=' + organVlasti + '&userEmail=' + userEmail + '&zahtevId=' + zahtevId,
    {headers: this.headers, responseType: 'text'});
  }

  convertZalbaCutanjePDF(broj: string): Observable<any> {
    return this.http.get('http://localhost:8085/zalba-cutanje/toPdf/' + broj,
      {headers: this.headers, responseType: 'blob'});
  }
  convertZalbaCutanjeXHTML(s: string): Observable<any> {
    return this.http.get('http://localhost:8085/zalba-cutanje/toXhtml/' + s,
      {headers: this.headers, responseType: 'blob'});
  }

  convertZalbaOdlukaPDF(broj: string): Observable<any> {
    return this.http.get('http://localhost:8085/zalba-odluka/toPdf/' + broj,
      {headers: this.headers, responseType: 'blob'});
  }
  convertZalbaOdlukaXHTML(s: string): Observable<any> {
    return this.http.get('http://localhost:8085/zalba-odluka/toXhtml/' + s,
      {headers: this.headers, responseType: 'blob'});
  }

  convertZalbaCutanjeRDF(broj: string): Observable<any> {
    return this.http.get('http://localhost:8085/zalba-cutanje/toRdf/' + broj,
      {headers: this.headers, responseType: 'blob'});
  }
  convertZalbaCutanjeJSON(s: string): Observable<any> {
    return this.http.get('http://localhost:8085/zalba-cutanje/toJson/' + s,
      {headers: this.headers, responseType: 'blob'});
  }

  convertZalbaOdlukaRDF(broj: string): Observable<any> {
    return this.http.get('http://localhost:8085/zalba-odluka/toRdf/' + broj,
      {headers: this.headers, responseType: 'blob'});
  }
  convertZalbaOdlukaJSON(s: string): Observable<any> {
    return this.http.get('http://localhost:8085/zalba-odluka/toJson/' + s,
      {headers: this.headers, responseType: 'blob'});
  }
  getResenjeByZalba(s: string): Observable<any> {
    return this.http.get('http://localhost:8085/resenje/by-zalba/' + s,
      {headers: this.headers, responseType: 'text'});
  }

}
