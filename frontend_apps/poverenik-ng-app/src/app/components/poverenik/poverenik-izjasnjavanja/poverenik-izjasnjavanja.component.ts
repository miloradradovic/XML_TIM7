import { Component, OnInit } from '@angular/core';
import {IzjasnjavanjaService} from '../../../services/izjasnjavanja-service/izjasnjavanja.service';
import {MatSnackBar} from '@angular/material/snack-bar';

@Component({
  selector: 'app-poverenik-izjasnjavanja',
  templateUrl: './poverenik-izjasnjavanja.component.html',
  styleUrls: ['./poverenik-izjasnjavanja.component.css']
})
export class PoverenikIzjasnjavanjaComponent implements OnInit {

  izjasnjavanja = []; // objekti tipa {id: string}
  constructor(private izjasnjavanjeService: IzjasnjavanjaService, private snackBar: MatSnackBar) { }

  ngOnInit(): void {
    const newList = [];
    this.izjasnjavanjeService.getIzjasnjavanjaList().subscribe(
      result => {
        // @ts-ignore
        const convert = require('xml-js');
        const izjasnjavanjeList = JSON.parse(convert.xml2json(result, {compact: true, spaces: 4}));
        const izjasnjavanja = izjasnjavanjeList.messageList['ns2:message'];
        if (izjasnjavanja !== undefined){
          izjasnjavanja.forEach((item, index) => {
            const message = item['ns2:body']._text;
            const messageObject = {id: message};
            newList.push(messageObject);
          });
          this.izjasnjavanja = newList;
        }
      },
      error => {
        this.snackBar.open('Something went wrong!', 'Ok', { duration: 2000 });
      }
    );
  }


  clicked($event: number) {
    console.log($event);
  }
}
