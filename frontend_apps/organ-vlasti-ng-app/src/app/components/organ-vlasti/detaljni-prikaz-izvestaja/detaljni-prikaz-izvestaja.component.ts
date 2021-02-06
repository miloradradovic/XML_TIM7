import { Component, OnInit } from '@angular/core';
import {ActivatedRoute} from "@angular/router";
import {IzvestajService} from "../../../services/izvestaj-service/izvestaj.service";

@Component({
  selector: 'app-detaljni-prikaz-izvestaja',
  templateUrl: './detaljni-prikaz-izvestaja.component.html',
  styleUrls: ['./detaljni-prikaz-izvestaja.component.css']
})
export class DetaljniPrikazIzvestajaComponent implements OnInit {

  src = '';

  constructor(private activatedRoute: ActivatedRoute, private service: IzvestajService) { }

  ngOnInit(): void {
    const izvestajId = this.activatedRoute.snapshot.queryParamMap.get('izvestaj_id');
    this.service.convertIzvestajXHTML(izvestajId).subscribe( res => {
      const binaryData = [];
      binaryData.push(res);
      const url = window.URL.createObjectURL(new Blob(binaryData, {type: 'text/html'}));
      this.src = url;
    });
  }


}
