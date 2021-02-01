import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-poverenik-svi-izvestaji',
  templateUrl: './poverenik-svi-izvestaji.component.html',
  styleUrls: ['./poverenik-svi-izvestaji.component.css']
})
export class PoverenikSviIzvestajiComponent implements OnInit {

  izvestaji = []; // objekti tipa {id: number}
  constructor() { }

  ngOnInit(): void {
    // TODO pozovi metode iz servisa kad se napravi api
    // treba dobaviti sve izvestaje
  }

}
