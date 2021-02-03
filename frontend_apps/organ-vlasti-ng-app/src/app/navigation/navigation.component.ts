import { Component, OnInit } from '@angular/core';
import {SignInService} from '../services/sign-in-service/sign-in.service';
import {Router} from '@angular/router';

@Component({
  selector: 'app-navigation',
  templateUrl: './navigation.component.html',
  styleUrls: ['./navigation.component.css']
})
export class NavigationComponent implements OnInit {

  role: string;
  constructor(private signInService: SignInService,
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
