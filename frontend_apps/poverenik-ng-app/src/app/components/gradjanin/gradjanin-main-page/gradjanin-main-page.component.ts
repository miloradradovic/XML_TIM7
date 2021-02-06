import {AfterViewInit, Component, OnInit} from '@angular/core';
import {ZalbaService} from '../../../services/zalba-service/zalba.service';
import {JwtHelperService} from '@auth0/angular-jwt';
import {SignInModel} from '../../../model/sign-in.model';
import {MatSnackBar} from '@angular/material/snack-bar';
import {ResenjeService} from '../../../services/resenje-service/resenje.service';
import {GradjaninPonistavanjeDialogComponent} from './gradjanin-ponistavanje-dialog/gradjanin-ponistavanje-dialog.component';
import {MatDialog} from '@angular/material/dialog';
import {of} from 'rxjs';
import {Router} from '@angular/router';

@Component({
  selector: 'app-gradjanin-main-page',
  templateUrl: './gradjanin-main-page.component.html',
  styleUrls: ['./gradjanin-main-page.component.css']
})
export class GradjaninMainPageComponent implements OnInit {

  zalbe = []; // lista objekata tipa {id: number}
  resenja = []; // isto
  constructor(private zalbaService: ZalbaService,
              private resenjeService: ResenjeService,
              private snackBar: MatSnackBar,
              public dialog: MatDialog,
              private router: Router) { }

  ngOnInit(): void {
    const newList = [];
    const newList2 = [];
    const newList3 = [];
    this.zalbaService.getZalbaOdlukaListByUser().subscribe(
      result => {
        // @ts-ignore
        const convert = require('xml-js');
        const zalbaOdlukaList = JSON.parse(convert.xml2json(result, {compact: true, spaces: 4}));
        const lista = zalbaOdlukaList.zalbaOdlukaList;
        const zalbe = lista['zoc:zalba_odluka'];
        if (zalbe !== undefined){
          try {
            zalbe.forEach((item, index) => {

              const idZalbe = item['zoc:zalba_odluka_body']._attributes.id;

              const statusZalbe = item['zoc:zalba_odluka_body']['zoc:status']._text;
              const zalba = {id: idZalbe, tip: 'odluka', status: statusZalbe};
              newList.push(zalba);
            });
          }
          catch (err) {
            const idZalbe = zalbe['zoc:zalba_odluka_body']._attributes.id;
            const statusZalbe = zalbe['zoc:zalba_odluka_body']['zoc:status']._text;
            const zalba = {id: idZalbe, tip: 'odluka', status: statusZalbe};
            newList.push(zalba);
          }
        }
        this.zalbe = newList.concat(this.zalbe);
      },
      error => {
        this.snackBar.open('Нешто није у реду!', 'Ok', { duration: 2000 });
      }
    );
    this.zalbaService.getZalbaCutanjeListByUser().subscribe(
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
              const statusZalbe = item['zc:zalba_cutanje_body']['zc:status']._text;
              const zalba = {id: idZalbe, tip: 'cutanje', status: statusZalbe};
              newList2.push(zalba);
            });
          }
          catch (err) {
            const idZalbe = zalbe['zc:zalba_cutanje_body']._attributes.id;
            const statusZalbe = zalbe['zc:zalba_cutanje_body']['zc:status']._text;
            const zalba = {id: idZalbe, tip: 'cutanje', status: statusZalbe};
            newList2.push(zalba);
          }
          this.zalbe = newList2.concat(this.zalbe);
        }
      },
      error => {
        this.snackBar.open('Нешто није у реду!', 'Ok', { duration: 2000 });
      }
    );
    this.resenjeService.getResenjeListByUser().subscribe(
      result => {
        // @ts-ignore
        const convert = require('xml-js');
        const resenjeList = JSON.parse(convert.xml2json(result, {compact: true, spaces: 4}));
        const resenja = resenjeList['ra:resenjeList']['ra:resenje'];
        if (resenja !== undefined){
          try {
            resenja.forEach((item, index) => {
              const idResenja = item['ra:resenje_body']._attributes.broj;
              const resenje = {id: idResenja};
              newList3.push(resenje);
            });
          }
          catch (err) {
            const idResenja = resenja['ra:resenje_body']._attributes.broj;
            const resenje = {id: idResenja};
            newList3.push(resenje);
          }
          this.resenja = newList3;
        }
      },
      error => {
        this.snackBar.open('Нешто није у реду!', 'Ok', { duration: 2000 });
      }
    );

  }

  ponistiZalba($event: string): void {
    console.log($event);
    // $event je tip/id
    // u need email iz dialoga
    this.zalbe.forEach((item, index) => {
      const itemid = item.tip + '/' + item.id;
      if (itemid === $event){
        if (item.status !== 'u obradi' || item.status !== 'neobradjena'){
          this.openDialog($event);
        }else{
          this.snackBar.open('Жалба је обрађена!', 'Ok', { duration: 2000 });
        }
      }
    });
  }
  openDialog(zalbaId: string): void{
    const dialogRef = this.dialog.open(GradjaninPonistavanjeDialogComponent, {
      width: '250px',
      data: {zalba: zalbaId}
    });

    dialogRef.afterClosed().subscribe(result => {
      console.log('The dialog was closed');
    });
  }

  convertToPdfZalba($event: string): void {
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

  convertToXHTMLZalba($event: string): void {
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

  convertToPdfResenje($event: number): void {
    this.resenjeService.convertResenjePDF(String($event)).subscribe(
      result => {
        const binaryData = [];
        binaryData.push(result);
        const url = window.URL.createObjectURL(new Blob(binaryData, {type: 'application/pdf'}));
        const a = document.createElement('a');
        document.body.appendChild(a);
        a.setAttribute('style', 'display: none');
        a.setAttribute('target', 'blank');
        a.href = url;
        a.download = 'resenje' + $event + '.pdf';
        a.click();
        window.URL.revokeObjectURL(url);
        a.remove();
      },
      error => {
        this.snackBar.open('Нешто није у реду!', 'Ok', { duration: 2000 });
      });
  }

  convertToXHTMLResenje($event: number): void {
    this.resenjeService.convertResenjeXHTML(String($event)).subscribe(
      result => {
        const binaryData = [];
        binaryData.push(result);
        const url = window.URL.createObjectURL(new Blob(binaryData, {type: 'application/html'}));
        const a = document.createElement('a');
        document.body.appendChild(a);
        a.setAttribute('style', 'display: none');
        a.setAttribute('target', 'blank');
        a.href = url;
        a.download = 'resenje' + $event + '.html';
        a.click();
        window.URL.revokeObjectURL(url);
        a.remove();
      },
      error => {
        this.snackBar.open('Нешто није у реду!', 'Ok', { duration: 2000 });
      });
  }

  doubleClickedResenje($event: number): void {
    this.resenja.forEach( resenje => {
      if (resenje.id === $event){
        this.router.navigate(['/detaljni-prikaz-resenja'], {queryParams: {broj: resenje.id}});
      }
    });
  }

  doubleClickedZalbe($event: string): void {
    this.zalbe.forEach((item, index) => {
      const zalba = item.tip + '/' + item.id;
      if (zalba === $event){
        this.router.navigate(['/detaljni-prikaz-zalbe'], {queryParams: {zalba_id: zalba, zalba_status: item.status}});
      }
    });
  }
}
