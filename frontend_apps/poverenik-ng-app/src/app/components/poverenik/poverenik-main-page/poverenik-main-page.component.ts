import { Component, OnInit } from '@angular/core';
import {ZalbaService} from '../../../services/zalba-service/zalba.service';
import {MatSnackBar} from '@angular/material/snack-bar';

@Component({
  selector: 'app-poverenik-main-page',
  templateUrl: './poverenik-main-page.component.html',
  styleUrls: ['./poverenik-main-page.component.css']
})
export class PoverenikMainPageComponent implements OnInit {

  zalbe = []; // objekti tipa {id: number}
  constructor(private zalbaService: ZalbaService, private snackBar: MatSnackBar) { }

  ngOnInit(): void {
    const newList = [];
    const newList2 = [];
    this.zalbaService.getNeobradjeneAndUObradiZalbeCutanje().subscribe(
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
            newList.push(zalba);
          });
          this.zalbe = newList.concat(this.zalbe);
        }
      },
      error => {
        this.snackBar.open('Something went wrong!', 'Ok', { duration: 2000 });
      }
    );
    this.zalbaService.getNeobradjeneAndUObradiZalbeOdluka().subscribe(
      result => {
        // @ts-ignore
        const convert = require('xml-js');
        const zalbaCutanjeOdluka = JSON.parse(convert.xml2json(result, {compact: true, spaces: 4}));
        const lista = zalbaCutanjeOdluka.zalbaOdlukaList;
        const zalbe = lista['zoc:zalba_odluka'];
        if (zalbe !== undefined){
          zalbe.forEach((item, index) => {
            const idZalbe = item['zoc:zalba_odluka_body']._attributes.id;
            const zalba = {id: idZalbe, tip: 'odluka'};
            newList2.push(zalba);
          });
          this.zalbe = newList2.concat(this.zalbe);
        }
      },
      error => {
        this.snackBar.open('Something went wrong!', 'Ok', { duration: 2000 });
      }
    );
  }

  doubleClicked($event: number): void {
    // TODO redirectuje ga na detaljni prikaz zalbe
    console.log('double clicked');
  }

  convertToXHTML($event: number) {

  }

  convertToPDF($event: number) {

  }
}
