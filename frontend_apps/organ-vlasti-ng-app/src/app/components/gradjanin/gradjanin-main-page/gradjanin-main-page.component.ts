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
    this.zahtevService.getZahtevListByUser().subscribe(
      result => {
        // @ts-ignore
        const convert = require('xml-js');
        const zahtevList = JSON.parse(convert.xml2json(result, {compact: true, spaces: 4}));
        console.log(zahtevList);
        /*
        const lista = zalbaCutanjeList.zalbaCutanjeList;
        const zalbe = lista['zc:zalba_odluka'];
        if (zalbe !== undefined){
          zalbe.forEach((item, index) => {
            const idZalbe = item['zc:zalba_odluka_body']._attributes.id;
            const zalba = {id: idZalbe, tip: 'odluka'};
            newList.push(zalba);
          });
        }
        this.zalbe = newList.concat(this.zalbe);

         */
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
        /*
        const lista = zalbaCutanjeList.zalbaCutanjeList;
        const zalbe = lista['zc:zalba_odluka'];
        if (zalbe !== undefined){
          zalbe.forEach((item, index) => {
            const idZalbe = item['zc:zalba_odluka_body']._attributes.id;
            const zalba = {id: idZalbe, tip: 'odluka'};
            newList.push(zalba);
          });
        }
        this.zalbe = newList.concat(this.zalbe);

         */
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
