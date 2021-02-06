import { Component, OnInit } from '@angular/core';
import {ActivatedRoute, Router} from "@angular/router";
import {MatSnackBar} from "@angular/material/snack-bar";
import {ZalbaService} from "../../../services/zalba-service/zalba.service";

@Component({
  selector: 'app-detaljni-prikaz-zalbe',
  templateUrl: './detaljni-prikaz-zalbe.component.html',
  styleUrls: ['./detaljni-prikaz-zalbe.component.css']
})
export class DetaljniPrikazZalbeComponent implements OnInit {

  src = '';

  constructor(private activatedRoute: ActivatedRoute, private router: Router,
              private snackBar: MatSnackBar,
              private service: ZalbaService) { }

  ngOnInit(): void {
    const zalbaId: string = this.activatedRoute.snapshot.queryParamMap.get('zalba_id');
    const tip = zalbaId.split('/')[0];
    const broj = zalbaId.split('/')[1];
    console.log(zalbaId);
    let obs$;
    if (tip === 'cutanje'){
      obs$ = this.service.convertZalbaCutanjeXHTML(broj);
    }
    else{
      obs$ = this.service.convertZalbaOdlukaXHTML(broj);
    }
    obs$.subscribe( res => {
      const binaryData = [];
      binaryData.push(res);
      const url = window.URL.createObjectURL(new Blob(binaryData, {type: 'text/html'}));
      this.src = url;
    });
  }

}
