import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-poverenik-main-page',
  templateUrl: './poverenik-main-page.component.html',
  styleUrls: ['./poverenik-main-page.component.css']
})
export class PoverenikMainPageComponent implements OnInit {

  zalbe = []; // objekti tipa {id: number}
  constructor() { }

  ngOnInit(): void {
    // TODO pozovi metode iz servisa kad se napravi api
    // treba dobaviti sve neobradjene i zalbe u obradi i smjestiti ih u zalbe
  }

  doubleClicked($event: number): void {
    // TODO redirectuje ga na detaljni prikaz zalbe
    console.log('double clicked');
  }
}
