import { Component, OnInit } from '@angular/core';
import {IzjasnjavanjeService} from '../../../services/izjasnjavanje-service/izjasnjavanje.service';
import {MatSnackBar} from '@angular/material/snack-bar';

@Component({
  selector: 'app-izjasnjavanja',
  templateUrl: './izjasnjavanja.component.html',
  styleUrls: ['./izjasnjavanja.component.css']
})
export class IzjasnjavanjaComponent implements OnInit {
  izjasnjavanja = [];

  constructor(private izjasnjavanjeService: IzjasnjavanjeService,
              private snackBar: MatSnackBar) { }

  ngOnInit(): void {
    const newList = [];
    this.izjasnjavanjeService.getIzjasnjavanjeList().subscribe(
      result => {
        // @ts-ignore
        const convert = require('xml-js');
        const izjasnjavanjeList = JSON.parse(convert.xml2json(result, {compact: true, spaces: 4}));
        console.log(izjasnjavanjeList);
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

}
