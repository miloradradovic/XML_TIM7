import { Component, OnInit } from '@angular/core';
import { MatSnackBar } from '@angular/material/snack-bar';
import { ResenjeService } from 'src/app/services/resenje-service/resenje.service';
import {ActivatedRoute} from '@angular/router';

declare const Xonomy: any;

@Component({
  selector: 'app-resenje-form',
  templateUrl: './resenje-form.component.html',
  styleUrls: ['./resenje-form.component.css']
})
export class ResenjeFormComponent implements OnInit {

  constructor(private resenjeService: ResenjeService, public snackBar: MatSnackBar, private activatedRoute: ActivatedRoute) { }

  ngOnInit(): void {
  }

  ngAfterViewInit(): void {
    const idZalbe = this.activatedRoute.snapshot.queryParamMap.get('zalba_id');
    const today = new Date();
    const dd = String(today.getDate()).padStart(2, '0'); // day
    const mm = String(today.getMonth() + 1).padStart(2, '0'); // month
    const yyyy = today.getFullYear(); // year
    const hour =  String(today.getUTCHours()) === '23' ? String('00').padStart(2, '0') : String(today.getUTCHours() + 1).padStart(2, '0');
    const minutes = String(today.getMinutes()).padStart(2, '0');
    const seconds = String(today.getSeconds()).padStart(2, '0');
    const datumAtr = yyyy + '-' + mm + '-' + dd + 'T' + hour + ':' + minutes + ':' + seconds;
    const idPoverenika = JSON.parse(localStorage.getItem('user')).email;
    const elementR = document.getElementById('resenje');
    const xmlStringR =
    `<resenje xmlns="http://resenje"
    xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
    xmlns:xs="http://www.w3.org/2001/XMLSchema#"
   	xsi:schemaLocation="http://resenje ../xsd/resenje.xsd"><resenje_body idZalbe="${idZalbe}" datum="${datumAtr}"><tip_resenja></tip_resenja><uvodne_informacije>
                <trazilac id=""></trazilac>
                <lice></lice>
            	  <adresa><mesto></mesto><ulica broj="0"></ulica></adresa>
                <datum></datum>
            </uvodne_informacije><podaci_o_resenju><naslov>Р Е Ш Е Њ Е</naslov></podaci_o_resenju><podaci_o_obrazlozenju><naslov>О б р а з л о ж е њ е</naslov><predmet_zalbe></predmet_zalbe><postupak_poverenika></postupak_poverenika><odluka></odluka></podaci_o_obrazlozenju><poverenik id="${idPoverenika}"></poverenik></resenje_body></resenje>`;
    Xonomy.render(xmlStringR, elementR, {
      validate: this.resenjeService.resenjeSpecification.validate,
      elements: this.resenjeService.resenjeSpecification.elements,
      onchange: () => { }
    });
  }

  public submit(): void {
    if (Xonomy.warnings.length) {
      this.snackBar.open('Попуните сва обавезна поља!', 'Ok', { duration: 3000 });
      return;
    }

    this.resenjeService.send(Xonomy.harvest())
      .subscribe(res => {
        this.snackBar.open('Успешно сте послали решење!', 'Ok', { duration: 3000 });
      }, error => {
        this.snackBar.open('Немогуће креирање и слање решења!', 'Ok', { duration: 3000 });

      });

  }

}
