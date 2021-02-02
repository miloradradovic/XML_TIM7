import { Component, OnInit } from '@angular/core';
import {ResenjeService} from '../../../services/resenje-service/resenje.service';
import {MatSnackBar} from '@angular/material/snack-bar';
import {ObavestenjeService} from '../../../services/obavestenje-service/obavestenje.service';

@Component({
  selector: 'app-obavestenja',
  templateUrl: './obavestenja.component.html',
  styleUrls: ['./obavestenja.component.css']
})
export class ObavestenjaComponent implements OnInit {

  obavestenja = [];

  constructor(private obavestenjeService: ObavestenjeService,
              private snackBar: MatSnackBar) { }

  ngOnInit(): void {
    const newList = [];
    this.obavestenjeService.getObavestenjeList().subscribe(
      result => {
        // @ts-ignore
        const convert = require('xml-js');
        const obavestenjeList = JSON.parse(convert.xml2json(result, {compact: true, spaces: 4}));
        console.log(obavestenjeList);
        const lista = obavestenjeList.obavestenjeList['oba:obavestenje'];
        if (lista !== undefined){
          lista.forEach((item, index) => {
            const idObavestenja = item['oba:obavestenje_body']._attributes.id;
            const obavestenje = {id: idObavestenja};
            newList.push(obavestenje);
          });
          this.obavestenja = newList;
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
