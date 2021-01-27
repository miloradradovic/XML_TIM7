import { Injectable } from '@angular/core';
import {HttpClient, HttpHeaders} from '@angular/common/http';
import {Observable} from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class SignUpService {

  private headers = new HttpHeaders({'Content-Type': 'application/xml'});

  constructor(private http: HttpClient
  ) { }

  signUp(auth: any): Observable<any> {
    return this.http.post('http://localhost:8085/auth/sign-up',
      auth, {headers: this.headers, responseType: 'text'});
  }
}
