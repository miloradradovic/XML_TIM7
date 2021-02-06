import { Component, OnInit } from '@angular/core';
import {ObavestenjeService} from '../../../services/obavestenje-service/obavestenje.service';
import {ZahtevService} from '../../../services/zahtev-service/zahtev.service';
import {MatSnackBar} from '@angular/material/snack-bar';
import {Router} from '@angular/router';

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
              private snackBar: MatSnackBar,
              private router: Router) { }

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
    this.obavestenjeService.getObavestenjeListByUser().subscribe(
      result => {
        // @ts-ignore
        const convert = require('xml-js');
        const obavestenjeList = JSON.parse(convert.xml2json(result, {compact: true, spaces: 4}));
        const lista = obavestenjeList.obavestenjeList['oba:obavestenje'];
        if (lista !== undefined){
          try {
            lista.forEach((item, index) => {
              const idObavestenja = item['oba:obavestenje_body']._attributes.id;
              const idZahtevaOba = item['oba:obavestenje_body']._attributes.idZahteva;
              const obavestenje = {id: idObavestenja, idZahteva: idZahtevaOba};
              newList2.push(obavestenje);
            });
          } catch (err){
            const idObavestenja = lista['oba:obavestenje_body']._attributes.id;
            const idZahtevaOba = lista['oba:obavestenje_body']._attributes.idZahteva;
            const obavestenje = {id: idObavestenja, idZahteva: idZahtevaOba};
            newList2.push(obavestenje);
          }
          this.obavestenja = newList2;
        }
      },
      error => {
        this.snackBar.open('Something went wrong!', 'Ok', { duration: 2000 });
      }
    );
  }

  convertToPdfZahtev($event: number): void {
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

  convertToXHTMLZahtev($event: number): void {
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

  convertToPdfObavestenje($event: number): void {
    this.obavestenjeService.convertObavestenjePDF($event.toString()).subscribe(
      result => {
        const binaryData = [];
        binaryData.push(result);
        const url = window.URL.createObjectURL(new Blob(binaryData, {type: 'application/pdf'}));
        const a = document.createElement('a');
        document.body.appendChild(a);
        a.setAttribute('style', 'display: none');
        a.setAttribute('target', 'blank');
        a.href = url;
        a.download = 'obavestenje' + $event + '.pdf';
        a.click();
        window.URL.revokeObjectURL(url);
        a.remove();
      },
      error => {
        this.snackBar.open('Нешто није у реду!', 'Ok', {duration: 2000});
      });
  }

  convertToXHTMLObavestenje($event: number): void {
    this.obavestenjeService.convertObavestenjeXHTML($event.toString()).subscribe(
      result => {
        const binaryData = [];
        binaryData.push(result);
        const url = window.URL.createObjectURL(new Blob(binaryData, {type: 'application/pdf'}));
        const a = document.createElement('a');
        document.body.appendChild(a);
        a.setAttribute('style', 'display: none');
        a.setAttribute('target', 'blank');
        a.href = url;
        a.download = 'obavestenje' + $event + '.html';
        a.click();
        window.URL.revokeObjectURL(url);
        a.remove();
      },
      error => {
        this.snackBar.open('Нешто није у реду!', 'Ok', {duration: 2000});
      });
  }

  doubleClickedObavestenje($event: number): void {
    this.obavestenja.forEach( obavestenje => {
      if (obavestenje.id === $event){
        this.router.navigate(['/detaljni-prikaz-obavestenja'], {queryParams: {broj: obavestenje.id}});
      }
    });
  }

  doubleClickedZahtev($event: number): void {
    this.zahtevi.forEach((item, index) => {
      if (item.id === $event){
        this.router.navigate(['/detaljni-prikaz-zahteva'], {queryParams: {zahtev_id: $event, zahtev_status: item.status}});
      }
    });
  }

  jsonZahtev($event: number) {

  }

  rdfZahtev($event: number) {

  }

  rdfObavestenje($event: number) {

  }

  jsonObavestenje($event: number) {

  }
}
