import { Component, OnInit } from '@angular/core';
import {SignInService} from '../services/sign-in-service/sign-in.service';
import {Router} from '@angular/router';
import {StorageService} from '../services/stogare-service/storage.service';

@Component({
  selector: 'app-navigation',
  templateUrl: './navigation.component.html',
  styleUrls: ['./navigation.component.css']
})
export class NavigationComponent implements OnInit {

  role: string;

  constructor(private storageService: StorageService,
              private signInService: SignInService,
              public router: Router) {
  }

  ngOnInit(): void {
    this.storageService.watchStorage().subscribe(() => {
      const user = JSON.parse(localStorage.getItem('user'));
      if (user === null) {
        this.role = '';
      } else {
        this.role = user.role;
      }
    });

    const user = JSON.parse(localStorage.getItem('user'));
    if (user === null) {
      this.role = '';
    } else {
      this.role = user.role;
    }
  }

  signOut($event: any): void {
    this.signInService.signOut();
  }

}
