import { Component, OnInit } from '@angular/core';
import {IzjasnjavanjeService} from '../../../services/izjasnjavanje-service/izjasnjavanje.service';
import {MatSnackBar} from '@angular/material/snack-bar';
import {IzvestajService} from '../../../services/izvestaj-service/izvestaj.service';
import { FormBuilder, FormGroup } from '@angular/forms';

declare var require: any;

@Component({
  selector: 'app-izvestaji',
  templateUrl: './izvestaji.component.html',
  styleUrls: ['./izvestaji.component.css']
})
export class IzvestajiComponent implements OnInit {

  izvestaji = [];
  form: FormGroup;
  constructor(private izvestajService: IzvestajService,
              private snackBar: MatSnackBar,
              private fb: FormBuilder) {
                this.form = this.fb.group({
                  datumAfter: [''],
                  datumBefore: [''],
                });
               }

  ngOnInit(): void {
    const newList = [];
    this.izvestajService.getIzvestajList().subscribe(
      result => {
        // @ts-ignore
        const convert = require('xml-js');
        const izvestajList = JSON.parse(convert.xml2json(result, {compact: true, spaces: 4}));
        const izvestaj = izvestajList.izvestajList['ns4:izvestaj'];
        if (izvestaj !== undefined){
          try{
            izvestaj.forEach((item, index) => {
              const izvestajObject = {id: item['ns4:izvestaj_body']._attributes.id};
              newList.push(izvestajObject);
            });
          } catch (err){
            const izvestajObject = {id: izvestaj['ns4:izvestaj_body']._attributes.id};
            newList.push(izvestajObject);
          }
          this.izvestaji = newList;
        }
      },
      error => {
        this.snackBar.open('Нешто није у реду!', 'Ok', { duration: 2000 });
      }
    );
  }

  renderIzvestaji = (result) => {
    this.izvestaji = [];
    const newList = [];
    const convert = require('xml-js');
    const izvestajList = JSON.parse(convert.xml2json(result, {compact: true, spaces: 4}));
    const izvestaj = izvestajList.izvestajList['ns4:izvestaj'];
    if (izvestaj !== undefined){
      try{
        izvestaj.forEach((item, index) => {
          const izvestajObject = {id: item['ns4:izvestaj_body']._attributes.id};
          newList.push(izvestajObject);
        });
      } catch (err){
        const izvestajObject = {id: izvestaj['ns4:izvestaj_body']._attributes.id};
        newList.push(izvestajObject);
      }
      this.izvestaji = newList;
    }
  }

  onSubmitClicked() {
    this.izvestajService.getPretragaMetadata(this.form.controls.datumAfter.value, this.form.controls.datumBefore.value).subscribe(
      result => {
        this.renderIzvestaji(result);
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

  pdf($event: number) {

  }

  xhtml($event: number) {

  }

  rdfIzvestaji($event: number) {

  }

  jsonIzvestaji($event: number) {

  }
}
