import { Component, OnInit } from '@angular/core';
import {ZalbaService} from '../../../services/zalba-service/zalba.service';
import {MatSnackBar} from '@angular/material/snack-bar';
import { FormBuilder, FormGroup } from '@angular/forms';
import { throwMatDialogContentAlreadyAttachedError } from '@angular/material/dialog';
import {of} from 'rxjs';
import {Router} from '@angular/router';

declare var require: any;

@Component({
  selector: 'app-poverenik-sve-zalbe',
  templateUrl: './poverenik-sve-zalbe.component.html',
  styleUrls: ['./poverenik-sve-zalbe.component.css']
})
export class PoverenikSveZalbeComponent implements OnInit {

  form: FormGroup;
  zalbe = []; // objekti tipa {id: number}
  constructor(private zalbaService: ZalbaService, private snackBar: MatSnackBar, private fb: FormBuilder, private router: Router ) {
    this.form = this.fb.group({
      mesto: [''],
      organVlasti: [''],
      userEmail: [''],
      status: [''],
      datumAfter: [''],
      datumBefore: [''],
      zahtevId: ['']
    });
   }

  ngOnInit(): void {
    // TODO pozovi metode iz servisa kad se napravi api
    // treba dobaviti sve zalbe
    const newList = [];
    const newList2 = [];
    this.zalbaService.getZalbaCutanjeList().subscribe(
      result => {
        // @ts-ignore
        const convert = require('xml-js');
        const zalbaCutanjeList = JSON.parse(convert.xml2json(result, {compact: true, spaces: 4}));
        const lista = zalbaCutanjeList.zalbaCutanjeList;
        const zalbe = lista['zc:zalba_cutanje'];
        if (zalbe !== undefined){
          try {
            zalbe.forEach((item, index) => {
              const idZalbe = item['zc:zalba_cutanje_body']._attributes.id;
              const zalba = {id: idZalbe, tip: 'cutanje'};
              newList.push(zalba);
            });
          } catch (err){
            const idZalbe = zalbe['zc:zalba_cutanje_body']._attributes.id;
            const zalba = {id: idZalbe, tip: 'cutanje'};
            newList.push(zalba);
          }
          this.zalbe = newList.concat(this.zalbe);
        }
      },
      error => {
        this.snackBar.open('Нешто није у реду!', 'Ok', { duration: 2000 });
      }
    );
    this.zalbaService.getZalbaOdlukaList().subscribe(
      result => {
        // @ts-ignore
        const convert = require('xml-js');
        const zalbaCutanjeOdluka = JSON.parse(convert.xml2json(result, {compact: true, spaces: 4}));
        const lista = zalbaCutanjeOdluka.zalbaOdlukaList;
        const zalbe = lista['zoc:zalba_odluka'];
        if (zalbe !== undefined){
          try {
            zalbe.forEach((item, index) => {
              const idZalbe = item['zoc:zalba_odluka_body']._attributes.id;
              const zalba = {id: idZalbe, tip: 'odluka'};
              newList2.push(zalba);
            });
          } catch (err) {
            const idZalbe = zalbe['zoc:zalba_odluka_body']._attributes.id;
            const zalba = {id: idZalbe, tip: 'odluka'};
            newList2.push(zalba);
          }
          this.zalbe = newList2.concat(this.zalbe);
        }
      },
      error => {
        this.snackBar.open('Нешто није у реду!', 'Ok', { duration: 2000 });
      }
    );
  }

  renderZalbeCutanje = (result) => {
    const newList = [];
    const convert = require('xml-js');
    const zalbaCutanjeList = JSON.parse(convert.xml2json(result, {compact: true, spaces: 4}));
    const lista = zalbaCutanjeList.zalbaCutanjeList;
    const zalbe = lista['zc:zalba_cutanje'];
    if (zalbe !== undefined){
      try {
        zalbe.forEach((item, index) => {
          const idZalbe = item['zc:zalba_cutanje_body']._attributes.id;
          const zalba = {id: idZalbe, tip: 'cutanje'};
          newList.push(zalba);
        });
      } catch (err){
        const idZalbe = zalbe['zc:zalba_cutanje_body']._attributes.id;
        const zalba = {id: idZalbe, tip: 'cutanje'};
        newList.push(zalba);
      }
      this.zalbe = newList.concat(this.zalbe);
    }
  }

  renderZalbeOdluka = (result) => {
    const newList2 = [];
    const convert = require('xml-js');
    const zalbaCutanjeOdluka = JSON.parse(convert.xml2json(result, {compact: true, spaces: 4}));
    const lista = zalbaCutanjeOdluka.zalbaOdlukaList;
    const zalbe = lista['zoc:zalba_odluka'];
    if (zalbe !== undefined){
      try {
        zalbe.forEach((item, index) => {
          const idZalbe = item['zoc:zalba_odluka_body']._attributes.id;
          const zalba = {id: idZalbe, tip: 'odluka'};
          newList2.push(zalba);
        });
      } catch (err) {
        const idZalbe = zalbe['zoc:zalba_odluka_body']._attributes.id;
        const zalba = {id: idZalbe, tip: 'odluka'};
        newList2.push(zalba);
      }
      this.zalbe = newList2.concat(this.zalbe);
    }
  }

  onTekstChanged(newValue: any){
    this.zalbe = [];
    this.zalbaService.getPretragaTekstZalbeCutanje(newValue.value).subscribe(
      result => {
        this.renderZalbeCutanje(result);
      },
      error => {
        this.snackBar.open('Нешто није у реду!', 'Ok', { duration: 2000 });
      }
    );
    this.zalbaService.getPretragaTekstZalbeOdluka(newValue.value).subscribe(
      result => {
        this.renderZalbeOdluka(result);
      },
      error => {
        this.snackBar.open('Нешто није у реду!', 'Ok', { duration: 2000 });
      }
    );
  }

  onSubmitClicked() {
    console.log(this.form.controls.mesto.value)
    console.log(this.form.controls.organVlasti.value)
    console.log(this.form.controls.userEmail.value)
    console.log(this.form.controls.status.value)
    console.log(this.form.controls.datumAfter.value)
    console.log(this.form.controls.datumBefore.value)
    console.log(this.form.controls.zahtevId.value)
    this.zalbe = [];
    this.zalbaService.getPretragaMetadataZalbeCutanje(this.form.controls.datumAfter.value, this.form.controls.datumBefore.value, this.form.controls.status.value, this.form.controls.organVlasti.value, this.form.controls.mesto.value, this.form.controls.userEmail.value, this.form.controls.zahtevId.value).subscribe(
      result => {
        this.renderZalbeCutanje(result);
      },
      error => {
        this.snackBar.open('Нешто није у реду!', 'Ok', { duration: 2000 });
      }
    );
    this.zalbaService.getPretragaMetadataZalbeOdluka(this.form.controls.datumAfter.value, this.form.controls.datumBefore.value, this.form.controls.status.value, this.form.controls.organVlasti.value, this.form.controls.mesto.value, this.form.controls.userEmail.value, this.form.controls.zahtevId.value).subscribe(
      result => {
        this.renderZalbeOdluka(result);
      },
      error => {
        this.snackBar.open('Нешто није у реду!', 'Ok', { duration: 2000 });
      }
    );

  }

  doubleClicked($event: string): void {
    console.log($event);
    this.zalbe.forEach((item, index) => {
      const zalba = item.tip + '/' + item.id;
      if (zalba === $event){
        this.router.navigate(['/detaljni-prikaz-zalbe'], {queryParams: {zalba_id: zalba, zalba_status: item.status}});
      }
    });
    // this.router.navigate(['/detailed-cultural-offer'], {queryParams: {offer_id: offerId}});
  }

  onDatumAfterChanged(event) {
    this.form.controls.datumAfter.patchValue(new Date(event.target.value).toISOString().split('.')[0]);
  }

  onDatumBeforeChanged(event) {
    this.form.controls.datumBefore.patchValue(new Date(event.target.value).toISOString().split('.')[0]);
  }

  convertToPDF($event: string): void {
    let obs$ = of([]);
    const tip: string = $event.split('/')[0];
    const broj: string = $event.split('/')[1];
    if (tip === 'cutanje'){
      obs$ = this.zalbaService.convertZalbaCutanjePDF($event.split('/')[1]);
    }
    else{
      obs$ = this.zalbaService.convertZalbaOdlukaPDF($event.split('/')[1]);
    }
    obs$.subscribe(
      result => {
        const binaryData = [];
        binaryData.push(result);
        const url = window.URL.createObjectURL(new Blob(binaryData, {type: 'application/pdf'}));
        const a = document.createElement('a');
        document.body.appendChild(a);
        a.setAttribute('style', 'display: none');
        a.setAttribute('target', 'blank');
        a.href = url;
        a.download = 'zalba' + tip + broj + '.pdf';
        a.click();
        window.URL.revokeObjectURL(url);
        a.remove();
      },
      error => {
        this.snackBar.open('Нешто није у реду!', 'Ok', { duration: 2000 });
      });
  }

  convertToXHTML($event: string): void {
    let obs$;
    const tip: string = $event.split('/')[0];
    const broj: string = $event.split('/')[1];
    if (tip === 'cutanje'){
      obs$ = this.zalbaService.convertZalbaCutanjeXHTML($event.split('/')[1]);
    }
    else{
      obs$ = this.zalbaService.convertZalbaOdlukaXHTML($event.split('/')[1]);
    }
    obs$.subscribe(
      result => {
        const binaryData = [];
        binaryData.push(result);
        const url = window.URL.createObjectURL(new Blob(binaryData, {type: 'application/pdf'}));
        const a = document.createElement('a');
        document.body.appendChild(a);
        a.setAttribute('style', 'display: none');
        a.setAttribute('target', 'blank');
        a.href = url;
        a.download = 'zalba' + tip + broj + '.html';
        a.click();
        window.URL.revokeObjectURL(url);
        a.remove();
      },
      error => {
        this.snackBar.open('Нешто није у реду!', 'Ok', { duration: 2000 });
      });
  }
}
