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

  xhtml($event: number) {

  }

  pdf($event: number) {

  }
}
