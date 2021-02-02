import { literal } from '@angular/compiler/src/output/output_ast';
import { Component, OnInit } from '@angular/core';
import { MatSnackBar } from '@angular/material/snack-bar';
import { ZalbaOdlukaService } from 'src/app/services/zalba-odluka-service/zalba-odluka.service';

declare const Xonomy: any;

@Component({
  selector: 'app-zalba-odluka-form',
  templateUrl: './zalba-odluka-form.component.html',
  styleUrls: ['./zalba-odluka-form.component.css']
})
export class ZalbaOdlukaFormComponent implements OnInit {

  constructor(private zalbaOdlukaService: ZalbaOdlukaService, public snackBar: MatSnackBar) { }

  ngOnInit(): void {
  }

  ngAfterViewInit(): void {
    let elementOdluka = document.getElementById("zalbaOdluka");
    let xmlStringOdluka =
    `<?xml version="1.0" encoding="UTF-8"?>
    <zoc:zalba_odluka
        xmlns:zoc="http://www.zalbanaodlukucir" 
        xmlns:re="http://www.reusability"
        xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
        xmlns:xs="http://www.w3.org/2001/XMLSchema#"
        xsi:schemaLocation="http://www.zalbanaodlukucir ../xsd/zalbanaodlukucir.xsd"><zoc:zalba_odluka_body mesto="" datum=""><zoc:zahtev></zoc:zahtev><zoc:zalilac><re:tip_lica><re:osoba><re:ime></re:ime><re:prezime></re:prezime></re:osoba></re:tip_lica><re:adresa><re:mesto></re:mesto><re:ulica broj="0"></re:ulica></re:adresa><re:sediste_zalioca></re:sediste_zalioca></zoc:zalilac><zoc:protiv_resenja_zakljucka><re:naziv_organa_koji_je_doneo_odluku></re:naziv_organa_koji_je_doneo_odluku><re:broj></re:broj><re:od_godine></re:od_godine></zoc:protiv_resenja_zakljucka><zoc:sadrzaj><re:datum></re:datum><re:osnova_za_zalbu></re:osnova_za_zalbu><re:clan><re:stav></re:stav></re:clan></zoc:sadrzaj><zoc:podaci_o_podnosiocu_zalbe><re:osoba><re:ime></re:ime><re:prezime></re:prezime></re:osoba><re:adresa><re:mesto></re:mesto><re:ulica broj="0"></re:ulica></re:adresa><re:drugi_podaci_za_kontakt></re:drugi_podaci_za_kontakt></zoc:podaci_o_podnosiocu_zalbe></zoc:zalba_odluka_body></zoc:zalba_odluka>`;
    Xonomy.render(xmlStringOdluka, elementOdluka, {
      validate: this.zalbaOdlukaService.zalbaOdlukaSpecification.validate,
      elements: this.zalbaOdlukaService.zalbaOdlukaSpecification.elements,
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
    const mestoAtr = data.split('mesto=')[1].split(' datum')[0];
    const datumAtr = data.split('datum=')[1].split('><zoc:zahtev>')[0];
    const zahtev = data.split('<zoc:zahtev>')[1].split('</zoc:zahtev>')[0];
    const ime = data.split('<re:ime>')[1].split('</re:ime>')[0];
    const prezime = data.split('<re:prezime>')[1].split('</re:prezime>')[0];
    const adresaMesto = data.split('<re:mesto>')[1].split('</re:mesto>')[0];
    const adresaBroj = data.split('<re:ulica broj=')[1].split('>')[0];
    const adresaUlica = data.split('<re:ulica broj=')[1].split('>')[1].split('</re:ulica')[0];
    const sediste = data.split('<re:sediste_zalioca>')[1].split('</re:sediste_zalioca>')[0];
    const nazivOrgana = data.split('<re:naziv_organa_koji_je_doneo_odluku>')[1].split('</re:naziv_organa_koji_je_doneo_odluku>')[0];
    const broj = data.split('</re:naziv_organa_koji_je_doneo_odluku><re:broj>')[1].split('</re:broj><re:od_godine>')[0];
    const godina = data.split('<re:od_godine>')[1].split('</re:od_godine>')[0];
    const datum = data.split('<re:datum>')[1].split('</re:datum>')[0];
    const osnova = data.split('<re:osnova_za_zalbu>')[1].split('</re:osnova_za_zalbu>')[0];
    const imePodnosilac = data.split('<zoc:podaci_o_podnosiocu_zalbe><re:osoba><re:ime>')[1].split('</re:ime><re:prezime>')[0];
    const prezimePodnosilac = data.split('<zoc:podaci_o_podnosiocu_zalbe><re:osoba><re:ime>')[1].split('</re:ime><re:prezime>')[1].split('</re:prezime></re:osoba><re:adresa><re:mesto>')[0];
    const adresaMestoPodnosilac = data.split('<zoc:podaci_o_podnosiocu_zalbe><re:osoba><re:ime>')[1].split('</re:prezime></re:osoba><re:adresa><re:mesto>')[1].split('</re:mesto><re:ulica broj=')[0];
    const adresaBrojPodnosilac = data.split('<zoc:podaci_o_podnosiocu_zalbe><re:osoba><re:ime>')[1].split('</re:mesto><re:ulica broj=')[1].split('>')[0];
    const adresaUlicaPodnosilac = data.split('<zoc:podaci_o_podnosiocu_zalbe><re:osoba><re:ime>')[1].split('</re:mesto><re:ulica broj=')[1].split('>')[1].split('</re:ulica')[0];
    const kontaktPodnosilac = data.split('<re:drugi_podaci_za_kontakt>')[1].split('</re:drugi_podaci_za_kontakt>')[0];
   let dataTemplate = `<?xml version="1.0" encoding="UTF-8"?>
    <zoc:zalba_odluka
        xmlns:zoc="http://www.zalbanaodlukucir" 
        xmlns:re="http://www.reusability"
        xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
        xmlns:xs="http://www.w3.org/2001/XMLSchema#"
        xsi:schemaLocation="http://www.zalbanaodlukucir ../xsd/zalbanaodlukucir.xsd"><zoc:zalba_odluka_body mesto=${mestoAtr} datum=${datumAtr}><zoc:zahtev>${zahtev}</zoc:zahtev><zoc:zalilac><re:tip_lica><re:osoba><re:ime>${ime}</re:ime><re:prezime>${prezime}</re:prezime></re:osoba></re:tip_lica><re:adresa><re:mesto>${adresaMesto}</re:mesto><re:ulica broj=${adresaBroj}>${adresaUlica}</re:ulica></re:adresa><re:sediste_zalioca>${sediste}</re:sediste_zalioca></zoc:zalilac><zoc:protiv_resenja_zakljucka><re:naziv_organa_koji_je_doneo_odluku>${nazivOrgana}</re:naziv_organa_koji_je_doneo_odluku><re:broj>${broj}</re:broj><re:od_godine>${godina}</re:od_godine></zoc:protiv_resenja_zakljucka><zoc:sadrzaj>
             <re:datum>${datum}</re:datum>
             <re:osnova_za_zalbu>${osnova}</re:osnova_za_zalbu>
             <re:clan><re:stav></re:stav></re:clan>
         </zoc:sadrzaj><zoc:podaci_o_podnosiocu_zalbe><re:osoba><re:ime>${imePodnosilac}</re:ime><re:prezime>${prezimePodnosilac}</re:prezime></re:osoba><re:adresa><re:mesto>${adresaMestoPodnosilac}</re:mesto><re:ulica broj=${adresaBrojPodnosilac}>${adresaUlicaPodnosilac}</re:ulica></re:adresa><re:drugi_podaci_za_kontakt>${kontaktPodnosilac}</re:drugi_podaci_za_kontakt></zoc:podaci_o_podnosiocu_zalbe></zoc:zalba_odluka_body></zoc:zalba_odluka>`;
    
    console.log(dataTemplate)
    this.zalbaOdlukaService.send(dataTemplate)
      .subscribe(res => console.log(res));
    this.snackBar.open("Uspešno ste poslali žalbu!", 'Ok', { duration: 3000 });

  }

}
