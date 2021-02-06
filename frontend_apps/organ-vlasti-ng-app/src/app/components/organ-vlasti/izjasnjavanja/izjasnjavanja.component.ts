import {ChangeDetectorRef, Component, OnInit} from '@angular/core';
import {IzjasnjavanjeService} from '../../../services/izjasnjavanje-service/izjasnjavanje.service';
import {MatSnackBar} from '@angular/material/snack-bar';
import {MatDialog} from '@angular/material/dialog';
import {IzjasnjavanjeDialogComponent} from './izjasnjavanje-dialog/izjasnjavanje-dialog.component';
import {Router} from '@angular/router';

@Component({
  selector: 'app-izjasnjavanja',
  templateUrl: './izjasnjavanja.component.html',
  styleUrls: ['./izjasnjavanja.component.css']
})
export class IzjasnjavanjaComponent implements OnInit {
  izjasnjavanja = [];

  constructor(private izjasnjavanjeService: IzjasnjavanjeService,
              private snackBar: MatSnackBar, public dialog: MatDialog, private detectChange: ChangeDetectorRef, private router: Router) {
  }

  ngOnInit(): void {
    const newList = [];
    this.izjasnjavanjeService.getIzjasnjavanjeList().subscribe(
      result => {
        // @ts-ignore
        const convert = require('xml-js');
        const izjasnjavanjeList = JSON.parse(convert.xml2json(result, {compact: true, spaces: 4}));
        const izjasnjavanja = izjasnjavanjeList.messageList['ns2:message'];
        if (izjasnjavanja !== undefined) {
          try {
            izjasnjavanja.forEach((item, index) => {
              console.log(item);
              const start = item['ns2:body']._text.split(':')[0];
              const end = item['ns2:body']._text.split('=')[1];
              const message = start + ': ' +  end;
              const idMessage = item['ns2:body']._attributes.id;
              const idZahteva = message.split(': ')[1];
              const messageObject = {id: idMessage, messageText: message, zahtev: idZahteva};
              newList.push(messageObject);
            });
          } catch (err) {
            console.log(izjasnjavanja);
            const message = izjasnjavanja['ns2:body']._text;
            const idMessage = izjasnjavanja['ns2:body']._attributes.id;
            const idZahteva = message.split(': ')[1];
            const messageObject = {id: idMessage, messageText: message, zahtev: idZahteva};
            newList.push(messageObject);
          }
          this.izjasnjavanja = newList;
        }
      },
      error => {
        this.snackBar.open('Нешто није у реду!', 'Ok', {duration: 2000});
      }
    );
  }

  openDialog($event: number): void {
    let zahtevId = '0';
    this.izjasnjavanja.forEach((item, index) => {
      if (item.id === $event) {
        zahtevId = item.zahtev;
      }
    });
    const dialogRef = this.dialog.open(IzjasnjavanjeDialogComponent, {
      width: '250px',
      data: {messageId: $event, zahtev: zahtevId}
    });

    dialogRef.afterClosed().subscribe(result => {
      const newList = [];
      this.router.navigate(['/pocetna-stranica-organ-vlasti']);
    });
  }
}
