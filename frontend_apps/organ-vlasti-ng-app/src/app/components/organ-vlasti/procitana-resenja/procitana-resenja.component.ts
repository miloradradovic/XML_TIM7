import { Component, OnInit } from '@angular/core';
import {ResenjeService} from '../../../services/resenje-service/resenje.service';
import {MatSnackBar} from '@angular/material/snack-bar';

@Component({
  selector: 'app-procitana-resenja',
  templateUrl: './procitana-resenja.component.html',
  styleUrls: ['./procitana-resenja.component.css']
})
export class ProcitanaResenjaComponent implements OnInit {

  resenja = [];

  constructor(private resenjeService: ResenjeService,
              private snackBar: MatSnackBar) { }

  ngOnInit(): void {
    const newList = [];
    this.resenjeService.getResenjeListByStatus('da').subscribe(
      result => {
        // @ts-ignore
        const convert = require('xml-js');
        const resenjeList = JSON.parse(convert.xml2json(result, {compact: true, spaces: 4}));
        console.log(resenjeList);
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

  pdf($event: number) {

  }

  xhtml($event: number) {

  }
}
