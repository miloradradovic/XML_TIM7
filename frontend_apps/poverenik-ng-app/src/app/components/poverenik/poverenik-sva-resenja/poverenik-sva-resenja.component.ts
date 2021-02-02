import { Component, OnInit } from '@angular/core';
import {ResenjeService} from '../../../services/resenje-service/resenje.service';
import {MatSnackBar} from '@angular/material/snack-bar';

@Component({
  selector: 'app-poverenik-sva-resenja',
  templateUrl: './poverenik-sva-resenja.component.html',
  styleUrls: ['./poverenik-sva-resenja.component.css']
})
export class PoverenikSvaResenjaComponent implements OnInit {

  resenja = []; // objekti tipa {id: number}
  constructor(private resenjeService: ResenjeService, private snackBar: MatSnackBar) { }

  ngOnInit(): void {
    // TODO pozovi metode iz servisa kad se napravi api
    // treba dobaviti sva resenja
    const newList = [];
    this.resenjeService.getResenjeList().subscribe(
      result => {
        // @ts-ignore
        const convert = require('xml-js');
        const resenjeList = JSON.parse(convert.xml2json(result, {compact: true, spaces: 4}));
        const lista = resenjeList['ra:resenjeList'];
        console.log(lista);
        const resenja = lista['ra:resenje'];
        if (resenja !== undefined){
          resenja.forEach((item, index) => {
            const idResenja = item['ra:resenje_body']._attributes.id;
            const resenje = {id: idResenja};
            newList.push(resenje);
          });
          this.resenja = newList;
        }
      },
      error => {
        this.snackBar.open('Something went wrong!', 'Ok', { duration: 2000 });
      }
    );
  }

  convertToPDF($event: number) {

  }

  convertToXHTML($event: number) {

  }
}
