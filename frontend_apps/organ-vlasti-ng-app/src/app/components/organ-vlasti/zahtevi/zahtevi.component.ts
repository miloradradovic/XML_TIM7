import { Component, OnInit } from '@angular/core';
import {ZahtevService} from '../../../services/zahtev-service/zahtev.service';
import {MatSnackBar} from '@angular/material/snack-bar';

@Component({
  selector: 'app-zahtevi',
  templateUrl: './zahtevi.component.html',
  styleUrls: ['./zahtevi.component.css']
})
export class ZahteviComponent implements OnInit {

  zahtevi = [];

  constructor(private zahtevService: ZahtevService,
              private snackBar: MatSnackBar) { }

  ngOnInit(): void {
    const newList = [];
    this.zahtevService.getZahtevList().subscribe(
      result => {
        // @ts-ignore
        const convert = require('xml-js');
        const zahtevList = JSON.parse(convert.xml2json(result, {compact: true, spaces: 4}));
        console.log(zahtevList);
        const lista = zahtevList.zahtevList['zcir:zahtev'];
        if (lista !== undefined){
          try {
            lista.forEach((item, index) => {
              const idZahteva = item['zcir:zahtev_body']._attributes.id;
              const zahtev = {id: idZahteva};
              newList.push(zahtev);
            });
          } catch (err){
            const idZahteva = lista['zcir:zahtev_body']._attributes.id;
            const zahtev = {id: idZahteva};
            newList.push(zahtev);
          }
          this.zahtevi = newList;
        }
      },
      error => {
        this.snackBar.open('Something went wrong!', 'Ok', { duration: 2000 });
      }
    );
  }

  xhtml($event: number) {

  }

  pdf($event: number) {

  }
}
