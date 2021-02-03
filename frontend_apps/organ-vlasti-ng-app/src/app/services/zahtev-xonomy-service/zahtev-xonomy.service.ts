import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';

declare const Xonomy: any;


@Injectable({
  providedIn: 'root'
})
export class ZahtevXonomyService {

  send(body: any): Observable<any> {
    const headers = new HttpHeaders({ 'Content-Type': 'application/xml' });
    return this.http.post("http://localhost:8090/zahtev", body, { headers: headers });
  }


  public zahtevSpecification = {
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
      "zcir:zahtev": {
        displayName: "zahtev",
        attributes: {
          "xsi:schemaLocation": {
            isInvisible: true,
          }
        },
      },
      "zcir:zahtev_body": {
        displayName: "zahtev_body",
        validate: function (jsElement) {
          if (jsElement.getAttribute("datum").value===""){
            Xonomy.warnings.push({
              htmlID: jsElement.htmlID,
              text: "Morate uneti datum."
            });
          }
        },
        attributes: {
          "datum": {
            asker: Xonomy.askDate,
          }
        }
      },
      "zcir:mesto": {
        displayName: "mesto",
        hasText: true,
        asker: Xonomy.askString
      },
      "zcir:ciljani_organ_vlasti": {
        displayName: "ciljani_organ_vlasti",
      },
      "re:naziv_organa": {
        displayName: "naziv_organa",
        hasText: true,
        asker: Xonomy.askString
      },
      "re:sediste_organa": {
        displayName: "sediste_organa",
        hasText: true,
        asker: Xonomy.askString
      },
      "zcir:tekst_zahteva": {
        displayName: "tekst_zahteva",
      },
      "re:clan": {
        isInvisible: true
      },
      "re:opcije": {
        displayName: "opcije",
        validate: function (jsElement) {
          let opcije = jsElement.children.slice(0,-1)
          let values = opcije.map(child => child.attributes[0].value)
          if (values.filter(value => value==='true').length !== 1){
            Xonomy.warnings.push({
              htmlID: jsElement.htmlID,
              text: "Izaberite jednu opciju."
            });
          }
        },
      },
      "re:opcija": {
        displayName: "opcija",
        attributes: {
          "izabran": {
            hasText: true,
            asker: Xonomy.askPicklist,
            askerParameter: [{value: "false", caption: "ne"}, {value: "true", caption: "da"}],
          }
        },
        asker: Xonomy.askForbidden
      },
      "re:nacini_dostave": {
        displayName: "nacini_dostave",
        validate: function (jsElement) {
          let opcije = jsElement.children.slice(0,-1)
          let values = opcije.map(child => child.attributes[0].value)
          if ((values.filter(value => value==='true').length !== 1) && !jsElement.children[3].children[1].getText()){
            Xonomy.warnings.push({
              htmlID: jsElement.htmlID,
              text: "Izaberite jednu opciju."
            });
          }
        },
      },
      "re:nacin_dostave": {
        displayName: "nacin_dostave",
        attributes: {
          "izabran": {
            hasText: true,
            asker: Xonomy.askPicklist,
            askerParameter: [{value: "false", caption: "ne"}, {value: "true", caption: "da"}],
          }
        },
        asker: Xonomy.askForbidden
      },
      "re:nacin_dostave_input": {
        displayName: "nacin_dostave_input",
        hasText: true,
        asker: Xonomy.askString
      },
      "re:informacija_o_zahtevu": {
        displayName: "informacija_o_zahtevu",
        hasText: true,
        asker: Xonomy.askStringLong,
        menu: [
          {
            caption: "Dodaj bold tekst",
            action: Xonomy.newElementChild,
            actionParameter: "<re:bold xmlns:re='http://www.reusability'></re:bold>"
          },
          {
            caption: "Dodaj italic tekst",
            action: Xonomy.newElementChild,
            actionParameter: "<re:italic xmlns:re='http://www.reusability'></re:italic>"
          }
        ]
      },
      "re:bold": {
        displayName: "bold",
        hasText: true,
        asker: Xonomy.askStringLong,
        menu: [
          {
            caption: "Obrisi",
            action: Xonomy.deleteElement,
          },
        ]
      },
      "re:italic": {
        displayName: "italic",
        hasText: true,
        asker: Xonomy.askStringLong,
        menu: [
          {
            caption: "Obrisi",
            action: Xonomy.deleteElement,
          },
        ]
      },
      "zcir:informacije_o_traziocu": {
        displayName: "informacije_o_traziocu",
      },
      "re:lice": {
        displayName: "lice",
      },
      "re:osoba": {
        displayName: "osoba",
      },
      "re:adresa": {
        displayName: "adresa",
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
        attributes: {
          "broj": {
            asker: Xonomy.askString,
          }
        },
        hasText: true,
        asker: Xonomy.askString
      },
      "re:drugi_podaci_za_kontakt": {
        displayName: "drugi_podaci_za_kontakt",
        hasText: true,
        asker: Xonomy.askString
      },
     


    }
  }

  constructor(private http: HttpClient) { }
}
