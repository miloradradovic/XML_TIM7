import {Component, OnInit} from '@angular/core';
import {ResenjeService} from '../../../services/resenje-service/resenje.service';
import {MatSnackBar} from '@angular/material/snack-bar';
import {ObavestenjeService} from '../../../services/obavestenje-service/obavestenje.service';
import {FormBuilder, FormGroup} from '@angular/forms';

declare var require: any;

@Component({
  selector: 'app-obavestenja',
  templateUrl: './obavestenja.component.html',
  styleUrls: ['./obavestenja.component.css']
})
export class ObavestenjaComponent implements OnInit {

  obavestenja = [];

  form: FormGroup;

  constructor(private obavestenjeService: ObavestenjeService,
              private snackBar: MatSnackBar,
              private fb: FormBuilder) {
    this.form = this.fb.group({
      zahtev: [''],
      organVlasti: [''],
      userEmail: [''],
      datumAfter: [''],
      datumBefore: [''],
    });
  }

  ngOnInit(): void {
    const newList = [];
    this.obavestenjeService.getObavestenjeList().subscribe(
      result => {
        // @ts-ignore
        const convert = require('xml-js');
        const obavestenjeList = JSON.parse(convert.xml2json(result, {compact: true, spaces: 4}));
        const lista = obavestenjeList.obavestenjeList['oba:obavestenje'];
        if (lista !== undefined) {
          try {
            lista.forEach((item, index) => {
              const idObavestenja = item['oba:obavestenje_body']._attributes.id;
              const obavestenje = {id: idObavestenja};
              newList.push(obavestenje);
            });
          } catch (err) {
            const idObavestenja = lista['oba:obavestenje_body']._attributes.id;
            const obavestenje = {id: idObavestenja};
            newList.push(obavestenje);
          }
          this.obavestenja = newList;
        }
      },
      error => {
        this.snackBar.open('Нешто није у реду!', 'Ok', {duration: 2000});
      }
    );
  }

  renderObavestenja = (result) => {
    this.obavestenja = [];
    const newList = [];
    const convert = require('xml-js');
    const obavestenjeList = JSON.parse(convert.xml2json(result, {compact: true, spaces: 4}));
    const lista = obavestenjeList.obavestenjeList['oba:obavestenje'];
    if (lista !== undefined) {
      try {
        lista.forEach((item, index) => {
          const idObavestenja = item['oba:obavestenje_body']._attributes.id;
          const obavestenje = {id: idObavestenja};
          newList.push(obavestenje);
        });
      } catch (err) {
        const idObavestenja = lista['oba:obavestenje_body']._attributes.id;
        const obavestenje = {id: idObavestenja};
        newList.push(obavestenje);
      }
      this.obavestenja = newList;
    }
  };

  onTekstChanged(newValue: any) {
    this.obavestenjeService.getPretragaTekst(newValue.value).subscribe(
      result => {
        this.renderObavestenja(result);
      },
      error => {
        this.snackBar.open('Нешто није у реду!', 'Ok', {duration: 2000});
      }
    );
  }

  onSubmitClicked() {
    this.obavestenjeService.getPretragaMetadata(this.form.controls.datumAfter.value, this.form.controls.datumBefore.value, this.form.controls.organVlasti.value, this.form.controls.userEmail.value, this.form.controls.zahtev.value).subscribe(
      result => {
        this.renderObavestenja(result);
      },
      error => {
        this.snackBar.open('Нешто није у реду!', 'Ok', {duration: 2000});
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
    this.obavestenjeService.convertZahtevPDF($event).subscribe(
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

  xhtml($event: string): void {
    this.obavestenjeService.convertZahtevXHTML($event).subscribe(
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

  rdfObavestenje($event: number) {

  }

  jsonObavestenje($event: number) {

  }
}
