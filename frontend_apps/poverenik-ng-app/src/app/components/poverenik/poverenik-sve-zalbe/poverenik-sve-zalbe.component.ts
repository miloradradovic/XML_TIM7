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
    this.zalbaService.getZalbaCutanjeList().subscribe(
      result => {
        // @ts-ignore
        const convert = require('xml-js');
        const zalbaCutanjeList = JSON.parse(convert.xml2json(result, {compact: true, spaces: 4}));
        const lista = zalbaCutanjeList.zalbaCutanjeList;
        const zalbe = lista['zc:zalba_cutanje'];
        zalbe.forEach((item, index) => {
          const idZalbe = item['zc:zalba_cutanje_body']._attributes.id;
          const zalba = {id: idZalbe};
          newList.push(zalba);
        });
        this.zalbe = newList;
      },
      error => {
        this.snackBar.open('Something went wrong!', 'Ok', { duration: 2000 });
      }
    );
    // TODO odraditi zalba odluku jer na beku ne radi!
  }

}
