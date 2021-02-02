import { Component, OnInit } from '@angular/core';
import {IzjasnjavanjeService} from '../../../services/izjasnjavanje-service/izjasnjavanje.service';
import {MatSnackBar} from '@angular/material/snack-bar';

@Component({
  selector: 'app-izjasnjavanja',
  templateUrl: './izjasnjavanja.component.html',
  styleUrls: ['./izjasnjavanja.component.css']
})
export class IzjasnjavanjaComponent implements OnInit {
  izjasnjavanja = [];

  constructor(private izjasnjavanjeService: IzjasnjavanjeService,
              private snackBar: MatSnackBar) { }

  ngOnInit(): void {
    const newList = [];
    this.izjasnjavanjeService.getIzjasnjavanjeList().subscribe(
      result => {
        // @ts-ignore
        const convert = require('xml-js');
        const izjasnjavanjeList = JSON.parse(convert.xml2json(result, {compact: true, spaces: 4}));
        console.log(izjasnjavanjeList);
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

}
