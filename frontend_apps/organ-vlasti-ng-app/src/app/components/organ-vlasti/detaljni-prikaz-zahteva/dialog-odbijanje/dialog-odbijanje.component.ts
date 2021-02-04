import {Component, Inject, OnInit} from '@angular/core';
import {MAT_DIALOG_DATA, MatDialogRef} from '@angular/material/dialog';
import {IzjasnjavanjeService} from '../../../../services/izjasnjavanje-service/izjasnjavanje.service';
import {MatSnackBar} from '@angular/material/snack-bar';
import {ZahtevService} from '../../../../services/zahtev-service/zahtev.service';

@Component({
  selector: 'app-dialog-odbijanje',
  templateUrl: './dialog-odbijanje.component.html',
  styleUrls: ['./dialog-odbijanje.component.css']
})
export class DialogOdbijanjeComponent implements OnInit {
  razlog = '';

  constructor(public dialogRef: MatDialogRef<DialogOdbijanjeComponent>,
              @Inject(MAT_DIALOG_DATA) public data: {zahtevId: string},
              private zahtevService: ZahtevService,
              private snackBar: MatSnackBar) { }

  ngOnInit(): void {
  }

  onNoClick() {
    this.dialogRef.close();
  }

  posaljiOdbijanje() {
    const zahtevId = this.data.zahtevId;
    const toSend = zahtevId + ':' + this.razlog;
    this.zahtevService.posaljiOdbijanje(toSend).subscribe(
      result => {
        this.snackBar.open('Uspjesno poslato odbijanje!', 'Ok', {duration: 2000});
        this.dialogRef.close();
      },
      error => {
        this.snackBar.open('Something went wrong!', 'Ok', { duration: 2000 });
      }
    );
  }
}
