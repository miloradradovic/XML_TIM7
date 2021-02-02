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
        const lista = resenjeList.resenjeRefList['ns2:resenje_ref'];
        if (lista !== undefined){
          lista.forEach((item, index) => {
            const idResenja = item['ns2:body']._attributes.broj;
            const resenje = {id: idResenja};
            newList.push(resenje);
          });
          this.resenja = newList;
        }
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
