import { Component, OnInit, ViewChild } from '@angular/core';
import { MatRadioButton, MatRadioChange } from '@angular/material/radio';
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
    const today = new Date();
    const dd = String(today.getDate()).padStart(2, '0'); // day
    const mm = String(today.getMonth() + 1).padStart(2, '0'); // month
    const yyyy = today.getFullYear(); // year
    const hour =  String(today.getUTCHours()) === '23' ? String('00').padStart(2, '0') : String(today.getUTCHours() + 1).padStart(2, '0');
    const minutes = String(today.getMinutes()).padStart(2, '0');
    const seconds = String(today.getSeconds()).padStart(2, '0');
    const datumAtr = yyyy + '-' + mm + '-' + dd + 'T' + hour + ':' + minutes + ':' + seconds;
    let elementCutanje = document.getElementById("zalbaCutanje");
    let xmlStringCutanje =
    `<zc:zalba_cutanje
      xmlns:zc="http://www.zalbacutanje"
      xmlns:re="http://www.reusability"
      xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
      xsi:schemaLocation="http://www.zalbacutanje ../xsd/zalba_cutanje.xsd"><zc:zalba_cutanje_body  mjesto="" datum="${datumAtr}"><zc:zahtev></zc:zahtev><zc:sadrzaj_zalbe><re:clan></re:clan><re:ciljani_organ_vlasti></re:ciljani_organ_vlasti><re:razlog_zalbe><re:opcija izabran="false">није поступио</re:opcija><re:opcija izabran="false">није поступио у целости</re:opcija><re:opcija izabran="false">у законском року</re:opcija></re:razlog_zalbe><re:datum></re:datum><re:podaci_o_zahtjevu_i_informacijama></re:podaci_o_zahtjevu_i_informacijama><re:napomena></re:napomena></zc:sadrzaj_zalbe><zc:podaci_o_podnosiocu><re:osoba><re:ime></re:ime><re:prezime></re:prezime></re:osoba><re:adresa><re:mesto></re:mesto><re:ulica broj="0"></re:ulica></re:adresa><re:drugi_podaci_za_kontakt></re:drugi_podaci_za_kontakt></zc:podaci_o_podnosiocu></zc:zalba_cutanje_body></zc:zalba_cutanje>`;
    Xonomy.render(xmlStringCutanje, elementCutanje, {
      validate: this.zalbaCutanjeService.zalbaCutanjeSpecification.validate,
      elements: this.zalbaCutanjeService.zalbaCutanjeSpecification.elements,
      onchange: () => {
      }
    });
  }

  public submit(): void {
    if (Xonomy.warnings.length) {
      this.snackBar.open("Попуните сва обавезна поља!", 'Ok', { duration: 3000 });
      return;
    }
    //console.log(Xonomy.harvest())
    let data = Xonomy.harvest();
    const mjestoAtr = data.split('mjesto=')[1].split(' datum')[0];
    const datumAtr = data.split('datum=')[1].split('><zc:sadrzaj_zalbe>')[0];
    const zahtev = data.split('<zc:zahtev>')[1].split('</zc:zahtev>')[0];
    const ciljani_organ_vlasi = data.split('<re:ciljani_organ_vlasti>')[1].split('</re:ciljani_organ_vlasti>')[0];
    const razlog_zalbe = data.split('<re:razlog_zalbe>')[1].split('</re:razlog_zalbe>')[0];
    const datum = data.split('<re:datum>')[1].split('</re:datum>')[0];
    const podaci_o_zahtjevu_i_informacijama = data.split('<re:podaci_o_zahtjevu_i_informacijama>')[1].split('</re:podaci_o_zahtjevu_i_informacijama>')[0];
    const ime = data.split('<re:ime>')[1].split('</re:ime>')[0];
    const prezime = data.split('<re:prezime>')[1].split('</re:prezime>')[0];
    const mesto = data.split('<re:mesto>')[1].split('</re:mesto>')[0];
    const broj = data.split('</re:mesto><re:ulica broj=')[1].split('>')[0];
    const ulica = data.split('</re:mesto><re:ulica broj=')[1].split('>')[1].split('</re:ulica')[0];
    const drugi_podaci = data.split('<re:drugi_podaci_za_kontakt>')[1].split('</re:drugi_podaci_za_kontakt>')[0];
    let dataTemplate = `<zc:zalba_cutanje
    xmlns:zc="http://www.zalbacutanje"
    xmlns:re="http://www.reusability"
    xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
    xmlns:xs="http://www.w3.org/2001/XMLSchema#"
    xsi:schemaLocation="http://www.zalbacutanje ../xsd/zalba_cutanje.xsd"><zc:zalba_cutanje_body  mjesto=${mjestoAtr} datum=${datumAtr}><zc:zahtev>${zahtev}</zc:zahtev><zc:sadrzaj_zalbe>
        <re:clan></re:clan>
        <re:ciljani_organ_vlasti>${ciljani_organ_vlasi}</re:ciljani_organ_vlasti>
        <re:razlog_zalbe>${razlog_zalbe}</re:razlog_zalbe>
        <re:datum>${datum}</re:datum>
        <re:podaci_o_zahtjevu_i_informacijama>${podaci_o_zahtjevu_i_informacijama}</re:podaci_o_zahtjevu_i_informacijama>
        <re:napomena></re:napomena>
    </zc:sadrzaj_zalbe><zc:podaci_o_podnosiocu><re:osoba><re:ime>${ime}</re:ime><re:prezime>${prezime}</re:prezime></re:osoba><re:adresa><re:mesto>${mesto}</re:mesto><re:ulica broj=${broj}>${ulica}</re:ulica></re:adresa><re:drugi_podaci_za_kontakt>${drugi_podaci}</re:drugi_podaci_za_kontakt></zc:podaci_o_podnosiocu></zc:zalba_cutanje_body></zc:zalba_cutanje>`;
    this.zalbaCutanjeService.send(dataTemplate)
      .subscribe(res => {
          this.snackBar.open('Успешно сте послали жалбу на ћутање!', 'Ок', {duration: 3000});
        },
        error => {
          this.snackBar.open('Креирање жалбе на ћутање је тренутно немогуће!', 'Ок', {duration: 3000});
        });

  }

}
