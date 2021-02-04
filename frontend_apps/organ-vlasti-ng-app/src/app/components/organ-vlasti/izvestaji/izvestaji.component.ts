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
        console.log(izvestajList);
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
        /*
        const izjasnjavanja = izjasnjavanjeList.messageList['ns2:message'];
        if (izjasnjavanja !== undefined){
          try {
            izjasnjavanja.forEach((item, index) => {
              const message = item['ns2:body']._text;
              const idMessage = item['ns2:body']._attributes.id;
              const messageObject = {id: idMessage, messageText: message};
              newList.push(messageObject);
            });
          } catch (err){
            const message = izjasnjavanja['ns2:body']._text;
            const idMessage = izjasnjavanja['ns2:body']._attributes.id;
            const messageObject = {id: idMessage, messageText: message};
            newList.push(messageObject);
          }
          this.izjasnjavanja = newList;
        }

         */
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
    console.log(izvestajList);
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
    console.log(this.form.controls.datumAfter.value)
    console.log(this.form.controls.datumBefore.value)
    this.izvestajService.getPretragaMetadata(this.form.controls.datumAfter.value, this.form.controls.datumBefore.value).subscribe(
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

  pdf($event: number) {

  }

  xhtml($event: number) {

  }
}
