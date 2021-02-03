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
}
