import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';

declare const Xonomy: any;
const domParser = new DOMParser();
const xmlSerializer = new XMLSerializer();

@Injectable({
  providedIn: 'root'
})
export class ZalbaCutanjeService {

  send(path: string, body: any): Observable<any> {
    const headers = new HttpHeaders({ 'Content-Type': 'application/xml' });
    return this.http.post("http://localhost:8085/zalba-cutanje", body, { headers: headers });
  }

  public zalbaCutanjeSpecification = {
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
      "zc:zalba_cutanje": {
        displayName: "žalba",
        validate: function (jsElement) {
          if (!jsElement.hasElements()) {
            Xonomy.warnings.push({
              htmlID: jsElement.htmlID,
              text: "Element ne smije biti prazan."
            }
            );
          }
        },
        attributes: {
          "xsi:schemaLocation": {
            isInvisible: true,
          }
        },
        menu: []
      },
      "zc:zalba_cutanje_body": {
        displayName: " ",
        validate: function (jsElement) {
          if (!jsElement.hasElements()) {
            Xonomy.warnings.push({
              htmlID: jsElement.htmlID,
              text: "Element ne smije biti prazan."
            }
            );
          }
          if (!jsElement.hasAttribute("mjesto")) {
            Xonomy.warnings.push({
              htmlID: jsElement.htmlID,
              text: "Element mora imati atribut 'mjesto'."
            }
            );
          }
          if (!jsElement.hasAttribute("datum")) {
            Xonomy.warnings.push({
              htmlID: jsElement.htmlID,
              text: "Element mora imati atribut 'datum'."
            }
            );
          }
          if (jsElement.getAttribute("mjesto").value===""){
            Xonomy.warnings.push({
              htmlID: jsElement.htmlID,
              text: "Morate unijeti mjesto."
            });
          }
          if (jsElement.getAttribute("datum").value===""){
            Xonomy.warnings.push({
              htmlID: jsElement.htmlID,
              text: "Morate unijeti datum."
            });
          }
        },
        menu: [],
        attributes: {
          "mjesto": {
            asker: Xonomy.askString,
            menu: [],
          },
          "datum": {
            asker: Xonomy.askDate,
            menu: [],
          }
        }
      },
      "zc:sadrzaj_zalbe": {
        displayName: "sadržaj žalbe",
        validate: function (jsElement) {
          if (!jsElement.hasChildElement("re:clan")) {
            Xonomy.warnings.push({
              htmlID: jsElement.htmlID,
              text: "Element mora imati podelement 'clan'."
            }
            );
          }
          if (!jsElement.hasChildElement("re:ciljani_organ_vlasti")) {
            Xonomy.warnings.push({
              htmlID: jsElement.htmlID,
              text: "Element mora imati podelement 'ciljani_organ_vlasti'."
            }
            );
          }
          if (!jsElement.hasChildElement("re:razlog_zalbe")) {
            Xonomy.warnings.push({
              htmlID: jsElement.htmlID,
              text: "Element mora imati podelement 'razlog_zalbe'."
            }
            );
          }
          if (!jsElement.hasChildElement("re:datum")) {
            Xonomy.warnings.push({
              htmlID: jsElement.htmlID,
              text: "Element mora imati podelement 'datum'."
            }
            );
          }
          if (!jsElement.hasChildElement("re:podaci_o_zahtjevu_i_informacijama")) {
            Xonomy.warnings.push({
              htmlID: jsElement.htmlID,
              text: "Element mora imati podelement 'podaci_o_zahtjevu_i_informacijama'."
            }
            );
          }
          if (!jsElement.hasChildElement("re:napomena")) {
            Xonomy.warnings.push({
              htmlID: jsElement.htmlID,
              text: "Element mora imati podelement 'napomena'."
            }
            );
          }
        },
        menu: [],
      },
      "re:clan": {
        validate: function (jsElement) {
        },
        isInvisible: true,
        menu: []
      },
      "zc:zahtev": {
        displayName: "identifikator zahteva",
        validate: function (jsElement) {
          if (!jsElement.getText()) {
            Xonomy.warnings.push({
              htmlID: jsElement.htmlID,
              text: "Element ne smije biti prazan."
            }
            );
          }
        },
        asker: Xonomy.askString,
        hasText: true,
        menu: []
      },
      "re:ciljani_organ_vlasti": {
        displayName: "organ vlasti",
        validate: function (jsElement) {
          if (!jsElement.getText()) {
            Xonomy.warnings.push({
              htmlID: jsElement.htmlID,
              text: "Morate navesti organ vlasti."
            }
            );
          }
        },
        hasText: true,
        menu: []
      },
      "re:razlog_zalbe": {
        displayName: "razlog",
        validate: function (jsElement) {
        },
        menu: []
      },
      "re:opcija": {
        displayName: "opcija",
        validate: function (jsElement) {
        },
        attributes: {
          "izabran": {
            asker: Xonomy.askPicklist,
            askerParameter: [{value: "false", caption: "ne"}, {value: "true", caption: "da"}],
            menu: [],
          },
        },
        menu: []
      },
      "re:datum": {
        displayName: "datum",
        validate: function (jsElement) {
          if (!jsElement.getText()) {
            Xonomy.warnings.push({
              htmlID: jsElement.htmlID,
              text: "Morate navesti datum podnošenja zahteva."
            });
          }
        },
        asker: Xonomy.askDate,
        hasText: true,
        menu: []
      },
      "re:podaci_o_zahtjevu_i_informacijama": {
        displayName: "podaci",
        validate: function (jsElement) {
        },
        asker: Xonomy.askStringLong,
        hasText: true,
        menu: []
      },
      "re:napomena": {
        validate: function (jsElement) {
        },
        isInvisible: true,
        menu: []
      },
      "re:ime": {
        displayName: "ime",
        validate: function (jsElement) {
        },
        asker: Xonomy.askString,
        hasText: true,
        menu: []
      },
      "re:prezime": {
        displayName: "prezime",
        validate: function (jsElement) {
        },
        asker: Xonomy.askString,
        hasText: true,
        menu: []
      },
      "re:mesto": {
        displayName: "mesto",
        validate: function (jsElement) {
          if (!jsElement.getText()) {
            Xonomy.warnings.push({
              htmlID: jsElement.htmlID,
              text: "Morate navesti datum podnošenja zahteva."
            });
          }
        },
        asker: Xonomy.askString,
        hasText: true,
        menu: []
      },
      "re:ulica": {
        displayName: "ulica",
        validate: function (jsElement) {
        },
        attributes: {
          "broj": {
            asker: Xonomy.askString,
            menu: [],
          },
        },
        asker: Xonomy.askString,
        hasText: true,
        menu: []
      },
      "re:drugi_podaci_za_kontakt": {
        displayName: "drugi podaci za kontakt",
        validate: function (jsElement) {
        },
        hasText: true,
        menu: []
      },
      "re:adresa": {
        displayName: "adresa",
        validate: function (jsElement) {
        },
        menu: []
      },
      "re:osoba": {
        displayName: "podnosilac",
        validate: function (jsElement) {
        },
        menu: []
      },
      "zc:podaci_o_podnosiocu": {
        displayName: "podaci o podnosiocu",
        validate: function (jsElement) {
        },
        menu: []
      },
      
    }
  }
























    //PRIMJER ZA RJESENJE

  /*public resenjeSpecification = {
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
      "uvodne_informacije": {
        displayName: "uvodne informacije",
        validate: function (jsElement) {
          if (!jsElement.hasElements()) {
            Xonomy.warnings.push({
              htmlID: jsElement.htmlID,
              text: "Element ne smije biti prazan."
            }
            );
          }
        },
        hasText: true,
        mustBeAfter: ["datum"],
        menu: [{
          caption: "Dodaj clan",
          action: Xonomy.newElementChild,
          actionParameter: "<clan></clan>"
          }]
      },
      
    }
  }*/

  constructor(private http: HttpClient) { }
}
