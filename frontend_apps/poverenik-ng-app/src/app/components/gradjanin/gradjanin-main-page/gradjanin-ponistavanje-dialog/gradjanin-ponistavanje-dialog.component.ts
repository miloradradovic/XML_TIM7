import {Component, Inject, OnInit} from '@angular/core';
import {MatDialogRef, MAT_DIALOG_DATA} from '@angular/material/dialog';
import {ZalbaService} from '../../../../services/zalba-service/zalba.service';
import {MatSnackBar} from '@angular/material/snack-bar';

@Component({
  selector: 'app-gradjanin-ponistavanje-dialog',
  templateUrl: './gradjanin-ponistavanje-dialog.component.html',
  styleUrls: ['./gradjanin-ponistavanje-dialog.component.css']
})
export class GradjaninPonistavanjeDialogComponent {

  emailInput: string;

  constructor(
    public dialogRef: MatDialogRef<GradjaninPonistavanjeDialogComponent>,
    @Inject(MAT_DIALOG_DATA) public data: {zalba: string},
    private zalbaService: ZalbaService,
    private snackBar: MatSnackBar) {}

  onNoClick(): void {
    this.dialogRef.close();
  }
  posaljiPonistavanje(): void {
    const zalbaId = this.data.zalba;
    const toSend = zalbaId + ' ' + this.emailInput;
    this.zalbaService.posaljiPonistavanje(toSend).subscribe(
      result => {
        this.snackBar.open('Uspjesno poslat zahtev za ponistenjem zalbe!', 'Ok', {duration: 2000});
        this.dialogRef.close();
      },
      error => {
        this.snackBar.open('Something went wrong!', 'Ok', { duration: 2000 });
      }
    );
  }

}
