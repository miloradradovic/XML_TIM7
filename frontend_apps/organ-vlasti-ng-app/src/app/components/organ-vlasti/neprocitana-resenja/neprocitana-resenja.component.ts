import { Component, OnInit } from '@angular/core';
import {IzjasnjavanjeService} from '../../../services/izjasnjavanje-service/izjasnjavanje.service';
import {MatSnackBar} from '@angular/material/snack-bar';
import {ResenjeService} from '../../../services/resenje-service/resenje.service';

@Component({
  selector: 'app-neprocitana-resenja',
  templateUrl: './neprocitana-resenja.component.html',
  styleUrls: ['./neprocitana-resenja.component.css']
})
export class NeprocitanaResenjaComponent implements OnInit {

  resenja = [];

  constructor(private resenjeService: ResenjeService,
              private snackBar: MatSnackBar) { }

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
          lista.forEach((item, index) => {
            const idResenja = item['ns2:body']._attributes.broj;
            const resenje = {id: idResenja};
            newList.push(resenje);
          });
          this.resenja = newList;
        }
        /*
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

         */
      },
      error => {
        this.snackBar.open('Something went wrong!', 'Ok', { duration: 2000 });
      }
    );
  }

  clicked($event: number) {

  }

  xhtml($event: number) {

  }

  pdf($event: number) {

  }
}
