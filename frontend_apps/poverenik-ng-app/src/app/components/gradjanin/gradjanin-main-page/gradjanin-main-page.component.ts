import {AfterViewInit, Component, OnInit} from '@angular/core';
import {ZalbaService} from '../../../services/zalba-service/zalba.service';
import {JwtHelperService} from '@auth0/angular-jwt';
import {SignInModel} from '../../../model/sign-in.model';
import {MatSnackBar} from '@angular/material/snack-bar';
import {ResenjeService} from '../../../services/resenje-service/resenje.service';
import {GradjaninPonistavanjeDialogComponent} from './gradjanin-ponistavanje-dialog/gradjanin-ponistavanje-dialog.component';
import {MatDialog} from '@angular/material/dialog';

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
              public dialog: MatDialog) { }

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

  convertToPdfZalba($event: string) {
    console.log($event);
  }

  convertToXHTMLZalba($event: string) {
    console.log($event);
  }

  convertToPdfResenje($event: number) {
    console.log($event);
  }

  convertToXHTMLResenje($event: number) {
    console.log($event);
  }
}
