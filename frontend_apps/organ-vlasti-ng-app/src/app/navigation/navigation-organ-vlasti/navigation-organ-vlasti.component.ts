import {Component, OnInit, Output, EventEmitter} from '@angular/core';

@Component({
  selector: 'app-navigation-organ-vlasti',
  templateUrl: './navigation-organ-vlasti.component.html',
  styleUrls: ['./navigation-organ-vlasti.component.css']
})
export class NavigationOrganVlastiComponent implements OnInit {

  @Output() signOut = new EventEmitter<void>();
  constructor() { }

  ngOnInit(): void {
  }

  signOutUser(): void {
    this.signOut.emit();
  }

}
