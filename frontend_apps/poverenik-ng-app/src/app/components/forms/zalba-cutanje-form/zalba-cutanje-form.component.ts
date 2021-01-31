import { Component, OnInit, ViewChild } from '@angular/core';
import { ZalbaCutanjeService } from 'src/app/services/zalba-cutanje-service/zalba-cutanje.service';

declare const Xonomy: any;

@Component({
  selector: 'app-zalba-cutanje-form',
  templateUrl: './zalba-cutanje-form.component.html',
  styleUrls: ['./zalba-cutanje-form.component.css']
})
export class ZalbaCutanjeFormComponent implements OnInit {

  disabled = true;


  constructor(private zalbaCutanjeService: ZalbaCutanjeService) { }


  ngOnInit(): void {
  }

  ngAfterViewInit(): void {
    let element = document.getElementById("zalbaCutanje");
    //fali STATUS ZALBE (neobradjena, u obradi, prihvacena, odbijena, ponistena) i LINK NA ZAHTJEV (id zahtjeva)
    //isto i kod druge zalbe
    let xmlString = 
    `<zc:zalba_cutanje 
      xmlns:zc="http://www.zalbacutanje" 
      xmlns:re="http://www.reusability"
      xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
      xsi:schemaLocation="http://www.zalbacutanje ../xsd/zalba_cutanje.xsd"><zc:zalba_cutanje_body  mjesto="" datum=""><zc:sadrzaj_zalbe>
          <re:clan></re:clan>
          У складу са чланом 22. Закона о слободном приступу информацијама од јавног значаја подносим жалбу против <re:ciljani_organ_vlasti></re:ciljani_organ_vlasti>
          због тога што орган власти<re:razlog_zalbe><re:opcija izabran="false">није поступио</re:opcija><re:opcija izabran="false">није поступио у целости</re:opcija><re:opcija izabran="false">у законском року</re:opcija></re:razlog_zalbe>
          по мом захтеву за слободан приступ информацијама од јавног значаја који сам поднео  том органу  дана<re:datum></re:datum>
          године, а којим сам тражио/ла да ми се у складу са Законом о слободном приступу информацијама од јавног значаја омогући увид- копија документа који садржи информације  о /у вези са <re:podaci_o_zahtjevu_i_informacijama></re:podaci_o_zahtjevu_i_informacijama>
          На основу изнетог, предлажем да Повереник уважи моју жалбу и омогући ми приступ траженој/им  информацији/ма. Као доказ , уз жалбу достављам копију захтева са доказом о предаји органу власти. Напомена: Код жалбе  због непоступању по захтеву у целости, треба приложити и добијени одговор органа власти.<re:napomena></re:napomena>
      </zc:sadrzaj_zalbe><zc:podaci_o_podnosiocu><re:osoba><re:ime></re:ime><re:prezime></re:prezime></re:osoba><re:adresa><re:mesto></re:mesto><re:ulica broj="0"></re:ulica></re:adresa><re:drugi_podaci_za_kontakt></re:drugi_podaci_za_kontakt></zc:podaci_o_podnosiocu></zc:zalba_cutanje_body></zc:zalba_cutanje>`;
    Xonomy.render(xmlString, element, {
      validate: this.zalbaCutanjeService.zalbaCutanjeSpecification.validate,
      elements: this.zalbaCutanjeService.zalbaCutanjeSpecification.elements,
      onchange: () => { this.disabled = !!Xonomy.warnings.length }
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
    //if (Xonomy.warnings.length) {return}
    console.log(Xonomy.harvest())
    let data = Xonomy.harvest();
    let replacedData = data.replace("У складу са чланом 22. Закона о слободном приступу информацијама од јавног значаја подносим жалбу против ", "")
    .replace("због тога што орган власти ", "")
    .replace("по мом захтеву за слободан приступ информацијама од јавног значаја који сам поднео  том органу  дана", "")
    .replace("године, а којим сам тражио/ла да ми се у складу са Законом о слободном приступу информацијама од јавног значаја омогући увид- копија документа који садржи информације  о /у вези са", "")
    .replace("На основу изнетог, предлажем да Повереник уважи моју жалбу и омогући ми приступ траженој/им  информацији/ма. Као доказ , уз жалбу достављам копију захтева са доказом о предаји органу власти. Напомена: Код жалбе  због непоступању по захтеву у целости, треба приложити и добијени одговор органа власти.", "")
    
    console.log(replacedData)
    this.zalbaCutanjeService.send("zalba-cutanje", replacedData)
      .subscribe(res => console.log(res));
  }

}
