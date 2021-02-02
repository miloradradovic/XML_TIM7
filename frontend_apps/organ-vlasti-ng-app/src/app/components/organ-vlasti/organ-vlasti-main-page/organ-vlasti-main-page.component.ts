import { Component, OnInit } from '@angular/core';
import {ObavestenjeService} from '../../../services/obavestenje-service/obavestenje.service';
import {MatSnackBar} from '@angular/material/snack-bar';
import {ZahtevService} from '../../../services/zahtev-service/zahtev.service';

@Component({
  selector: 'app-organ-vlasti-main-page',
  templateUrl: './organ-vlasti-main-page.component.html',
  styleUrls: ['./organ-vlasti-main-page.component.css']
})
export class OrganVlastiMainPageComponent implements OnInit {

  zahtevi = [];

  constructor(private zahtevService: ZahtevService,
              private snackBar: MatSnackBar) { }

  ngOnInit(): void {
    const newList = [];
    this.zahtevService.getNeobradjenZahtevList().subscribe(
      result => {
        // @ts-ignore
        const convert = require('xml-js');
        const zahtevList = JSON.parse(convert.xml2json(result, {compact: true, spaces: 4}));
        console.log(zahtevList);
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
  }

  xhtml($event: number) {

  }

  pdf($event: number) {

  }
}
