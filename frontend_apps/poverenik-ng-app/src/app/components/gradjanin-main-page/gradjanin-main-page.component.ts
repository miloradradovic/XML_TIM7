import { Component, OnInit } from '@angular/core';
import {ZalbaService} from '../../services/zalba-service/zalba.service';
import {JwtHelperService} from '@auth0/angular-jwt';
import {SignInModel} from '../../model/sign-in.model';
import {MatSnackBar} from '@angular/material/snack-bar';

@Component({
  selector: 'app-gradjanin-main-page',
  templateUrl: './gradjanin-main-page.component.html',
  styleUrls: ['./gradjanin-main-page.component.css']
})
export class GradjaninMainPageComponent implements OnInit {

  zalbe = [];
  resenja = [];
  constructor(private zalbaService: ZalbaService,
              private snackBar: MatSnackBar) { }

  ngOnInit(): void {
    this.zalbaService.getZalbaCutanjeList().subscribe(
      result => {
        console.log(result);
      },
      error => {
        this.snackBar.open('Idk sta se desilo!', 'Ok', { duration: 2000 });
      }
    );
  }

  ponistiZalba($event: number): void {
    console.log('ponisti');
    console.log($event);
  }

  convertToPdfZalba($event: number): void {
    console.log('pdf');
    console.log($event);
  }

  convertToXHTMLZalba($event: number): void {
    console.log('xhtml');
    console.log($event);
  }
  ponistiResenje($event: number): void {
    console.log('ponisti');
    console.log($event);
  }

  convertToPdfResenje($event: number): void {
    console.log('pdf');
    console.log($event);
  }

  convertToXHTMLResenje($event: number): void {
    console.log('xhtml');
    console.log($event);
  }
}
