import {Component, OnInit, EventEmitter, Output} from '@angular/core';
import {Router} from '@angular/router';

@Component({
  selector: 'app-navigation-gradjanin',
  templateUrl: './navigation-gradjanin.component.html',
  styleUrls: ['./navigation-gradjanin.component.css']
})
export class NavigationGradjaninComponent implements OnInit {

  @Output() signOut = new EventEmitter<void>();

  constructor(private router: Router) { }

  ngOnInit(): void {
  }

  signOutUser(): void {
    this.signOut.emit();
  }

}
