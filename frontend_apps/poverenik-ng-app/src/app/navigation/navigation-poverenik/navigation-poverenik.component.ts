import {Component, EventEmitter, OnInit, Output} from '@angular/core';

@Component({
  selector: 'app-navigation-poverenik',
  templateUrl: './navigation-poverenik.component.html',
  styleUrls: ['./navigation-poverenik.component.css']
})
export class NavigationPoverenikComponent implements OnInit {

  @Output() signOut = new EventEmitter<void>();
  constructor() { }

  ngOnInit(): void {
  }

  signOutUser(): void {
    this.signOut.emit();
  }
}
