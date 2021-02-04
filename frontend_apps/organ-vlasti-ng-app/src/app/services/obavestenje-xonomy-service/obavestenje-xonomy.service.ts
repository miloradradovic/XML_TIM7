import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';

declare const Xonomy: any;

@Injectable({
  providedIn: 'root'
})
export class ObavestenjeXonomyService {

  send(body: any): Observable<any> {
    const headers = new HttpHeaders({ 'Content-Type': 'application/xml' });
    return this.http.post("http://localhost:8090/obavestenje", body, { headers: headers });
  }

  public obavestenjeSpecification = {
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
      "oba:obavestenje": {
        displayName: "obavestenje",
        attributes: {
          "xsi:schemaLocation": {
            isInvisible: true,
          }
        },
      },
      "oba:obavestenje_body": {
        displayName: "obavestenje_body",
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
          },
          "idZahteva": {
          }
        },
      },
      "oba:naziv_organa": {
        displayName: "naziv_organa",
        attributes: {
          "sediste": {
            asker: Xonomy.askString,
          }
        },
        hasText: true,
        asker: Xonomy.askString
      },
      "oba:informacije_o_podnosiocu": {
        displayName: "informacije_o_podnosiocu",
      },
      "re:lice": {
        displayName: "lice",
      },
      "re:osoba": {
        displayName: "osoba",
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
      "re:adresa": {
        displayName: "adresa",
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
      "oba:tekst_zahteva": {
        displayName: "tekst_zahteva",
      },
      "re:clan": {
        isInvisible: true
      },
      "re:godina": {
        displayName: "godina",
        validate: function (jsElement) {
          if (isNaN(jsElement.getText()) || jsElement.getText()<1000 || jsElement.getText()>9999 ){
            Xonomy.warnings.push({
              htmlID: jsElement.htmlID,
              text: "Validan format je 0000."
            });
          }
        },
        hasText: true,
        asker: Xonomy.askString
      },
      "re:dan": {
        displayName: "dan",
        validate: function (jsElement) {
          if (!jsElement.getText()){
            Xonomy.warnings.push({
              htmlID: jsElement.htmlID,
              text: "Obavezno polje."
            });
          }
        },
        hasText: true,
        asker: Xonomy.askDate
      },
      "re:vreme": {
        displayName: "vreme",
        validate: function (jsElement) {
          if (isNaN(jsElement.getText()) || jsElement.getText()<1 || jsElement.getText()>24 ){
            Xonomy.warnings.push({
              htmlID: jsElement.htmlID,
              text: "Unesite broj od 1 do 24."
            });
          }
        },
        hasText: true,
        asker: Xonomy.askString
      },
      "re:opis_trazene_informacije": {
        displayName: "opis_trazene_informacije",
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
      "re:radno_vreme": {
        displayName: "radno_vreme",
        asker: Xonomy.askForbidden,
      },
      "re:pocetak": {
        displayName: "pocetak",
        validate: function (jsElement) {
          if (isNaN(jsElement.getText()) || jsElement.getText()<1 || jsElement.getText()>24 ){
            Xonomy.warnings.push({
              htmlID: jsElement.htmlID,
              text: "Unesite broj od 1 do 24."
            });
          }
        },
        hasText: true,
        asker: Xonomy.askString,
      },
      "re:kraj": {
        displayName: "kraj",
        validate: function (jsElement) {
          if (isNaN(jsElement.getText()) || jsElement.getText()<1 || jsElement.getText()>24 ){
            Xonomy.warnings.push({
              htmlID: jsElement.htmlID,
              text: "Unesite broj od 1 do 24."
            });
          }
        },
        hasText: true,
        asker: Xonomy.askString,
      },
      "re:broj_kancelarije": {
        displayName: "broj_kancelarije",
        validate: function (jsElement) {
          if (!jsElement.getText() || isNaN(jsElement.getText())){
            Xonomy.warnings.push({
              htmlID: jsElement.htmlID,
              text: "Unesite broj."
            });
          }
        },
        hasText: true,
        asker: Xonomy.askString,
      },
      "re:opis_troskova": {
        isInvisible: true
      },
      "re:ukupan_trosak": {
        displayName: "ukupan_trosak",
      },
      "re:iznos": {
        displayName: "iznos",
        validate: function (jsElement) {
          if (jsElement.getText().split(',').length != 2 || isNaN(jsElement.getText().split(',')[0]) || isNaN(jsElement.getText().split(',')[1]) ){
            Xonomy.warnings.push({
              htmlID: jsElement.htmlID,
              text: "Validan format je 0.00."
            });
          }
        },
        hasText: true,
        asker: Xonomy.askString,
      },
      "re:broj_racuna": {
        isInvisible: true
      },
      "re:poziv_na_broj": {
        isInvisible: true
      },
      "oba:opcija_dostave": {
        displayName: "opcija_dostave",
        validate: function (jsElement) {
          let values = jsElement.children.map(child => child.attributes[0].value)
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

      
      
      








    }
  }

  constructor(private http: HttpClient) { }
}
