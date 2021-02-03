import { Component, OnInit } from '@angular/core';
import {ActivatedRoute, Router} from '@angular/router';

@Component({
  selector: 'app-detaljni-prikaz-zahteva',
  templateUrl: './detaljni-prikaz-zahteva.component.html',
  styleUrls: ['./detaljni-prikaz-zahteva.component.css']
})
export class DetaljniPrikazZahtevaComponent implements OnInit {

  zahtevId = '0';
  constructor(private activatedRoute: ActivatedRoute, private router: Router) { }

  ngOnInit(): void {
    this.zahtevId = this.activatedRoute.snapshot.queryParamMap.get('zahtev_id');
  }


  kreirajObavestenje($event: MouseEvent): void {
    this.router.navigate(['/obavestenje'], {queryParams: {zahtev_id: this.zahtevId}});
  }
}
