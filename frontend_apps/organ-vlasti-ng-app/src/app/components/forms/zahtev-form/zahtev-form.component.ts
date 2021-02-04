import { Component, OnInit } from '@angular/core';
import { MatSnackBar } from '@angular/material/snack-bar';
import { ZahtevXonomyService } from 'src/app/services/zahtev-xonomy-service/zahtev-xonomy.service';
import {Router} from '@angular/router';

declare const Xonomy: any;

@Component({
  selector: 'app-zahtev-form',
  templateUrl: './zahtev-form.component.html',
  styleUrls: ['./zahtev-form.component.css']
})
export class ZahtevFormComponent implements OnInit {

  constructor(private zahtevService: ZahtevXonomyService, public snackBar: MatSnackBar, private router: Router) { }

  ngOnInit(): void {
  }

  ngAfterViewInit(): void {
    let elementZahtev = document.getElementById("zahtev");
    let xmlStringZahtev =
    `<?xml version="1.0" encoding="UTF-8"?>
    <?xml-stylesheet type="text/xsl" href="../xsl/grddl.xsl"?>
    <zcir:zahtev
        xmlns:zcir="http://www.zahtevcir"
        xmlns:re="http://www.reusability"
        xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
        xmlns:xs="http://www.w3.org/2001/XMLSchema#"
        xsi:schemaLocation="http://www.zahtevcir ../xsd/zahtevcir.xsd"><zcir:zahtev_body datum=""><zcir:mesto></zcir:mesto><zcir:ciljani_organ_vlasti><re:naziv_organa></re:naziv_organa><re:sediste_organa></re:sediste_organa></zcir:ciljani_organ_vlasti><zcir:tekst_zahteva><re:clan><re:stav></re:stav></re:clan><re:opcije><re:opcija izabran="false">oбавештење да ли поседује тражену информацију;</re:opcija><re:opcija izabran="false">увид у документ који садржи тражену информацију;</re:opcija><re:opcija izabran="false">копију документа који садржи тражену информацију;</re:opcija><re:opcija izabran="false">достављање копије документа који садржи тражену информацију:**</re:opcija><re:nacini_dostave><re:nacin_dostave izabran="false">поштом</re:nacin_dostave><re:nacin_dostave izabran="false">електронском поштом</re:nacin_dostave><re:nacin_dostave izabran="false">факсом</re:nacin_dostave><re:nacin_dostave>на други начин:***<re:nacin_dostave_input></re:nacin_dostave_input></re:nacin_dostave></re:nacini_dostave></re:opcije><re:informacija_o_zahtevu></re:informacija_o_zahtevu></zcir:tekst_zahteva><zcir:informacije_o_traziocu><re:lice><re:osoba><re:ime></re:ime><re:prezime></re:prezime></re:osoba></re:lice><re:adresa><re:mesto></re:mesto><re:ulica broj="0"></re:ulica></re:adresa><re:drugi_podaci_za_kontakt></re:drugi_podaci_za_kontakt></zcir:informacije_o_traziocu></zcir:zahtev_body></zcir:zahtev>`;
    Xonomy.render(xmlStringZahtev, elementZahtev, {
      validate: this.zahtevService.zahtevSpecification.validate,
      elements: this.zahtevService.zahtevSpecification.elements,
      onchange: () => {
      }
    });
  }

  public submit(): void {
    if (Xonomy.warnings.length) {
      this.snackBar.open("Popunite sva obavezna polja!", 'Ok', { duration: 3000 });
      return;
    }
    console.log(Xonomy.harvest())
    let data = Xonomy.harvest();
    const datumAtr = data.split('datum=')[1].split('><zcir:mesto>')[0];
    const mesto = data.split('><zcir:mesto>')[1].split('</zcir:mesto>')[0];
    const nazivOrgana = data.split('<re:naziv_organa>')[1].split('</re:naziv_organa>')[0];
    const sedisteOrgana = data.split('<re:sediste_organa>')[1].split('</re:sediste_organa>')[0];
    const opcija1 = data.split('<re:opcije><re:opcija izabran=')[1].split('>oбавештење')[0];
    const opcija2 = data.split('>oбавештење да ли поседује тражену информацију;</re:opcija><re:opcija izabran=')[1].split('>увид')[0];
    const opcija3 = data.split('>увид у документ који садржи тражену информацију;</re:opcija><re:opcija izabran=')[1].split('>копију документа који садржи тражену информацију;</re:opcija><re:opcija izabran=')[0];
    const opcija4 = data.split('>копију документа који садржи тражену информацију;</re:opcija><re:opcija izabran=')[1].split('>достављање копије документа који садржи тражену информацију')[0];
    const opcija11 = data.split(':**</re:opcija><re:nacini_dostave><re:nacin_dostave izabran=')[1].split('>поштом</re:nacin_dostave><re:nacin_dostave izabran=')[0];
    const opcija12 = data.split('>поштом</re:nacin_dostave><re:nacin_dostave izabran=')[1].split('>електронском поштом</re:nacin_dostave><re:nacin_dostave izabran=')[0];
    const opcija13 = data.split('>електронском поштом</re:nacin_dostave><re:nacin_dostave izabran=')[1].split('>факсом</re:nacin_dostave><re:nacin_dostave>на други начин:***')[0];
    const opcijaInput = data.split('<re:nacin_dostave_input>')[1].split('</re:nacin_dostave_input>')[0];
    const opis = data.split('<re:informacija_o_zahtevu>')[1].split('</re:informacija_o_zahtevu>')[0];
    const ime = data.split('<re:ime>')[1].split('</re:ime>')[0];
    const prezime = data.split('<re:prezime>')[1].split('</re:prezime>')[0];
    const adresaMesto = data.split('<re:adresa><re:mesto>')[1].split('</re:mesto><re:ulica broj=')[0];
    const adresaBroj = data.split('<re:ulica broj=')[1].split('>')[0];
    const adresaUlica = data.split('<re:ulica broj=')[1].split('>')[1].split('</re:ulica')[0];
    const kontakt = data.split('<re:drugi_podaci_za_kontakt>')[1].split('</re:drugi_podaci_za_kontakt>')[0];

   let dataTemplate = `<?xml version="1.0" encoding="UTF-8"?>
   <?xml-stylesheet type="text/xsl" href="../xsl/grddl.xsl"?>
   <zcir:zahtev
       xmlns:zcir="http://www.zahtevcir"
       xmlns:re="http://www.reusability"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xmlns:xs="http://www.w3.org/2001/XMLSchema#"
       xsi:schemaLocation="http://www.zahtevcir ../xsd/zahtevcir.xsd">
       <zcir:zahtev_body datum=${datumAtr}><zcir:mesto>${mesto}</zcir:mesto><zcir:ciljani_organ_vlasti><re:naziv_organa>${nazivOrgana}</re:naziv_organa><re:sediste_organa>${sedisteOrgana}</re:sediste_organa></zcir:ciljani_organ_vlasti><zcir:tekst_zahteva>
               <re:clan><re:stav></re:stav></re:clan>
               <re:opcije><re:opcija izabran=${opcija1}>oбавештење да ли поседује тражену информацију;</re:opcija><re:opcija izabran=${opcija2}>увид у документ који садржи тражену информацију;</re:opcija><re:opcija izabran=${opcija3}>копију документа који садржи тражену информацију;</re:opcija><re:opcija izabran=${opcija4}>достављање копије документа који садржи тражену информацију:**</re:opcija><re:nacini_dostave><re:nacin_dostave izabran=${opcija11}>поштом</re:nacin_dostave><re:nacin_dostave izabran=${opcija12}>електронском поштом</re:nacin_dostave><re:nacin_dostave izabran=${opcija13}>факсом</re:nacin_dostave><re:nacin_dostave>на други начин:***<re:nacin_dostave_input>${opcijaInput}</re:nacin_dostave_input></re:nacin_dostave></re:nacini_dostave></re:opcije>
               <re:informacija_o_zahtevu>${opis}</re:informacija_o_zahtevu>
           </zcir:tekst_zahteva><zcir:informacije_o_traziocu><re:lice><re:osoba><re:ime>${ime}</re:ime><re:prezime>${prezime}</re:prezime></re:osoba></re:lice><re:adresa><re:mesto>${adresaMesto}</re:mesto><re:ulica broj=${adresaBroj}>${adresaUlica}</re:ulica></re:adresa><re:drugi_podaci_za_kontakt>${kontakt}</re:drugi_podaci_za_kontakt></zcir:informacije_o_traziocu></zcir:zahtev_body></zcir:zahtev>`;
     console.log(dataTemplate)
    this.zahtevService.send(dataTemplate)
      .subscribe(res => console.log(res));
    this.snackBar.open("Uspešno ste poslali zahtev!", 'Ok', { duration: 3000 });
    this.router.navigate(['/main-page-gradjanin']);
  }

}
