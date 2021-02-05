import { Component, OnInit } from '@angular/core';
import {ObavestenjeService} from '../../../services/obavestenje-service/obavestenje.service';
import {MatSnackBar} from '@angular/material/snack-bar';
import {ZahtevService} from '../../../services/zahtev-service/zahtev.service';
import {Router} from '@angular/router';

@Component({
  selector: 'app-organ-vlasti-main-page',
  templateUrl: './organ-vlasti-main-page.component.html',
  styleUrls: ['./organ-vlasti-main-page.component.css']
})
export class OrganVlastiMainPageComponent implements OnInit {

  zahtevi = [];

  constructor(private zahtevService: ZahtevService,
              private snackBar: MatSnackBar,
              private router: Router) { }

  ngOnInit(): void {
    const newList = [];
    this.zahtevService.getNeobradjenZahtevList().subscribe(
      result => {
        // @ts-ignore
        const convert = require('xml-js');
        const zahtevList = JSON.parse(convert.xml2json(result, {compact: true, spaces: 4}));
        const lista = zahtevList.zahtevList['zcir:zahtev'];
        if (lista !== undefined){
          try {
            lista.forEach((item, index) => {
              const idZahteva = item['zcir:zahtev_body']._attributes.id;
              const statusZahteva = item['zcir:zahtev_body']['zcir:status']._text;
              const zahtev = {id: idZahteva, status: statusZahteva};
              newList.push(zahtev);
            });
          } catch (err){
            const idZahteva = lista['zcir:zahtev_body']._attributes.id;
            const statusZahteva = lista['zcir:zahtev_body']['zcir:status']._text;
            const zahtev = {id: idZahteva, status: statusZahteva};
            newList.push(zahtev);
          }
          this.zahtevi = newList;
        }
      },
      error => {
        this.snackBar.open('Нешто није у реду!', 'Ok', { duration: 2000 });
      }
    );
  }

  pdf($event: number): void {
    this.zahtevService.convertZahtevPDF(String($event)).subscribe(
      result => {
        const binaryData = [];
        binaryData.push(result);
        const url = window.URL.createObjectURL(new Blob(binaryData, {type: 'application/pdf'}));
        const a = document.createElement('a');
        document.body.appendChild(a);
        a.setAttribute('style', 'display: none');
        a.setAttribute('target', 'blank');
        a.href = url;
        a.download = 'zahtev' + $event + '.pdf';
        a.click();
        window.URL.revokeObjectURL(url);
        a.remove();
      },
      error => {
        this.snackBar.open('Something went wrong!', 'Ok', { duration: 2000 });
      });
  }

  xhtml($event: number): void {
    this.zahtevService.convertZahtevXHTML(String($event)).subscribe(
      result => {
        const binaryData = [];
        binaryData.push(result);
        const url = window.URL.createObjectURL(new Blob(binaryData, {type: 'application/html'}));
        const a = document.createElement('a');
        document.body.appendChild(a);
        a.setAttribute('style', 'display: none');
        a.setAttribute('target', 'blank');
        a.href = url;
        a.download = 'zahtev' + $event + '.html';
        a.click();
        window.URL.revokeObjectURL(url);
        a.remove();
      },
      error => {
        this.snackBar.open('Something went wrong!', 'Ok', { duration: 2000 });
      });
  }

  doubleClicked($event: number): void {
    this.zahtevi.forEach((item, index) => {
      if (item.id === $event){
        this.router.navigate(['/detaljni-prikaz-zahteva'], {queryParams: {zahtev_id: $event, zahtev_status: item.status}});
      }
    });
  }

  rdfZahtev($event: number) {

  }

  jsonZahtev($event: number) {

  }
}
