import { Injectable } from '@angular/core';
import { Router, CanActivate } from '@angular/router';
import {SignInService} from '../services/sign-in-service/sign-in.service';

@Injectable({
  providedIn: 'root'
})
export class GradjaninGuardService implements CanActivate {

  constructor(
    public auth: SignInService,
    public router: Router
  ) { }

  canActivate(): boolean {
    if (this.auth.getRole() !== 'ROLE_USER') {
      this.router.navigate(['/']);
      return false;
    }
    return true;
  }
}
