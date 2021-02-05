import { Component, OnInit } from '@angular/core';
import {ResenjeService} from '../../../services/resenje-service/resenje.service';
import {MatSnackBar} from '@angular/material/snack-bar';
import { FormBuilder, FormGroup } from '@angular/forms';

declare var require: any;

@Component({
  selector: 'app-procitana-resenja',
  templateUrl: './procitana-resenja.component.html',
  styleUrls: ['./procitana-resenja.component.css']
})
export class ProcitanaResenjaComponent implements OnInit {

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
    this.resenjeService.getResenjeListByStatus('da').subscribe(
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
          } catch (err){
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
    this.resenjeService.getPretragaTekst('da', newValue.value).subscribe(
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
    this.resenjeService.getPretragaMetadata('da', this.form.controls.poverenik.value, this.form.controls.trazilac.value, this.form.controls.zalba.value.replace('/', '-'), this.form.controls.datumAfter.value, this.form.controls.datumBefore.value, this.form.controls.tip.value, this.form.controls.organVlasti.value, this.form.controls.mesto.value).subscribe(
      result => {
        console.log(result)
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

  pdf($event: number) {

  }

  xhtml($event: number) {

  }

  rdfResenja($event: number) {

  }

  jsonResenja($event: number) {

  }
}
