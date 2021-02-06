import { Component, OnInit } from '@angular/core';
import {ActivatedRoute, Router} from '@angular/router';
import {ResenjeService} from '../../../services/resenje-service/resenje.service';
import {ZahtevService} from '../../../services/zahtev-service/zahtev.service';

@Component({
  selector: 'app-poverenik-detaljni-prikaz-zahteva',
  templateUrl: './poverenik-detaljni-prikaz-zahteva.component.html',
  styleUrls: ['./poverenik-detaljni-prikaz-zahteva.component.css']
})
export class PoverenikDetaljniPrikazZahtevaComponent implements OnInit {

  zahtevId = '0';
  src = '';

  constructor(private activatedRoute: ActivatedRoute, private service: ZahtevService) { }


  ngOnInit(): void {
    this.zahtevId = this.activatedRoute.snapshot.queryParamMap.get('zahtev_id');
    this.service.convertZahtevXHTML(this.zahtevId).subscribe( res => {
      const binaryData = [];
      binaryData.push(res);
      const url = window.URL.createObjectURL(new Blob(binaryData, {type: 'text/html'}));
      this.src = url;
    });
  }

}
