import { Component, OnInit } from '@angular/core';
import {ZahtevService} from '../../../services/zahtev-service/zahtev.service';
import {MatSnackBar} from '@angular/material/snack-bar';
import { FormBuilder, FormGroup } from '@angular/forms';

declare var require: any;

@Component({
  selector: 'app-zahtevi',
  templateUrl: './zahtevi.component.html',
  styleUrls: ['./zahtevi.component.css']
})
export class ZahteviComponent implements OnInit {

  zahtevi = [];

  form: FormGroup;
  constructor(private zahtevService: ZahtevService,
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
        this.snackBar.open('Something went wrong!', 'Ok', { duration: 2000 });
      }
    );
  }

  renderZahtevi = (result) => {
    this.zahtevi = [];
    const newList = [];
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
  }

  onTekstChanged(newValue: any){
    console.log(newValue.value)
    this.zahtevService.getPretragaTekst(newValue.value).subscribe(
      result => {
        this.renderZahtevi(result);    
      },
      error => {
        this.snackBar.open('Something went wrong!', 'Ok', { duration: 2000 });
      }
    );
  }

  onSubmitClicked() {
    console.log(this.form.controls.mesto.value)
    console.log(this.form.controls.organVlasti.value)
    console.log(this.form.controls.userEmail.value)
    console.log(this.form.controls.datumAfter.value)
    console.log(this.form.controls.datumBefore.value)
    this.zahtevService.getPretragaMetadata(this.form.controls.datumAfter.value, this.form.controls.datumBefore.value, this.form.controls.mesto.value, this.form.controls.organVlasti.value, this.form.controls.userEmail.value).subscribe(
      result => {
        this.renderZahtevi(result);    
      },
      error => {
        this.snackBar.open('Something went wrong!', 'Ok', { duration: 2000 });
      }
    );
  }

  onDatumAfterChanged(event) {
    this.form.controls.datumAfter.patchValue(new Date(event.target.value).toISOString().split('T')[0]);
  }

  onDatumBeforeChanged(event) {
    this.form.controls.datumBefore.patchValue(new Date(event.target.value).toISOString().split('T')[0]);
  }

  xhtml($event: number) {

  }

  pdf($event: number) {

  }
}
