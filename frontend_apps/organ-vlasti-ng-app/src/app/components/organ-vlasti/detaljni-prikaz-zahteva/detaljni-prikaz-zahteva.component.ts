import { Component, OnInit } from '@angular/core';
import {ActivatedRoute, Router} from '@angular/router';
import {IzjasnjavanjeDialogComponent} from '../izjasnjavanja/izjasnjavanje-dialog/izjasnjavanje-dialog.component';
import {DialogOdbijanjeComponent} from './dialog-odbijanje/dialog-odbijanje.component';
import {MatDialog} from '@angular/material/dialog';

@Component({
  selector: 'app-detaljni-prikaz-zahteva',
  templateUrl: './detaljni-prikaz-zahteva.component.html',
  styleUrls: ['./detaljni-prikaz-zahteva.component.css']
})
export class DetaljniPrikazZahtevaComponent implements OnInit {

  zahtevId = '0';
  constructor(private activatedRoute: ActivatedRoute, private router: Router, public dialog: MatDialog) { }

  ngOnInit(): void {
    this.zahtevId = this.activatedRoute.snapshot.queryParamMap.get('zahtev_id');
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
      this.router.navigate(['/main-page-organ-vlasti']);
    });
  }
}
