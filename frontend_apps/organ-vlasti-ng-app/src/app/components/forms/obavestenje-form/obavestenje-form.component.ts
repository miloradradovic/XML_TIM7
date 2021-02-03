import { Component, OnInit } from '@angular/core';
import { MatSnackBar } from '@angular/material/snack-bar';
import { ObavestenjeXonomyService } from 'src/app/services/obavestenje-xonomy-service/obavestenje-xonomy.service';

declare const Xonomy: any;

@Component({
  selector: 'app-obavestenje-form',
  templateUrl: './obavestenje-form.component.html',
  styleUrls: ['./obavestenje-form.component.css']
})
export class ObavestenjeFormComponent implements OnInit {

  constructor(private obavestenjeService: ObavestenjeXonomyService, public snackBar: MatSnackBar) { }

  idZahteva = '1';

  ngOnInit(): void {
  }

  ngAfterViewInit(): void {
    let elementObavestenje = document.getElementById("obavestenje");
    let xmlStringObavestenje =
    `<?xml version="1.0" encoding="UTF-8"?>
    <?xml-stylesheet type="text/xsl" href="../xsl/grddl.xsl"?>
    <oba:obavestenje 
        xmlns:oba="http://www.obavestenje" 
        xmlns:re="http://www.reusability"
        xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
        xsi:schemaLocation="http://www.obavestenje ../xsd/obavestenje.xsd"><oba:obavestenje_body idZahteva='${this.idZahteva}' datum=""><oba:naziv_organa sediste=""></oba:naziv_organa><oba:informacije_o_podnosiocu><re:lice><re:osoba><re:ime></re:ime><re:prezime></re:prezime></re:osoba></re:lice><re:adresa><re:mesto></re:mesto><re:ulica broj="0"></re:ulica></re:adresa></oba:informacije_o_podnosiocu><oba:tekst_zahteva><re:clan><re:stav></re:stav></re:clan><re:godina></re:godina><re:opis_trazene_informacije></re:opis_trazene_informacije><re:dan></re:dan><re:vreme></re:vreme><re:radno_vreme>od <re:pocetak></re:pocetak> do <re:kraj></re:kraj></re:radno_vreme><re:adresa><re:mesto></re:mesto><re:ulica broj="0"></re:ulica></re:adresa><re:broj_kancelarije></re:broj_kancelarije><re:opis_troskova><re:cena valuta="dinar">00,00</re:cena><re:cena valuta="dinar">00,00</re:cena><re:cena valuta="dinar">00,00</re:cena><re:cena valuta="dinar">00,00</re:cena><re:cena valuta="dinar">00,00</re:cena><re:cena valuta="dinar">00,00</re:cena><re:cena valuta="dinar">00,00</re:cena><re:cena valuta="dinar">00,00</re:cena></re:opis_troskova><re:ukupan_trosak><re:iznos>0,00</re:iznos><re:broj_racuna>000-000000-000-00</re:broj_racuna><re:poziv_na_broj>97</re:poziv_na_broj></re:ukupan_trosak></oba:tekst_zahteva><oba:opcija_dostave><re:opcija izabran="false">Именованом</re:opcija><re:opcija izabran="false">Архиви</re:opcija></oba:opcija_dostave></oba:obavestenje_body></oba:obavestenje>`;
    Xonomy.render(xmlStringObavestenje, elementObavestenje, {
      validate: this.obavestenjeService.obavestenjeSpecification.validate,
      elements: this.obavestenjeService.obavestenjeSpecification.elements,
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

    const datumAtr = data.split('datum=')[1].split('><oba:naziv_organa sediste=')[0];
    const sedisteAtr = data.split('><oba:naziv_organa sediste=')[1].split('>')[0]
    const nazivOrgana = data.split('><oba:naziv_organa sediste=')[1].split('>')[1].split('</oba:naziv_organa')[0];
    const ime = data.split('<re:lice><re:osoba><re:ime>')[1].split('</re:ime>')[0];
    const prezime = data.split('<re:prezime>')[1].split('</re:prezime>')[0];
    const adresaMesto = data.split('<re:mesto>')[1].split('</re:mesto>')[0];
    const adresaBroj = data.split('<re:ulica broj=')[1].split('>')[0];
    const adresaUlica = data.split('<re:ulica broj=')[1].split('>')[1].split('</re:ulica')[0];
    const godina = data.split('<re:godina>')[1].split('</re:godina>')[0];
    const opis = data.split('<re:opis_trazene_informacije>')[1].split('</re:opis_trazene_informacije>')[0];
    const dan = data.split('<re:dan>')[1].split('</re:dan>')[0];
    const vreme = data.split('<re:vreme>')[1].split('</re:vreme>')[0];
    const radnoVremePocetak = data.split('<re:pocetak>')[1].split('</re:pocetak>')[0];
    const radnoVremeKraj = data.split('<re:kraj>')[1].split('</re:kraj>')[0];
    const adresaOrganaMesto = data.split('<re:adresa><re:mesto>')[2].split('</re:mesto><re:ulica broj=')[0];
    const adresaOrganaBroj = data.split('<re:adresa><re:mesto>')[2].split('</re:mesto><re:ulica broj=')[1].split('>')[0];
    const adresaOrganaUlica = data.split('<re:adresa><re:mesto>')[2].split('</re:mesto><re:ulica broj=')[1].split('>')[1].split('</re:ulica')[0];
    const brojKancelarije = data.split('<re:broj_kancelarije>')[1].split('</re:broj_kancelarije>')[0];
    const iznos = data.split('<re:iznos>')[1].split('</re:iznos>')[0];
    const opcija1 = data.split('<oba:opcija_dostave><re:opcija izabran=')[1].split('>Именованом</re:opcija><re:opcija izabran=')[0];
    const opcija2 = data.split('>Именованом</re:opcija><re:opcija izabran=')[1].split('>Архиви')[0];

   let dataTemplate = `<?xml version="1.0" encoding="UTF-8"?>
   <?xml-stylesheet type="text/xsl" href="../xsl/grddl.xsl"?>
   <oba:obavestenje 
       xmlns:oba="http://www.obavestenje" 
       xmlns:re="http://www.reusability"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xsi:schemaLocation="http://www.obavestenje ../xsd/obavestenje.xsd"><oba:obavestenje_body datum=${datumAtr}><oba:naziv_organa sediste=${sedisteAtr}>${nazivOrgana}</oba:naziv_organa><oba:informacije_o_podnosiocu><re:lice><re:osoba><re:ime>${ime}</re:ime><re:prezime>${prezime}</re:prezime></re:osoba></re:lice><re:adresa><re:mesto>${adresaMesto}</re:mesto><re:ulica broj=${adresaBroj}>${adresaUlica}</re:ulica></re:adresa></oba:informacije_o_podnosiocu><oba:tekst_zahteva>
               <re:clan><re:stav></re:stav></re:clan>
               <re:godina>${godina}</re:godina>,
               <re:opis_trazene_informacije>${opis}</re:opis_trazene_informacije>
               <re:dan>${dan}</re:dan>
               <re:vreme>${vreme}</re:vreme>
               <re:radno_vreme>
                   od <re:pocetak>${radnoVremePocetak}</re:pocetak> do <re:kraj>${radnoVremeKraj}</re:kraj>
               </re:radno_vreme> 
               <re:adresa><re:mesto>${adresaOrganaMesto}</re:mesto><re:ulica broj=${adresaOrganaBroj}>${adresaOrganaUlica}</re:ulica></re:adresa>
               <re:broj_kancelarije>${brojKancelarije}</re:broj_kancelarije>
               <re:opis_troskova>
                  <re:cena valuta="dinar">00,00</re:cena>
                  <re:cena valuta="dinar">00,00</re:cena>
                  <re:cena valuta="dinar">00,00</re:cena> 
                  <re:cena valuta="dinar">00,00</re:cena>
                  <re:cena valuta="dinar">00,00</re:cena>
                  <re:cena valuta="dinar">00,00</re:cena>
                  <re:cena valuta="dinar">00,00</re:cena>
                  <re:cena valuta="dinar">00,00</re:cena>
               </re:opis_troskova><re:ukupan_trosak>
                   <re:iznos>${iznos}</re:iznos>
                   <re:broj_racuna>000-000000-000-00</re:broj_racuna>,
                   <re:poziv_na_broj>97</re:poziv_na_broj>
               </re:ukupan_trosak>
           </oba:tekst_zahteva><oba:opcija_dostave><re:opcija izabran=${opcija1}>Именованом</re:opcija><re:opcija izabran=${opcija2}>Архиви</re:opcija></oba:opcija_dostave></oba:obavestenje_body></oba:obavestenje>`;
     console.log(dataTemplate)
    this.obavestenjeService.send(dataTemplate)
      .subscribe(res => console.log(res));
    this.snackBar.open("Uspešno ste poslali obavestenje!", 'Ok', { duration: 3000 });

  }

}
