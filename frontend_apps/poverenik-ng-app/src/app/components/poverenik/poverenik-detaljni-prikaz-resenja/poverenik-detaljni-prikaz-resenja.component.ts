import { Component, OnInit } from '@angular/core';
import {ActivatedRoute, Router} from '@angular/router';
import {ResenjeService} from '../../../services/resenje-service/resenje.service';

@Component({
  selector: 'app-poverenik-detaljni-prikaz-resenja',
  templateUrl: './poverenik-detaljni-prikaz-resenja.component.html',
  styleUrls: ['./poverenik-detaljni-prikaz-resenja.component.css']
})
export class PoverenikDetaljniPrikazResenjaComponent implements OnInit {

  src = '';

  constructor(private activatedRoute: ActivatedRoute, private router: Router, private service: ResenjeService) { }

  ngOnInit(): void {
    const brojResenja: string = this.activatedRoute.snapshot.queryParamMap.get('broj');
    this.service.convertResenjeXHTML(brojResenja).subscribe( res => {
      const binaryData = [];
      binaryData.push(res);
      const url = window.URL.createObjectURL(new Blob(binaryData, {type: 'text/html'}));
      this.src = url;
    });
  }

}
