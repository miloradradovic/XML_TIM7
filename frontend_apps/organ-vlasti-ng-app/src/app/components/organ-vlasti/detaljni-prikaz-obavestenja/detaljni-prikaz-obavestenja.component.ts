import { Component, OnInit } from '@angular/core';
import {ObavestenjeService} from '../../../services/obavestenje-service/obavestenje.service';
import {ActivatedRoute} from '@angular/router';

@Component({
  selector: 'app-detaljni-prikaz-obavestenja',
  templateUrl: './detaljni-prikaz-obavestenja.component.html',
  styleUrls: ['./detaljni-prikaz-obavestenja.component.css']
})
export class DetaljniPrikazObavestenjaComponent implements OnInit {

  src = '';

  constructor(private service: ObavestenjeService, private activatedRoute: ActivatedRoute) { }

  ngOnInit(): void {
    const brojObavestenje: string = this.activatedRoute.snapshot.queryParamMap.get('broj');
    this.service.convertObavestenjeXHTML(brojObavestenje).subscribe( res => {
      const binaryData = [];
      binaryData.push(res);
      const url = window.URL.createObjectURL(new Blob(binaryData, {type: 'text/html'}));
      this.src = url;
    });
  }

}
