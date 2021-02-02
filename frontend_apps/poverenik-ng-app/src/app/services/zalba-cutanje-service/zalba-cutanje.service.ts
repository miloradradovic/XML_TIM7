import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';

declare const Xonomy: any;

@Injectable({
  providedIn: 'root'
})
export class ZalbaCutanjeService {

  send(body: any): Observable<any> {
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
        displayName: "zalba_cutanje",
        attributes: {
          "xsi:schemaLocation": {
            isInvisible: true,
          }
        }
      },
      "zc:zalba_cutanje_body": {
        displayName: "zalba_cutanje_body",
        validate: function (jsElement) {
          if (jsElement.getAttribute("mjesto").value===""){
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
          "mjesto": {
            displayName: "mesto",
            asker: Xonomy.askString
          },
          "datum": {
            asker: Xonomy.askDate
          }
        }
      },
      "zc:sadrzaj_zalbe": {
        displayName: "sadrzaj_zalbe žalbe"
      },
      "re:clan": {
        isInvisible: true
      },
      "zc:zahtev": {
        displayName: "zahtev",
        validate: function (jsElement) {
          if (!jsElement.getText()) {
            Xonomy.warnings.push({
              htmlID: jsElement.htmlID,
              text: "Morate uneti identifikator zahteva."
            }
            );
          }
        },
        asker: Xonomy.askString,
        hasText: true
      },
      "re:ciljani_organ_vlasti": {
        displayName: "ciljani_organ_vlasti",
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
        asker: Xonomy.askString
      },
      "re:razlog_zalbe": {
        displayName: "razlog_zalbe"
      },
      "re:opcija": {
        displayName: "opcija",
        asker: Xonomy.askForbidden,
        attributes: {
          "izabran": {
            asker: Xonomy.askPicklist,
            askerParameter: [{value: "false", caption: "ne"}, {value: "true", caption: "da"}]
          },
        }
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
        hasText: true
      },
      "re:podaci_o_zahtjevu_i_informacijama": {
        displayName: "podaci_o_zahtjevu_i_informacijama",
        asker: Xonomy.askStringLong,
        hasText: true,
      },
      "re:napomena": {
        isInvisible: true,
      },
      "re:ime": {
        displayName: "ime",
        asker: Xonomy.askString,
        hasText: true,
      },
      "re:prezime": {
        displayName: "prezime",
        asker: Xonomy.askString,
        hasText: true,
      },
      "re:mesto": {
        displayName: "mesto",
        asker: Xonomy.askString,
        hasText: true,
      },
      "re:ulica": {
        displayName: "ulica",
        attributes: {
          "broj": {
            asker: Xonomy.askString,
          },
        },
        asker: Xonomy.askString,
        hasText: true,
      },
      "re:drugi_podaci_za_kontakt": {
        displayName: "drugi_podaci_za_kontakt",
        hasText: true,
      },
      "re:adresa": {
        displayName: "adresa",
      },
      "re:osoba": {
        displayName: "osoba",
      },
      "zc:podaci_o_podnosiocu": {
        displayName: "podaci_o_podnosiocu",
      },
      
    }
  }

  constructor(private http: HttpClient) { }
}
