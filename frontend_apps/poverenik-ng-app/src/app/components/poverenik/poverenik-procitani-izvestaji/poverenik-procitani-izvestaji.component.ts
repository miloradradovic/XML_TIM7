import { Component, OnInit } from '@angular/core';
import {IzvestajService} from '../../../services/izvestaj-service/izvestaj.service';
import {MatSnackBar} from '@angular/material/snack-bar';
import { FormBuilder, FormGroup } from '@angular/forms';

declare var require: any;

@Component({
  selector: 'app-poverenik-procitani-izvestaji',
  templateUrl: './poverenik-procitani-izvestaji.component.html',
  styleUrls: ['./poverenik-procitani-izvestaji.component.css']
})
export class PoverenikProcitaniIzvestajiComponent implements OnInit {

  izvestaji = [];
  form: FormGroup;
  constructor(private izvestajService: IzvestajService, private snackBar: MatSnackBar, private fb: FormBuilder) {
    this.form = this.fb.group({
      datumAfter: [''],
      datumBefore: [''],
    });
   }

  ngOnInit(): void {
    const newList = [];
    this.izvestajService.getIzvestajListByStatus('da').subscribe(
      result => {
        // @ts-ignore
        const convert = require('xml-js');
        const izvestajList = JSON.parse(convert.xml2json(result, {compact: true, spaces: 4}));
        const izvestaj = izvestajList.izvestajRefList['ns2:izvestaj_ref'];
        console.log(izvestaj);
        if (izvestaj !== undefined){
          try {
            izvestaj.forEach((item, index) => {
              const izvestajObject = {id: item['ns2:body']._attributes.id};
              newList.push(izvestajObject);
            });
          } catch (err){
            const izvestajObject = {id: izvestaj['ns2:body']._attributes.id};
            newList.push(izvestajObject);
          }
          this.izvestaji = newList;
        }
      },
      error => {
        this.snackBar.open('Something went wrong!', 'Ok', { duration: 2000 });
      }
    );
  }

  renderIzvestaji = (result) => {
    this.izvestaji = [];
    const newList = [];
    const convert = require('xml-js');
    const izvestajList = JSON.parse(convert.xml2json(result, {compact: true, spaces: 4}));
    const izvestaj = izvestajList.izvestajRefList['ns2:izvestaj_ref'];
    console.log(izvestaj);
    if (izvestaj !== undefined){
      try {
        izvestaj.forEach((item, index) => {
          const izvestajObject = {id: item['ns2:body']._attributes.id};
          newList.push(izvestajObject);
        });
      } catch (err){
        const izvestajObject = {id: izvestaj['ns2:body']._attributes.id};
        newList.push(izvestajObject);
      }
      this.izvestaji = newList;
    }
  }

  onSubmitClicked() {
    console.log(this.form.controls.datumAfter.value)
    console.log(this.form.controls.datumBefore.value)
    this.izvestajService.getPretragaMetadata('da', this.form.controls.datumAfter.value, this.form.controls.datumBefore.value).subscribe(
      result => {
        this.renderIzvestaji(result);    
      },
      error => {
        this.snackBar.open('Something went wrong!', 'Ok', { duration: 2000 });
      }
    );
  }

  onDatumAfterChanged(event) {
    this.form.controls.datumAfter.patchValue(new Date(event.target.value).toISOString().split('.')[0]);
  }

  onDatumBeforeChanged(event) {
    this.form.controls.datumBefore.patchValue(new Date(event.target.value).toISOString().split('.')[0]);
  }

  doubleClicked($event: number): void {
    console.log($event);
  }

  pdf($event: number) {

  }

  xhtml($event: number) {

  }

}
