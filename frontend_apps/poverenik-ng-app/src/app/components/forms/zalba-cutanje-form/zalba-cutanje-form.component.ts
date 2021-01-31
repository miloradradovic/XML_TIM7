import { Component, OnInit, ViewChild } from '@angular/core';
import { MatSnackBar } from '@angular/material/snack-bar';
import { ZalbaCutanjeService } from 'src/app/services/zalba-cutanje-service/zalba-cutanje.service';

declare const Xonomy: any;

@Component({
  selector: 'app-zalba-cutanje-form',
  templateUrl: './zalba-cutanje-form.component.html',
  styleUrls: ['./zalba-cutanje-form.component.css']
})
export class ZalbaCutanjeFormComponent implements OnInit {

  constructor(private zalbaCutanjeService: ZalbaCutanjeService, public snackBar: MatSnackBar) { }


  ngOnInit(): void {
  }

  ngAfterViewInit(): void {
    let element = document.getElementById("zalbaCutanje");
    let xmlString = 
    `<zc:zalba_cutanje 
      xmlns:zc="http://www.zalbacutanje" 
      xmlns:re="http://www.reusability"
      xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
      xsi:schemaLocation="http://www.zalbacutanje ../xsd/zalba_cutanje.xsd"><zc:zalba_cutanje_body  mjesto="" datum=""><zc:zahtev></zc:zahtev><zc:sadrzaj_zalbe><re:clan></re:clan><re:ciljani_organ_vlasti></re:ciljani_organ_vlasti><re:razlog_zalbe><re:opcija izabran="false">није поступио</re:opcija><re:opcija izabran="false">није поступио у целости</re:opcija><re:opcija izabran="false">у законском року</re:opcija></re:razlog_zalbe><re:datum></re:datum><re:podaci_o_zahtjevu_i_informacijama></re:podaci_o_zahtjevu_i_informacijama><re:napomena></re:napomena></zc:sadrzaj_zalbe><zc:podaci_o_podnosiocu><re:osoba><re:ime></re:ime><re:prezime></re:prezime></re:osoba><re:adresa><re:mesto></re:mesto><re:ulica broj="0"></re:ulica></re:adresa><re:drugi_podaci_za_kontakt></re:drugi_podaci_za_kontakt></zc:podaci_o_podnosiocu></zc:zalba_cutanje_body></zc:zalba_cutanje>`;
    Xonomy.render(xmlString, element, {
      validate: this.zalbaCutanjeService.zalbaCutanjeSpecification.validate,
      elements: this.zalbaCutanjeService.zalbaCutanjeSpecification.elements,
      onchange: () => { 
      }
    });

    //PRIMJER ZA RJESENJE
    /*let elementR = document.getElementById("resenje");
    let xmlStringR = 
    `<resenje xmlns="http://resenje"
    xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
	xsi:schemaLocation="http://resenje ../xsd/resenje.xsd"><resenje_body idZalbe="1" datum="2020-06-10"><tip_resenja></tip_resenja><uvodne_informacije>
                <trazilac id=""></trazilac>
                <lice></lice>
            	  <adresa><mesto></mesto><ulica broj="0"></ulica></adresa>
                <datum></datum>
            </uvodne_informacije><podaci_o_resenju>
                <naslov>Р Е Ш Е Њ Е</naslov>
            </podaci_o_resenju><podaci_o_obrazlozenju>
                <naslov>О б р а з л о ж е њ е</naslov>
                <predmet_zalbe>
                </predmet_zalbe>
                <postupak_poverenika>
                </postupak_poverenika>
                <odluka>
                </odluka>
            </podaci_o_obrazlozenju><poverenik id=""></poverenik></resenje_body></resenje>`;
    Xonomy.render(xmlStringR, elementR, {
      validate: this.zalbaCutanjeService.resenjeSpecification.validate,
      elements: this.zalbaCutanjeService.resenjeSpecification.elements,
      //onchange: () => { this.disabled = !!Xonomy.warnings.length }
    });*/

  }


  public submit(): void {
    if (Xonomy.warnings.length) {
      this.snackBar.open("Popunite sva obavezna polja!", 'Ok', { duration: 3000 });
      return;
    }
    console.log(Xonomy.harvest())
    let data = Xonomy.harvest();
    const mjestoAtr = data.split('mjesto=')[1].split(' datum')[0];
    const datumAtr = data.split('datum=')[1].split('><zc:sadrzaj_zalbe>')[0];
    const zahtev = data.split('<zc:zahtev>')[1].split('</zc:zahtev>')[0];
    const ciljani_organ_vlasi = data.split('<re:ciljani_organ_vlasti>')[1].split('</re:ciljani_organ_vlasti>')[0];
    const razlog_zalbe = data.split('<re:razlog_zalbe>')[1].split('</re:razlog_zalbe>')[0];
    const datum = data.split('<re:datum>')[1].split('</re:datum>')[0];
    const podaci_o_zahtjevu_i_informacijama = data.split('<re:podaci_o_zahtjevu_i_informacijama>')[1].split('</re:podaci_o_zahtjevu_i_informacijama>')[0];
    let dataTemplate = `<zc:zalba_cutanje 
    xmlns:zc="http://www.zalbacutanje" 
    xmlns:re="http://www.reusability"
    xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
    xsi:schemaLocation="http://www.zalbacutanje ../xsd/zalba_cutanje.xsd"><zc:zalba_cutanje_body  mjesto=${mjestoAtr} datum=${datumAtr}><zc:zahtev>${zahtev}</zc:zahtev><zc:sadrzaj_zalbe>
        <re:clan></re:clan>
        <re:ciljani_organ_vlasti>${ciljani_organ_vlasi}</re:ciljani_organ_vlasti>
        <re:razlog_zalbe>${razlog_zalbe}</re:razlog_zalbe>
        <re:datum>${datum}</re:datum>
        <re:podaci_o_zahtjevu_i_informacijama>${podaci_o_zahtjevu_i_informacijama}</re:podaci_o_zahtjevu_i_informacijama>
        <re:napomena></re:napomena>
    </zc:sadrzaj_zalbe><zc:podaci_o_podnosiocu><re:osoba><re:ime></re:ime><re:prezime></re:prezime></re:osoba><re:adresa><re:mesto></re:mesto><re:ulica broj="0"></re:ulica></re:adresa><re:drugi_podaci_za_kontakt></re:drugi_podaci_za_kontakt></zc:podaci_o_podnosiocu></zc:zalba_cutanje_body></zc:zalba_cutanje>`;
  
    console.log(dataTemplate)
    this.zalbaCutanjeService.send("zalba-cutanje", dataTemplate)
      .subscribe(res => console.log(res));
    this.snackBar.open("Uspešno ste poslali žalbu!", 'Ok', { duration: 3000 });

  }

}
