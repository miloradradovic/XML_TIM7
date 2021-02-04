import { Component, OnInit } from '@angular/core';
import {IzjasnjavanjeService} from '../../../services/izjasnjavanje-service/izjasnjavanje.service';
import {MatSnackBar} from '@angular/material/snack-bar';
import {ResenjeService} from '../../../services/resenje-service/resenje.service';
import { FormBuilder, FormGroup } from '@angular/forms';

declare var require: any;

@Component({
  selector: 'app-neprocitana-resenja',
  templateUrl: './neprocitana-resenja.component.html',
  styleUrls: ['./neprocitana-resenja.component.css']
})
export class NeprocitanaResenjaComponent implements OnInit {

  resenja = [];
  form: FormGroup;
  constructor(private resenjeService: ResenjeService,
              private snackBar: MatSnackBar,
              private fb: FormBuilder) {
                this.form = this.fb.group({
                  mesto: [''],
                  organVlasti: [''],
                  trazilac: [''],
                  poverenik: [''],
                  zalba: [''],
                  tip: [''],
                  datumAfter: [''],
                  datumBefore: [''],
                });
               }

  ngOnInit(): void {
    const newList = [];
    this.resenjeService.getResenjeListByStatus('ne').subscribe(
      result => {
        // @ts-ignore
        const convert = require('xml-js');
        const resenjeList = JSON.parse(convert.xml2json(result, {compact: true, spaces: 4}));
        console.log(resenjeList);
        const lista = resenjeList.resenjeRefList['ns2:resenje_ref'];
        if (lista !== undefined){
          try {
            lista.forEach((item, index) => {
              const idResenja = item['ns2:body']._attributes.broj;
              const resenje = {id: idResenja};
              newList.push(resenje);
            });
          } catch (err) {
            const idResenja = lista['ns2:body']._attributes.broj;
            const resenje = {id: idResenja};
            newList.push(resenje);
          }
          this.resenja = newList;
        }
      },
      error => {
        this.snackBar.open('Something went wrong!', 'Ok', { duration: 2000 });
      }
    );
  }

  renderResenja = (result) => {
    this.resenja = [];
    const newList = [];
    const convert = require('xml-js');
    const resenjeList = JSON.parse(convert.xml2json(result, {compact: true, spaces: 4}));
    console.log(resenjeList);
    const lista = resenjeList.resenjeRefList['ns2:resenje_ref'];
    if (lista !== undefined){
      try {
        lista.forEach((item, index) => {
          const idResenja = item['ns2:body']._attributes.broj;
          const resenje = {id: idResenja};
          newList.push(resenje);
        });
      } catch (err){
        const idResenja = lista['ns2:body']._attributes.broj;
        const resenje = {id: idResenja};
        newList.push(resenje);
      }
      this.resenja = newList;
    }
  }

  onTekstChanged(newValue: any){
    console.log(newValue.value)
    this.resenjeService.getPretragaTekst('ne', newValue.value).subscribe(
      result => {
        this.renderResenja(result);    
      },
      error => {
        this.snackBar.open('Something went wrong!', 'Ok', { duration: 2000 });
      }
    );
  }

  onSubmitClicked() {
    console.log(this.form.controls.mesto.value)
    console.log(this.form.controls.organVlasti.value)
    console.log(this.form.controls.poverenik.value)
    console.log(this.form.controls.trazilac.value)
    console.log(this.form.controls.zalba.value)
    console.log(this.form.controls.tip.value)
    console.log(this.form.controls.datumAfter.value)
    console.log(this.form.controls.datumBefore.value)
    this.resenjeService.getPretragaMetadata('ne', this.form.controls.poverenik.value, this.form.controls.trazilac.value, this.form.controls.zalba.value.replace('/', '-'), this.form.controls.datumAfter.value, this.form.controls.datumBefore.value, this.form.controls.tip.value, this.form.controls.organVlasti.value, this.form.controls.mesto.value).subscribe(
      result => {
        this.renderResenja(result);    
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

  clicked($event: number) {

  }

  xhtml($event: number) {

  }

  pdf($event: number) {

  }
}
