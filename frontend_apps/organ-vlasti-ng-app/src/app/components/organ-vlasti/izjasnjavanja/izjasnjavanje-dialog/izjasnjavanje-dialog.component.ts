import {Component, Inject, OnInit} from '@angular/core';
import {MAT_DIALOG_DATA, MatDialogRef} from '@angular/material/dialog';
import {IzjasnjavanjeService} from '../../../../services/izjasnjavanje-service/izjasnjavanje.service';
import {JwtHelperService} from '@auth0/angular-jwt';
import {SignInModel} from '../../../../model/sign-in.model';
import {MatSnackBar} from '@angular/material/snack-bar';

@Component({
  selector: 'app-izjasnjavanje-dialog',
  templateUrl: './izjasnjavanje-dialog.component.html',
  styleUrls: ['./izjasnjavanje-dialog.component.css']
})
export class IzjasnjavanjeDialogComponent {
  messageText: string;
  idZahteva: string;

  constructor(
    public dialogRef: MatDialogRef<IzjasnjavanjeDialogComponent>,
    @Inject(MAT_DIALOG_DATA) public data: {messageId: number, zahtev: string},
    private izjasnjavanjeService: IzjasnjavanjeService,
    private snackBar: MatSnackBar) {}

  onNoClick(): void {
    this.dialogRef.close();
  }
  posaljiIzjasnjavanje(): void {
    const messageId = this.data.messageId;
    this.idZahteva = this.data.zahtev;
    const messageNew = 'Zahtev sa ID-jem ' + messageId + ': ' + this.messageText;
    const messageObject = { _declaration:
        { _attributes: { version: '1.0', encoding: 'utf-8' } },
      message: { _attributes:
          { xmlns: 'http://www.message' },
        body: { _text: '', _attributes: {id: 0} } } };
    messageObject.message.body._text = messageNew;
    messageObject.message.body._attributes.id = messageId;
    // @ts-ignore
    const convert = require('xml-js');
    const messageXML = convert.js2xml(messageObject, {compact: true, ignoreComment: true, spaces: 4});
    this.izjasnjavanjeService.posaljiIzjasnjavanje(messageXML).subscribe(
      result => {
        this.snackBar.open('Успешно послато изјашњење!', 'Ок', {duration: 2000});
        this.dialogRef.close();
      },
      error => {
        this.snackBar.open('Слање није успело!', 'Ок', { duration: 2000 });
      }
    );
  }
}
