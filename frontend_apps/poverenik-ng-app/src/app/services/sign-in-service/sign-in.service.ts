import { Injectable } from '@angular/core';
import { HttpHeaders, HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import {StorageService} from '../stogare-service/storage.service';


@Injectable({
  providedIn: 'root'
})
export class SignInService {
  private headers = new HttpHeaders({'Content-Type': 'application/xml'});

  constructor(private http: HttpClient, private storageService: StorageService
  ) { }

  signIn(auth: any): Observable<any> {
    return this.http.post('http://localhost:8085/auth/sign-in',
      auth, {headers: this.headers, responseType: 'text'});
  }

  signOut(): void{
    this.storageService.clearStorage();
  }

  getRole(): string {
    if (!localStorage.getItem('user')) {
      return '';
    }
    return JSON.parse(localStorage.getItem('user')).role;
  }

}
