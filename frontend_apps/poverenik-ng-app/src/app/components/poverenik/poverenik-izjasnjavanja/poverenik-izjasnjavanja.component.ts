import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-poverenik-izjasnjavanja',
  templateUrl: './poverenik-izjasnjavanja.component.html',
  styleUrls: ['./poverenik-izjasnjavanja.component.css']
})
export class PoverenikIzjasnjavanjaComponent implements OnInit {

  izjasnjavanja = []; // objekti tipa {id: string}
  constructor() { }

  ngOnInit(): void {
    // TODO pozovi metode iz servisa kad se napravi api
    // treba dobaviti sva izjasnjenja
  }

  clicked($event: number): void {
    // TODO otvori dialog izjasnjenja
    console.log('clicked');
  }

}
