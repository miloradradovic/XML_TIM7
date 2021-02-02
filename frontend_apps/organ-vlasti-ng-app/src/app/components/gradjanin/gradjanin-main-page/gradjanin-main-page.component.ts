import { Component, OnInit } from '@angular/core';
import {ObavestenjeService} from '../../../services/obavestenje-service/obavestenje.service';
import {ZahtevService} from '../../../services/zahtev-service/zahtev.service';
import {MatSnackBar} from '@angular/material/snack-bar';

@Component({
  selector: 'app-gradjanin-main-page',
  templateUrl: './gradjanin-main-page.component.html',
  styleUrls: ['./gradjanin-main-page.component.css']
})
export class GradjaninMainPageComponent implements OnInit {
  zahtevi = [];
  obavestenja = [];
  constructor(private obavestenjeService: ObavestenjeService,
              private zahtevService: ZahtevService,
              private snackBar: MatSnackBar) { }

  ngOnInit(): void {
    const newList = [];
    const newList2 = [];
    this.zahtevService.getZahtevListByUser().subscribe(
      result => {
        // @ts-ignore
        const convert = require('xml-js');
        const zahtevList = JSON.parse(convert.xml2json(result, {compact: true, spaces: 4}));
        const lista = zahtevList.zahtevList['zcir:zahtev'];
        if (lista !== undefined){
          lista.forEach((item, index) => {
            const idZahteva = item['zcir:zahtev_body']._attributes.id;
            const zahtev = {id: idZahteva};
            newList.push(zahtev);
          });
          this.zahtevi = newList;
        }
      },
      error => {
        this.snackBar.open('Something went wrong!', 'Ok', { duration: 2000 });
      }
    );
    this.obavestenjeService.getObavestenjeListByUser().subscribe(
      result => {
        // @ts-ignore
        const convert = require('xml-js');
        const obavestenjeList = JSON.parse(convert.xml2json(result, {compact: true, spaces: 4}));
        console.log(obavestenjeList);
        const lista = obavestenjeList.obavestenjeList['oba:obavestenje'];
        if (lista !== undefined){
          lista.forEach((item, index) => {
            const idObavestenja = item['oba:obavestenje_body']._attributes.id;
            const idZahtevaOba = item['oba:obavestenje_body']._attributes.idZahteva;
            const obavestenje = {id: idObavestenja, idZahteva: idZahtevaOba};
            newList2.push(obavestenje);
          });
          this.obavestenja = newList2;
        }
      },
      error => {
        this.snackBar.open('Something went wrong!', 'Ok', { duration: 2000 });
      }
    );
  }

  convertToPdfZahtev($event: number) {

  }

  convertToXHTMLZahtev($event: number) {

  }

  convertToPdfObavestenje($event: number) {

  }

  convertToXHTMLObavestenje($event: number) {

  }
}
