import {Component, OnInit} from '@angular/core';
import {ActivatedRoute, Router} from '@angular/router';
import {IzjasnjavanjeDialogComponent} from '../izjasnjavanja/izjasnjavanje-dialog/izjasnjavanje-dialog.component';
import {DialogOdbijanjeComponent} from './dialog-odbijanje/dialog-odbijanje.component';
import {MatDialog} from '@angular/material/dialog';
import {ZahtevService} from "../../../services/zahtev-service/zahtev.service";
import {ObavestenjeService} from "../../../services/obavestenje-service/obavestenje.service";
import {ZalbaService} from "../../../services/zalba-service/zalba.service";

@Component({
  selector: 'app-detaljni-prikaz-zahteva',
  templateUrl: './detaljni-prikaz-zahteva.component.html',
  styleUrls: ['./detaljni-prikaz-zahteva.component.css']
})
export class DetaljniPrikazZahtevaComponent implements OnInit {

  zahtevId = '0';
  odbijen = false;
  src = '';
  obavestenje = '';
  zalba = '';

  constructor(private activatedRoute: ActivatedRoute, private router: Router, public dialog: MatDialog, private service: ZahtevService
              ,private obavestenjeService: ObavestenjeService, private zalbaService: ZalbaService) { }


  ngOnInit(): void {
    this.zahtevId = this.activatedRoute.snapshot.queryParamMap.get('zahtev_id');
    let status = this.activatedRoute.snapshot.queryParamMap.get('zahtev_status');

    if (!status) {
      this.service.getOneZahtev(this.zahtevId).subscribe(res => {
        // @ts-ignore
        const convert = require('xml-js');
        const zahtev = JSON.parse(convert.xml2json(res, {compact: true, spaces: 4}));
        status = zahtev['zcir:zahtev']['zcir:zahtev_body']['zcir:status']._text;
        if (status === 'odbijen' || status === 'prihvacen'){
          this.odbijen = true;
        }
      });
    }

    this.service.convertZahtevXHTML(this.zahtevId).subscribe( res => {
      const binaryData = [];
      binaryData.push(res);
      const url = window.URL.createObjectURL(new Blob(binaryData, {type: 'text/html'}));
      this.src = url;
    });
    if (status === 'odbijen' || status === 'prihvacen'){
      this.odbijen = true;
    }

    this.obavestenjeService.getObavestenjeByZahtev(this.zahtevId).subscribe( res => {
      if (res){
        // @ts-ignore
        const convert = require('xml-js');
        const obavestenje = JSON.parse(convert.xml2json(res, {compact: true, spaces: 4}));
        this.obavestenje = obavestenje['oba:obavestenje']['oba:obavestenje_body']._attributes.id;
      }
    }, error => {
        this.obavestenje = '';
    });

    this.zalbaService.getZalbaCutanjeByZahtev(this.zahtevId).subscribe( res => {
      if (res){
        // @ts-ignore

        const convert = require('xml-js');
        const zalba = JSON.parse(convert.xml2json(res, {compact: true, spaces: 4}));
        this.zalba = 'cutanje-' + zalba['zc:getZalbaCutanjeByIdResponse']['zalba_cutanje']._attributes.id;
      }
    }, error => {
    });

    this.zalbaService.getZalbaOdlukaByZahtev(this.zahtevId).subscribe( res => {
      if (res){
        // @ts-ignore

        const convert = require('xml-js');
        const zalba = JSON.parse(convert.xml2json(res, {compact: true, spaces: 4}));
        this.zalba = 'odluka-' + zalba['zc:getZalbaOdlukaByIdResponse']['zalba_odluka']._attributes.id;
      }
    }, error => {
    });

  }


  kreirajObavestenje($event: MouseEvent): void {
    this.router.navigate(['/obavestenje'], {queryParams: {zahtev_id: this.zahtevId}});
  }

  openDialog($event: MouseEvent) {
    const dialogRef = this.dialog.open(DialogOdbijanjeComponent, {
      width: '250px',
      data: {zahtevId: this.zahtevId}
    });

    dialogRef.afterClosed().subscribe(result => {
      console.log('The dialog was closed');
      this.router.navigate(['/pocetna-stranica-organ-vlasti']);
    });
  }

  obavestenjeDetails() {
    this.router.navigate(['/detaljni-prikaz-obavestenja'], {queryParams: {broj: this.obavestenje}});
  }

  zalbaDetails() {
    this.router.navigate(['/detaljni-prikaz-zalbe'], {queryParams: {zalba_id: this.zalba}});
  }
}
