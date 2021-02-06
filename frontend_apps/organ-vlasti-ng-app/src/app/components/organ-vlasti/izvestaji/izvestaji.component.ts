import { Component, OnInit } from '@angular/core';
import {IzjasnjavanjeService} from '../../../services/izjasnjavanje-service/izjasnjavanje.service';
import {MatSnackBar} from '@angular/material/snack-bar';
import {IzvestajService} from '../../../services/izvestaj-service/izvestaj.service';
import { FormBuilder, FormGroup } from '@angular/forms';
import {Router} from "@angular/router";

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
              private fb: FormBuilder,
              private router: Router) {
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

  doubleClicked($event: number) {
    this.izvestaji.forEach((item, index) => {
      if (item.id === $event){
        this.router.navigate(['/detaljni-prikaz-izvestaja'], {queryParams: {izvestaj_id: item.id}});
      }
    });
  }

  pdf($event: number): void {
    this.izvestajService.convertIzvestajPDF(String($event)).subscribe(
      result => {
        const binaryData = [];
        binaryData.push(result);
        const url = window.URL.createObjectURL(new Blob(binaryData, {type: 'application/pdf'}));
        const a = document.createElement('a');
        document.body.appendChild(a);
        a.setAttribute('style', 'display: none');
        a.setAttribute('target', 'blank');
        a.href = url;
        a.download = 'izvestaj' + $event + '.pdf';
        a.click();
        window.URL.revokeObjectURL(url);
        a.remove();
      },
      error => {
        this.snackBar.open('Нешто није у реду!', 'Ok', { duration: 2000 });
      });
  }

  xhtml($event: number): void {
    this.izvestajService.convertIzvestajXHTML(String($event)).subscribe(
      result => {
        const binaryData = [];
        binaryData.push(result);
        const url = window.URL.createObjectURL(new Blob(binaryData, {type: 'application/html'}));
        const a = document.createElement('a');
        document.body.appendChild(a);
        a.setAttribute('style', 'display: none');
        a.setAttribute('target', 'blank');
        a.href = url;
        a.download = 'izvestaj' + $event + '.html';
        a.click();
        window.URL.revokeObjectURL(url);
        a.remove();
      },
      error => {
        this.snackBar.open('Нешто није у реду!', 'Ok', { duration: 2000 });
      });
  }

  rdfIzvestaji($event: string) {
    this.izvestajService.convertIzvestajiRDF($event).subscribe(
      result => {
        const binaryData = [];
        binaryData.push(result);
        const url = window.URL.createObjectURL(new Blob(binaryData, {type: 'application/pdf'}));
        const a = document.createElement('a');
        document.body.appendChild(a);
        a.setAttribute('style', 'display: none');
        a.setAttribute('target', 'blank');
        a.href = url;
        a.download = 'obavestenje' + $event + '.rdf';
        a.click();
        window.URL.revokeObjectURL(url);
        a.remove();
      },
      error => {
        this.snackBar.open('Нешто није у реду!', 'Ok', {duration: 2000});
      });
  }

  jsonIzvestaji($event: string) {
    this.izvestajService.convertIzvestajiJSON($event).subscribe(
      result => {
        const binaryData = [];
        binaryData.push(result);
        const url = window.URL.createObjectURL(new Blob(binaryData, {type: 'application/pdf'}));
        const a = document.createElement('a');
        document.body.appendChild(a);
        a.setAttribute('style', 'display: none');
        a.setAttribute('target', 'blank');
        a.href = url;
        a.download = 'obavestenje' + $event + '.json';
        a.click();
        window.URL.revokeObjectURL(url);
        a.remove();
      },
      error => {
        this.snackBar.open('Нешто није у реду!', 'Ok', {duration: 2000});
      });
  }
}
