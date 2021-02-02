import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';

declare const Xonomy: any;


@Injectable({
  providedIn: 'root'
})
export class ZalbaOdlukaService {

  send(body: any): Observable<any> {
    const headers = new HttpHeaders({ 'Content-Type': 'application/xml' });
    return this.http.post("http://localhost:8085/zalba-odluka", body, { headers: headers });
  }

  public zalbaOdlukaSpecification = {
    validate: function (jsElement) {
      let elementSpec = this.elements[jsElement.name];
      if (elementSpec.validate) elementSpec.validate(jsElement);
      for (let i = 0; i < jsElement.attributes.length; i++) {
        let jsAttribute = jsElement.attributes[i];
        let attributeSpec = elementSpec.attributes[jsAttribute.name];
        if (attributeSpec.validate) attributeSpec.validate(jsAttribute);
      };
      for (let i = 0; i < jsElement.children.length; i++) {
        let jsChild = jsElement.children[i];
        if (jsChild.type == "element") { 
          this.validate(jsChild);
        }
      }
    },
    elements: {
      "zoc:zalba_odluka": {
        displayName: "zalba_odluka",
        attributes: {
          "xsi:schemaLocation": {
            isInvisible: true,
          }
        },
      },
      "zoc:zalba_odluka_body": {
        displayName: "zalba_odluka_body",
        validate: function (jsElement) {
          if (jsElement.getAttribute("mesto").value===""){
            Xonomy.warnings.push({
              htmlID: jsElement.htmlID,
              text: "Morate uneti mesto."
            });
          }
          if (jsElement.getAttribute("datum").value===""){
            Xonomy.warnings.push({
              htmlID: jsElement.htmlID,
              text: "Morate uneti datum."
            });
          }
        },
        attributes: {
          "mesto": {
            asker: Xonomy.askString,
          },
          "datum": {
            asker: Xonomy.askDate,
          }
        }
      },
      "zoc:zahtev": {
        displayName: "zahtev",
        validate: function (jsElement) {
          if (!jsElement.getText()){
            Xonomy.warnings.push({
              htmlID: jsElement.htmlID,
              text: "Morate uneti identifikator zahteva."
            });
          }
        },
        hasText: true,
        asker: Xonomy.askString
      },
      "zoc:zalilac": {
        displayName: "zalilac",
      },
      "re:tip_lica": {
        displayName: "tip_lica",
      },
      "re:ime": {
        displayName: "ime",
        hasText: true,
        asker: Xonomy.askString
      },
      "re:prezime": {
        displayName: "prezime",
        hasText: true,
        asker: Xonomy.askString
      },
      "re:mesto": {
        displayName: "mesto",
        hasText: true,
        asker: Xonomy.askString
      },
      "re:ulica": {
        displayName: "ulica",
        hasText: true,
        asker: Xonomy.askString,
        attributes: {
          "broj": {
            asker: Xonomy.askString
          }
        }
      },
      "re:sediste_zalioca": {
        displayName: "sediste_zalioca",
        hasText: true,
        asker: Xonomy.askString
      },
      "zoc:protiv_resenja_zakljucka": {
        displayName: "protiv_resenja_zakljucka",
      },
      "re:osoba": {
        displayName: "osoba",
      },
      "re:adresa": {
        displayName: "adresa",
      },
      "re:naziv_organa_koji_je_doneo_odluku": {
        displayName: "naziv_organa_koji_je_doneo_odluku",
        hasText: true,
        asker: Xonomy.askString
      },
      "re:broj": {
        displayName: "broj",
        hasText: true,
        asker: Xonomy.askString
      },
      "re:od_godine": {
        displayName: "od_godine",
        hasText: true,
        asker: Xonomy.askString
      },
      "zoc:sadrzaj": {
        displayName: "sadrzaj",
      },
      "re:datum": {
        displayName: "datum",
        validate: function (jsElement) {
          if (!jsElement.getText()) {
            Xonomy.warnings.push({
              htmlID: jsElement.htmlID,
              text: "Morate uneti datum."
            });
          }
        },
        asker: Xonomy.askDate,
        hasText: true
      },
      "re:osnova_za_zalbu": {
        displayName: "osnova_za_zalbu",
        hasText: true,
        asker: Xonomy.askStringLong
      },
      "zoc:podaci_o_podnosiocu_zalbe": {
        displayName: "podaci_o_podnosiocu_zalbe",
      },
      "re:drugi_podaci_za_kontakt": {
        displayName: "drugi drugi_podaci_za_kontakt za kontakt",
        hasText: true,
        asker: Xonomy.askStringLong
      },
      "re:clan": {
        isInvisible: true
      }
    }
  }

  constructor(private http: HttpClient) { }
}
