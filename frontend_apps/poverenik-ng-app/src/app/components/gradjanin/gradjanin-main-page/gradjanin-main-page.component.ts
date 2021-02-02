import {AfterViewInit, Component, OnInit} from '@angular/core';
import {ZalbaService} from '../../../services/zalba-service/zalba.service';
import {JwtHelperService} from '@auth0/angular-jwt';
import {SignInModel} from '../../../model/sign-in.model';
import {MatSnackBar} from '@angular/material/snack-bar';
import {ResenjeService} from '../../../services/resenje-service/resenje.service';

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
              private snackBar: MatSnackBar) { }

  ngOnInit(): void {
    const newList = [];
    const newList2 = [];
    const newList3 = [];
    this.zalbaService.getZalbaOdlukaListByUser().subscribe(
      result => {
        // @ts-ignore
        const convert = require('xml-js');
        const zalbaCutanjeList = JSON.parse(convert.xml2json(result, {compact: true, spaces: 4}));
        const lista = zalbaCutanjeList.zalbaCutanjeList;
        const zalbe = lista['zc:zalba_odluka'];
        if (zalbe !== undefined){
          zalbe.forEach((item, index) => {
            const idZalbe = item['zc:zalba_odluka_body']._attributes.id;
            const zalba = {id: idZalbe, tip: 'odluka'};
            newList.push(zalba);
          });
        }
        this.zalbe = newList.concat(this.zalbe);
      },
      error => {
        this.snackBar.open('Something went wrong!', 'Ok', { duration: 2000 });
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
          zalbe.forEach((item, index) => {
            const idZalbe = item['zc:zalba_cutanje_body']._attributes.id;
            const zalba = {id: idZalbe, tip: 'cutanje'};
            newList2.push(zalba);
          });
          this.zalbe = newList2.concat(this.zalbe);
        }
      },
      error => {
        this.snackBar.open('Something went wrong!', 'Ok', { duration: 2000 });
      }
    );
    this.resenjeService.getResenjeList().subscribe(
      result => {
        // @ts-ignore
        const convert = require('xml-js');
        const resenjeList = JSON.parse(convert.xml2json(result, {compact: true, spaces: 4}));
        const lista = resenjeList;
        const resenja = lista['ra:resenje'];
        if (resenja !== undefined){
          resenja.forEach((item, index) => {
            const idResenja = item['ra:resenje_body']._attributes.id;
            const resenje = {id: idResenja};
            newList3.push(resenje);
          });
          this.resenja = newList3;
        }
      },
      error => {
        this.snackBar.open('Something went wrong!', 'Ok', { duration: 2000 });
      }
    );

  }

  ponistiZalba($event: number): void {
    console.log('ponisti');
    console.log($event);
  }

  convertToPdfZalba($event: number): void {
    console.log('pdf');
    console.log($event);
  }

  convertToXHTMLZalba($event: number): void {
    console.log('xhtml');
    console.log($event);
  }
  ponistiResenje($event: number): void {
    console.log('ponisti');
    console.log($event);
  }

  convertToPdfResenje($event: number): void {
    console.log('pdf');
    console.log($event);
  }

  convertToXHTMLResenje($event: number): void {
    console.log('xhtml');
    console.log($event);
  }
}
