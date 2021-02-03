import { Component, OnInit } from '@angular/core';
import {ZalbaService} from '../../../services/zalba-service/zalba.service';
import {MatSnackBar} from '@angular/material/snack-bar';

@Component({
  selector: 'app-poverenik-sve-zalbe',
  templateUrl: './poverenik-sve-zalbe.component.html',
  styleUrls: ['./poverenik-sve-zalbe.component.css']
})
export class PoverenikSveZalbeComponent implements OnInit {

  zalbe = []; // objekti tipa {id: number}
  constructor(private zalbaService: ZalbaService, private snackBar: MatSnackBar) { }

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
        this.snackBar.open('Something went wrong!', 'Ok', { duration: 2000 });
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
        this.snackBar.open('Something went wrong!', 'Ok', { duration: 2000 });
      }
    );
  }


  convertToXHTML($event: string) {
    console.log($event);
  }

  convertToPDF($event: string) {
    console.log($event);
  }
}
