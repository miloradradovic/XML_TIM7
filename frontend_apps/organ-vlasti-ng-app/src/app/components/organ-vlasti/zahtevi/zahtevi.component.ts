import { Component, OnInit } from '@angular/core';
import {ZahtevService} from '../../../services/zahtev-service/zahtev.service';
import {MatSnackBar} from '@angular/material/snack-bar';
import { FormBuilder, FormGroup } from '@angular/forms';
import {Router} from '@angular/router';

declare var require: any;

@Component({
  selector: 'app-zahtevi',
  templateUrl: './zahtevi.component.html',
  styleUrls: ['./zahtevi.component.css']
})
export class ZahteviComponent implements OnInit {

  zahtevi = [];

  form: FormGroup;
  constructor(private router: Router, private zahtevService: ZahtevService,
              private snackBar: MatSnackBar,
              private fb: FormBuilder) {
                this.form = this.fb.group({
                  mesto: [''],
                  organVlasti: [''],
                  userEmail: [''],
                  datumAfter: [''],
                  datumBefore: [''],
                });
              }

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
        this.snackBar.open('Нешто није у реду!', 'Ok', { duration: 2000 });
      }
    );
  }

  renderZahtevi = (result) => {
    this.zahtevi = [];
    const newList = [];
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
  }

  onTekstChanged(newValue: any){
    this.zahtevService.getPretragaTekst(newValue.value).subscribe(
      result => {
        this.renderZahtevi(result);
      },
      error => {
        this.snackBar.open('Нешто није у реду!', 'Ok', { duration: 2000 });
      }
    );
  }

  onSubmitClicked() {
    this.zahtevService.getPretragaMetadata(this.form.controls.datumAfter.value, this.form.controls.datumBefore.value, this.form.controls.mesto.value, this.form.controls.organVlasti.value, this.form.controls.userEmail.value).subscribe(
      result => {
        this.renderZahtevi(result);
      },
      error => {
        this.snackBar.open('Нешто није у реду!', 'Ok', { duration: 2000 });
      }
    );
  }

  onDatumAfterChanged(event) {
    this.form.controls.datumAfter.patchValue(new Date(event.target.value).toISOString().split('.')[0]);
  }

  onDatumBeforeChanged(event) {
    this.form.controls.datumBefore.patchValue(new Date(event.target.value).toISOString().split('.')[0]);
  }

  pdf($event: string): void {
    this.zahtevService.convertZahtevPDF($event).subscribe(
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
        this.snackBar.open('Нешто није у реду!', 'Ok', { duration: 2000 });
      });
  }

  xhtml($event: string): void {
    this.zahtevService.convertZahtevXHTML($event).subscribe(
      result => {
        const binaryData = [];
        binaryData.push(result);
        const url = window.URL.createObjectURL(new Blob(binaryData, {type: 'application/pdf'}));
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
        this.snackBar.open('Нешто није у реду!', 'Ok', { duration: 2000 });
      });
  }

  doubleClicked($event: number) {
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
