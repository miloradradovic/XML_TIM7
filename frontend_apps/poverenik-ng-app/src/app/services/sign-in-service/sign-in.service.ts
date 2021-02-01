import { Injectable } from '@angular/core';
import { HttpHeaders, HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';


@Injectable({
  providedIn: 'root'
})
export class SignInService {
  private headers = new HttpHeaders({'Content-Type': 'application/xml'});

  constructor(private http: HttpClient
  ) { }

  signIn(auth: any): Observable<any> {
    return this.http.post('http://localhost:8085/auth/sign-in',
      auth, {headers: this.headers, responseType: 'text'});
  }

  signOut(): void{
    localStorage.removeItem('user');
  }

  isLoggedIn(): boolean {
    if (!localStorage.getItem('user')) {
      return false;
    }
    return true;
  }

}
