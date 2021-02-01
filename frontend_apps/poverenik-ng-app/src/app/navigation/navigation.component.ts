import { Component, OnInit } from '@angular/core';
import {SignInService} from '../services/sign-in-service/sign-in.service';
import {Router} from '@angular/router';
import {JwtHelperService} from '@auth0/angular-jwt';
import {SignInModel} from '../model/sign-in.model';
import {AuthService} from '../services/auth-service/auth.service';

@Component({
  selector: 'app-navigation',
  templateUrl: './navigation.component.html',
  styleUrls: ['./navigation.component.css']
})
export class NavigationComponent implements OnInit {

  role: string;
  constructor(private signInService: SignInService,
              private authService: AuthService,
              public router: Router) { }

  ngOnInit(): void {
    // this.authService.get
    const user = JSON.parse(localStorage.getItem('user'));
    if (user === null){
      this.role = '';
    }else{
      this.role = user.role;
    }
  }

  signOut($event: any): void {
    localStorage.clear();
    this.role = '';
    localStorage.setItem('role', this.role);
    this.router.navigate(['/sign-in']);
  }
}
