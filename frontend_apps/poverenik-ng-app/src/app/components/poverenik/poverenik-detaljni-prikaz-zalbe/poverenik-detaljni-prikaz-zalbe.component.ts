import { Component, OnInit } from '@angular/core';
import {ActivatedRoute, Router} from '@angular/router';
import {IzjasnjavanjaService} from '../../../services/izjasnjavanja-service/izjasnjavanja.service';
import {MatSnackBar} from '@angular/material/snack-bar';
import {ZalbaService} from '../../../services/zalba-service/zalba.service';
import {DomSanitizer, SafeResourceUrl} from '@angular/platform-browser';

@Component({
  selector: 'app-poverenik-detaljni-prikaz-zalbe',
  templateUrl: './poverenik-detaljni-prikaz-zalbe.component.html',
  styleUrls: ['./poverenik-detaljni-prikaz-zalbe.component.css']
})
export class PoverenikDetaljniPrikazZalbeComponent implements OnInit {
  uObradi: boolean;
  neobradjena: boolean;
  zalba = {id: '', status: ''};
  src = '';
  resenje = '';

  constructor(private activatedRoute: ActivatedRoute, private router: Router,
              private izjasnjavanjeService: IzjasnjavanjaService, private snackBar: MatSnackBar,
              private service: ZalbaService) { }

  ngOnInit(): void {

    const zalbaId: string = this.activatedRoute.snapshot.queryParamMap.get('zalba_id');
    let zalbaStatus = this.activatedRoute.snapshot.queryParamMap.get('zalba_status');
    const tip = zalbaId.split('/')[0];
    const broj = zalbaId.split('/')[1];
    let obs$;
    if (tip === 'cutanje'){
      if (!zalbaStatus) {
        this.service.getOneZalbaCutanje(broj).subscribe( res => {
            // @ts-ignore
            const convert = require('xml-js');
            const zalbaCutanje = JSON.parse(convert.xml2json(res, {compact: true, spaces: 4}));
            console.log(zalbaCutanje);
            zalbaStatus = zalbaCutanje['zalbaCutanjeList']['zc:zalba_cutanje']['zc:zalba_cutanje_body']['zc:status']._text;
            console.log(zalbaStatus);
        });
      }
      obs$ = this.service.convertZalbaCutanjeXHTML(broj);
    }
    else{
      if (!zalbaStatus) {
        this.service.getOneZalbaOdluka(broj).subscribe( res => {
          // @ts-ignore
          const convert = require('xml-js');
          const zalbaOdluka = JSON.parse(convert.xml2json(res, {compact: true, spaces: 4}));
          zalbaStatus = zalbaOdluka['zalbaOdlukaList']['zoc:zalba_odluka']['zoc:zalba_odluka_body']['zoc:status']._text;
        });
      }
      obs$ = this.service.convertZalbaOdlukaXHTML(broj);
    }
    obs$.subscribe( res => {
      const binaryData = [];
      binaryData.push(res);
      const url = window.URL.createObjectURL(new Blob(binaryData, {type: 'text/html'}));
      this.src = url;
    });

    this.service.getResenjeByZalba(tip + '-' + broj).subscribe( res => {
      if (res){
        // @ts-ignore
        const convert = require('xml-js');
        const resenje = JSON.parse(convert.xml2json(res, {compact: true, spaces: 4}));
        this.resenje = resenje['ra:resenje']['ra:resenje_body']._attributes.broj;
        console.log(this.resenje);
        //this.resenje = resenje['zc:getZalbaOdlukaByIdResponse']['zalba_odluka']._attributes.id;
      }
    });

    this.zalba.id = zalbaId;
    this.zalba.status = zalbaStatus;
    if (zalbaStatus === 'neobradjena'){
      this.neobradjena = true;
      this.uObradi = false;
    }else if (zalbaStatus === 'u obradi'){
      this.neobradjena = false;
      this.uObradi = true;
    }else{
      this.neobradjena = false;
      this.uObradi = false;
    }
  }


  traziIzjasnjenje($event: MouseEvent): void {
    this.izjasnjavanjeService.sendMessage(this.zalba.id).subscribe(
      result => {
        this.uObradi = true;
        this.neobradjena = false;
      },
      error => {
        this.snackBar.open('Something went wrong!', 'Ok', { duration: 2000 });
      }
    );
  }

  redirectToKreirajResenje($event: MouseEvent): void {
    this.router.navigate(['/resenje'], {queryParams: {zalba_id: this.zalba.id}});
  }

  resenjeDetails() {
    this.router.navigate(['/detaljni-prikaz-resenja'], {queryParams: {broj: this.resenje}});
  }

}
