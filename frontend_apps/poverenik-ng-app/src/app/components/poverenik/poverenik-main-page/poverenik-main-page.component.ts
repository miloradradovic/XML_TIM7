import {ChangeDetectorRef, Component, OnInit} from '@angular/core';
import {ZalbaService} from '../../../services/zalba-service/zalba.service';
import {MatSnackBar} from '@angular/material/snack-bar';
import {Router} from '@angular/router';
import {of} from 'rxjs';

@Component({
  selector: 'app-poverenik-main-page',
  templateUrl: './poverenik-main-page.component.html',
  styleUrls: ['./poverenik-main-page.component.css']
})
export class PoverenikMainPageComponent implements OnInit {

  zalbe = []; // objekti tipa {id: number}
  constructor(private zalbaService: ZalbaService, private snackBar: MatSnackBar, private router: Router, private detectChange: ChangeDetectorRef) { }

  ngOnInit(): void {
    this.zalbaService.getNeobradjeneAndUObradiZalbeCutanje().subscribe(
      result => {
        const newList = [];
        // @ts-ignore
        const convert = require('xml-js');
        const zalbaCutanjeList = JSON.parse(convert.xml2json(result, {compact: true, spaces: 4}));
        const lista = zalbaCutanjeList.zalbaCutanjeList;
        const zalbe = lista['zc:zalba_cutanje'];
        if (zalbe !== undefined){
          try {
            zalbe.forEach((item, index) => {
              const idZalbe = item['zc:zalba_cutanje_body']._attributes.id;
              const statusZalbe = item['zc:zalba_cutanje_body']['zc:status']._text;
              const zalba = {id: idZalbe, tip: 'cutanje', status: statusZalbe};
              newList.push(zalba);
            });
          } catch (err){
            const idZalbe = zalbe['zc:zalba_cutanje_body']._attributes.id;
            const statusZalbe = zalbe['zc:zalba_cutanje_body']['zc:status']._text;
            const zalba = {id: idZalbe, tip: 'cutanje', status: statusZalbe};
            newList.push(zalba);
          }
          this.zalbe = newList.concat(this.zalbe);
        }
      },
      error => {
        this.snackBar.open('Something went wrong!', 'Ok', { duration: 2000 });
      }
    );
    this.zalbaService.getNeobradjeneAndUObradiZalbeOdluka().subscribe(
      result => {
        const newList2 = [];
        // @ts-ignore
        const convert = require('xml-js');
        const zalbaCutanjeOdluka = JSON.parse(convert.xml2json(result, {compact: true, spaces: 4}));
        const lista = zalbaCutanjeOdluka.zalbaOdlukaList;
        const zalbe = lista['zoc:zalba_odluka'];
        if (zalbe !== undefined){
          try {
            zalbe.forEach((item, index) => {
              const idZalbe = item['zoc:zalba_odluka_body']._attributes.id;
              const statusZalbe = item['zoc:zalba_odluka_body']['zoc:status']._text;
              const zalba = {id: idZalbe, tip: 'odluka', status: statusZalbe};
              newList2.push(zalba);
            });
          } catch (err){
            const idZalbe = zalbe['zoc:zalba_odluka_body']._attributes.id;
            const statusZalbe = zalbe['zoc:zalba_odluka_body']['zoc:status']._text;
            const zalba = {id: idZalbe, tip: 'odluka', status: statusZalbe};
            newList2.push(zalba);
          }
          this.zalbe = newList2.concat(this.zalbe);
        }
      },
      error => {
        this.snackBar.open('Something went wrong!', 'Ok', { duration: 2000 });
      }
    );
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

  doubleClicked($event: string): void {
    this.zalbe.forEach((item, index) => {
      const zalba = item.tip + '/' + item.id;
      if (zalba === $event){
        this.router.navigate(['/detaljni-prikaz-zalbe'], {queryParams: {zalba_id: zalba, zalba_status: item.status}});
      }
    });
    // this.router.navigate(['/detailed-cultural-offer'], {queryParams: {offer_id: offerId}});
  }

  ponistiZalba($event: string): void {
    this.zalbaService.ponisti($event).subscribe(
      result => {
        this.snackBar.open('Uspjesno ponistena zalba!', 'Ok', {duration: 2000});
        this.zalbe = this.zalbe.filter( zalba => zalba.tip + '/' + zalba.id !== $event);
        this.zalbe = [...this.zalbe];
        this.detectChange.markForCheck();
      },
      error => {
        this.snackBar.open('Something went wrong!', 'Ok', { duration: 2000 });
      }
    );
  }
}
