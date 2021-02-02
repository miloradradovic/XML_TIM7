import { Injectable } from '@angular/core';
import {HttpClient, HttpHeaders} from '@angular/common/http';
import {Observable} from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class AddPoverenikService {

  private headers = new HttpHeaders({'Content-Type': 'application/xml'});

  constructor(private http: HttpClient
  ) { }

  addPoverenik(auth: any): Observable<any> {
    return this.http.post('http://localhost:8085/users',
      auth, {headers: this.headers, responseType: 'text'});
  }
}
