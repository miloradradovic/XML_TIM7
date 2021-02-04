import { Component, OnInit } from '@angular/core';
import {IzvestajService} from '../../../services/izvestaj-service/izvestaj.service';
import {MatSnackBar} from '@angular/material/snack-bar';

@Component({
  selector: 'app-kreiranje-izvestaja',
  templateUrl: './kreiranje-izvestaja.component.html',
  styleUrls: ['./kreiranje-izvestaja.component.css']
})
export class KreiranjeIzvestajaComponent implements OnInit {
  zahtevi = [];
  zalbecutanje = [];
  zalbeodluka = [];

  constructor(private izvestajService: IzvestajService, private snackBar: MatSnackBar) { }

  ngOnInit(): void {
  }

  generisiIzvestaj($event: MouseEvent): void {
    const newList = [];
    const newList2 = [];
    const newList3 = [];
    this.izvestajService.generisiIzvestaj().subscribe(
      result => {
        this.snackBar.open('Izvestaj je izgenerisan i poslat povereniku!', 'Ok', { duration: 4000 });
        // @ts-ignore
        const convert = require('xml-js');
        const izvestajList = JSON.parse(convert.xml2json(result, {compact: true, spaces: 4}));
        console.log(izvestajList);
        const izvestaj = izvestajList['ns3:izvestaj']['ns3:izvestaj_body'];
        const zahtev = {broj_podnetih_zahteva: izvestaj['ns3:zahtevi_podneti']._text, broj_prihvacenih_zahteva: izvestaj['ns3:zahtevi_prihvaceni']._text,
        broj_odbijenih_zahteva: izvestaj['ns3:zahtevi_odbijeni']._text};
        newList.push(zahtev);
        this.zahtevi = newList;
        const zalbacutanje = {broj_podnetih_zalbi_cutanje: izvestaj['ns3:zalbe_cutanje_podneti']._text,
          broj_prihvacenih_zalbi_cutanje: izvestaj['ns3:zalbe_cutanje_prihvaceno']._text,
          broj_odbijenih_zalbi_cutanje: izvestaj['ns3:zalbe_cutanje_odbijeno']._text,
          broj_ponistenih_zalbi_cutanje: izvestaj['ns3:zalbe_cutanje_ponisteno']._text};
        newList2.push(zalbacutanje);
        this.zalbecutanje = newList2;
        const zalbaodluka = {broj_podnetih_zalbi_odluka: izvestaj['ns3:zalbe_odluke_podneti']._text,
                             broj_prihvacenih_zalbi_odluka: izvestaj['ns3:zalbe_odluke_prihvaceno']._text,
                             broj_odbijenih_zalbi_odluka: izvestaj['ns3:zalbe_odluke_odbijeno']._text,
                             broj_ponistenih_zalbi_odluka: izvestaj['ns3:zalbe_odluke_ponisteno']._text};
        newList3.push(zalbaodluka);
        this.zalbeodluka = newList3;
      },
      error => {
        this.snackBar.open('Something went wrong!', 'Ok', { duration: 2000 });
      }
    );
  }
}
