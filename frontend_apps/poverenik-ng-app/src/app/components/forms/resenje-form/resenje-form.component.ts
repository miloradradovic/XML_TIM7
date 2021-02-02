import { Component, OnInit } from '@angular/core';
import { MatSnackBar } from '@angular/material/snack-bar';
import { ResenjeService } from 'src/app/services/resenje-service/resenje.service';

declare const Xonomy: any;

@Component({
  selector: 'app-resenje-form',
  templateUrl: './resenje-form.component.html',
  styleUrls: ['./resenje-form.component.css']
})
export class ResenjeFormComponent implements OnInit {

  constructor(private resenjeService: ResenjeService, public snackBar: MatSnackBar) { }

  ngOnInit(): void {
  }

  ngAfterViewInit(): void {
    const idZalbe = "cutanje/1";
    const idPoverenika = '1'//JSON.parse(localStorage.getItem('user')).email;
    let elementR = document.getElementById("resenje");
    let xmlStringR = 
    `<resenje xmlns="http://resenje"
    xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
    xmlns:xs="http://www.w3.org/2001/XMLSchema#"
   	xsi:schemaLocation="http://resenje ../xsd/resenje.xsd"><resenje_body idZalbe="${idZalbe}" datum=""><tip_resenja></tip_resenja><uvodne_informacije>
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
      this.snackBar.open("Popunite sva obavezna rešenje!", 'Ok', { duration: 3000 });
      return;
    }
    console.log(Xonomy.harvest())

    this.resenjeService.send(Xonomy.harvest())
      .subscribe(res => console.log(res));
    this.snackBar.open("Uspešno ste poslali rešenje!", 'Ok', { duration: 3000 });

  }

}
