import { Component, OnInit } from '@angular/core';
import {ActivatedRoute, Router} from '@angular/router';
import {IzjasnjavanjaService} from '../../../services/izjasnjavanja-service/izjasnjavanja.service';
import {MatSnackBar} from '@angular/material/snack-bar';

@Component({
  selector: 'app-poverenik-detaljni-prikaz-zalbe',
  templateUrl: './poverenik-detaljni-prikaz-zalbe.component.html',
  styleUrls: ['./poverenik-detaljni-prikaz-zalbe.component.css']
})
export class PoverenikDetaljniPrikazZalbeComponent implements OnInit {
  uObradi: boolean;
  neobradjena: boolean;
  zalba = {id: '', status: ''};

  constructor(private activatedRoute: ActivatedRoute, private router: Router,
              private izjasnjavanjeService: IzjasnjavanjaService, private snackBar: MatSnackBar) { }

  ngOnInit(): void {
    const zalbaId = this.activatedRoute.snapshot.queryParamMap.get('zalba_id');
    const zalbaStatus = this.activatedRoute.snapshot.queryParamMap.get('zalba_status');
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
}
