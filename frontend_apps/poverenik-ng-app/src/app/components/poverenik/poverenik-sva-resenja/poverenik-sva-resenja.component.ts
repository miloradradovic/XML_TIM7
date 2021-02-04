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
          try {
            resenja.forEach((item, index) => {
              const idResenja = item['ra:resenje_body']._attributes.broj;
              const resenje = {id: idResenja};
              newList.push(resenje);
            });
          } catch (err){
            const idResenja = resenja['ra:resenje_body']._attributes.id;
            const resenje = {id: idResenja};
            newList.push(resenje);
          }
          this.resenja = newList;
        }
      },
      error => {
        this.snackBar.open('Something went wrong!', 'Ok', { duration: 2000 });
      }
    );
  }

  convertToPDF($event: number): void {
    this.resenjeService.convertResenjePDF(String($event)).subscribe(
      result => {
        const binaryData = [];
        binaryData.push(result);
        const url = window.URL.createObjectURL(new Blob(binaryData, {type: 'application/pdf'}));
        const a = document.createElement('a');
        document.body.appendChild(a);
        a.setAttribute('style', 'display: none');
        a.setAttribute('target', 'blank');
        a.href = url;
        a.download = 'resenje' + $event + '.pdf';
        a.click();
        window.URL.revokeObjectURL(url);
        a.remove();
      },
      error => {
        this.snackBar.open('Something went wrong!', 'Ok', { duration: 2000 });
      });
  }

  convertToXHTML($event: number): void {
    this.resenjeService.convertResenjeXHTML(String($event)).subscribe(
      result => {
        const binaryData = [];
        binaryData.push(result);
        const url = window.URL.createObjectURL(new Blob(binaryData, {type: 'application/html'}));
        const a = document.createElement('a');
        document.body.appendChild(a);
        a.setAttribute('style', 'display: none');
        a.setAttribute('target', 'blank');
        a.href = url;
        a.download = 'resenje' + $event + '.html';
        a.click();
        window.URL.revokeObjectURL(url);
        a.remove();
      },
      error => {
        this.snackBar.open('Something went wrong!', 'Ok', { duration: 2000 });
      });
  }
}
