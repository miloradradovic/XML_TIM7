import { Component, OnInit } from '@angular/core';
import {IzvestajService} from '../../../services/izvestaj-service/izvestaj.service';
import {MatSnackBar} from '@angular/material/snack-bar';

@Component({
  selector: 'app-poverenik-procitani-izvestaji',
  templateUrl: './poverenik-procitani-izvestaji.component.html',
  styleUrls: ['./poverenik-procitani-izvestaji.component.css']
})
export class PoverenikProcitaniIzvestajiComponent implements OnInit {

  izvestaji = [];

  constructor(private izvestajService: IzvestajService, private snackBar: MatSnackBar) { }

  ngOnInit(): void {
    const newList = [];
    this.izvestajService.getIzvestajListByStatus('da').subscribe(
      result => {
        // @ts-ignore
        const convert = require('xml-js');
        const izvestajList = JSON.parse(convert.xml2json(result, {compact: true, spaces: 4}));
        const izvestaj = izvestajList.izvestajRefList['ns2:izvestaj_ref'];
        console.log(izvestaj);
        if (izvestaj !== undefined){
          try {
            izvestaj.forEach((item, index) => {
              const izvestajObject = {id: item['ns2:body']._attributes.id};
              newList.push(izvestajObject);
            });
          } catch (err){
            const izvestajObject = {id: izvestaj['ns2:body']._attributes.id};
            newList.push(izvestajObject);
          }
          this.izvestaji = newList;
        }
      },
      error => {
        this.snackBar.open('Something went wrong!', 'Ok', { duration: 2000 });
      }
    );
  }

  doubleClicked($event: number): void {
    console.log($event);
  }

  pdf($event: number) {

  }

  xhtml($event: number) {

  }

}
