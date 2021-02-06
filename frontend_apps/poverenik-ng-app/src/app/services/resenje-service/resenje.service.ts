import { Injectable } from '@angular/core';
import {HttpClient, HttpHeaders} from '@angular/common/http';
import {Observable} from 'rxjs';

declare const Xonomy: any;

@Injectable({
  providedIn: 'root'
})
export class ResenjeService {
  private headers = new HttpHeaders({'Content-Type': 'application/xml'});
  constructor(private http: HttpClient) { }

  getResenjeList(): Observable<any> {
    return this.http.get('http://localhost:8085/resenje',
      {headers: this.headers, responseType: 'text'});
  }
  getResenjeListByUser(): Observable<any> {
    return this.http.get('http://localhost:8085/resenje/by-user',
      {headers: this.headers, responseType: 'text'});
  }
  convertResenjePDF(broj: string): Observable<any> {
    return this.http.get('http://localhost:8085/resenje/toPdf/' + broj,
      {headers: this.headers, responseType: 'blob'});
  }
  convertResenjeXHTML(s: string): Observable<any> {
    return this.http.get('http://localhost:8085/resenje/toXhtml/' + s,
      {headers: this.headers, responseType: 'blob'});
  }

  convertResenjeRDF(broj: string): Observable<any> {
    console.log(broj)
    return this.http.get('http://localhost:8085/resenje/toRdf/' + broj,
      {headers: this.headers, responseType: 'blob'});
  }
  convertResenjeJSON(s: string): Observable<any> {
    return this.http.get('http://localhost:8085/resenje/toJson/' + s,
      {headers: this.headers, responseType: 'blob'});
  }

  getPretragaTekst(input): Observable<any> {
    return this.http.get('http://localhost:8085/resenje/search-text?input=' + input,
    {headers: this.headers, responseType: 'text'});
  }
  getPretragaMetadata(poverenik, trazilac, zalba, datumAfter, datumBefore, tip, organVlasti, mesto): Observable<any> {
    return this.http.get('http://localhost:8085/resenje/search-metadata?poverenik=' + poverenik + '&trazilac=' + trazilac + '&zalba=' + zalba + '&datumAfter=' + datumAfter + '&datumBefore=' + datumBefore + '&tip=' + tip + '&organVlasti=' + organVlasti + '&mesto=' + mesto,
      {headers: this.headers, responseType: 'text'});
  }


  send(body: any): Observable<any> {
    const headers = new HttpHeaders({ 'Content-Type': 'application/xml' });
    return this.http.post("http://localhost:8085/resenje", body, { headers: headers });
  }


  public resenjeSpecification = {
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
      "resenje": {
        attributes: {
          "xsi:schemaLocation": {
            isInvisible: true,
          }
        },
      },
      "resenje_body": {
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
          }
        }
      },
      "tip_resenja": {
        validate: function (jsElement) {
          if (!jsElement.getText()){
            Xonomy.warnings.push({
              htmlID: jsElement.htmlID,
              text: "Morate uneti tip."
            });
          }
        },
        hasText: true,
        asker: Xonomy.askPicklist,
        askerParameter: [{value: "prihvacena", caption: "osnovana zalba"}, {value: "odbijena", caption: "neosnovana zalba"}],
      },
      "trazilac": {
        hasText: true,
        asker: Xonomy.askString,
        attributes: {
          "id": {
            isInvisible: true
          }
        },
        menu: [
          {
            caption: "Obrisi",
            action: Xonomy.deleteElement,
            hideIf: function(jsElement){
              return jsElement.parent().name === "uvodne_informacije"
            }
          }
        ],
      },
      "uvodne_informacije": {
        hasText: true,
        asker: Xonomy.askStringLong,
        menu: [
          {
            caption: "Dodaj clan",
            action: Xonomy.newElementChild,
            actionParameter: "<clan broj='0'><stav broj='0'></stav><tacka broj='0'></tacka></clan>"
          },
          {
            caption: "Dodaj broj odluke",
            action: Xonomy.newElementChild,
            actionParameter: "<broj_odluke></broj_odluke>"
          }
        ]
      },
      "clan": {
        hasText: true,
        asker: Xonomy.askString,
        menu: [
          {
            caption: "Obrisi",
            action: Xonomy.deleteElement
          }
        ],
        attributes: {
          "broj": {
            asker: Xonomy.askString
          }
        },
      },
      "stav": {
        hasText: true,
        asker: Xonomy.askString,
        menu: [
          {
            caption: "Obrisi",
            action: Xonomy.deleteElement
          }
        ],
        attributes: {
          "broj": {
            asker: Xonomy.askString
          }
        },
      },
      "tacka": {
        hasText: true,
        asker: Xonomy.askString,
        menu: [
          {
            caption: "Obrisi",
            action: Xonomy.deleteElement
          }
        ],
        attributes: {
          "broj": {
            asker: Xonomy.askString
          }
        },
      },
      "broj_odluke": {
        hasText: true,
        asker: Xonomy.askString,
        menu: [
          {
            caption: "Obrisi",
            action: Xonomy.deleteElement
          }
        ],
      },
      "naslov": {
        isInvisible: true
      },
      "lice": {
        hasText: true,
        asker: Xonomy.askString,
        menu: [
          {
            caption: "Obrisi",
            action: Xonomy.deleteElement,
            hideIf: function(jsElement){
              return jsElement.parent().name === "uvodne_informacije"
            }
          }
        ],
      },
      "mesto": {
        hasText: true,
        asker: Xonomy.askString,
        menu: [
          {
            caption: "Obrisi",
            action: Xonomy.deleteElement,
            hideIf: function(jsElement){
              return jsElement.parent().parent().name === "uvodne_informacije"
            }
          }
        ],
      },
      "ulica": {
        hasText: true,
        asker: Xonomy.askString,
        attributes: {
          "broj": {
            asker: Xonomy.askString
          }
        },
      },
      "podaci_o_resenju": {
        hasText: true,
        asker: Xonomy.askStringLong,
        menu: [
          {
            caption: "Dodaj tacku",
            action: Xonomy.newElementChild,
            actionParameter: "<tacka broj='0'></tacka>"
          },
        ]
      },
      "predmet_zalbe": {
        hasText: true,
        asker: Xonomy.askStringLong,
        menu: [
          {
            caption: "Dodaj trazioca",
            action: Xonomy.newElementChild,
            actionParameter: "<trazilac id='0'></trazilac>"
          },
          {
            caption: "Dodaj datum",
            action: Xonomy.newElementChild,
            actionParameter: "<datum></datum>"
          },
          {
            caption: "Dodaj lice",
            action: Xonomy.newElementChild,
            actionParameter: "<lice></lice>"
          },
          {
            caption: "Dodaj mesto",
            action: Xonomy.newElementChild,
            actionParameter: "<mesto></mesto>"
          },
        ]
      },
      "datum": {
        hasText: true,
        asker: Xonomy.askDate,
        menu: [
          {
            caption: "Obrisi",
            action: Xonomy.deleteElement,
            hideIf: function(jsElement){
              return jsElement.parent().name === "uvodne_informacije"
            }
          }
        ],
      },
      "iznos": {
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
        menu: [
          {
            caption: "Obrisi",
            action: Xonomy.deleteElement
          }
        ],
      },
      "postupak_poverenika": {
        hasText: true,
        asker: Xonomy.askStringLong,
        menu: [
          {
            caption: "Dodaj clan",
            action: Xonomy.newElementChild,
            actionParameter: "<clan broj='0'><stav broj='0'></stav><tacka broj='0'></tacka></clan>"
          },
          {
            caption: "Dodaj datum",
            action: Xonomy.newElementChild,
            actionParameter: "<datum></datum>"
          },
          {
            caption: "Dodaj lice",
            action: Xonomy.newElementChild,
            actionParameter: "<lice></lice>"
          },
          {
            caption: "Dodaj mesto",
            action: Xonomy.newElementChild,
            actionParameter: "<mesto></mesto>"
          },
        ]
      },
      "odluka": {
        hasText: true,
        asker: Xonomy.askStringLong,
        menu: [
          {
            caption: "Dodaj clan",
            action: Xonomy.newElementChild,
            actionParameter: "<clan broj='0'><stav broj='0'></stav><tacka broj='0'></tacka></clan>"
          },
          {
            caption: "Dodaj datum",
            action: Xonomy.newElementChild,
            actionParameter: "<datum></datum>"
          },
          {
            caption: "Dodaj lice",
            action: Xonomy.newElementChild,
            actionParameter: "<lice></lice>"
          },
          {
            caption: "Dodaj mesto",
            action: Xonomy.newElementChild,
            actionParameter: "<mesto></mesto>"
          },
          {
            caption: "Dodaj stav",
            action: Xonomy.newElementChild,
            actionParameter: "<stav broj='0'></stav>"
          },
          {
            caption: "Dodaj iznos",
            action: Xonomy.newElementChild,
            actionParameter: "<iznos></iznos>"
          },
          {
            caption: "Dodaj trazioca",
            action: Xonomy.newElementChild,
            actionParameter: "<trazilac id='0'></trazilac>"
          },
        ]
      },
      "poverenik": {
        validate: function (jsElement) {
        },
        hasText: true,
        asker: Xonomy.askString,
        attributes: {
          "id": {
            isInvisible: true
          }
        },
        menu: []
      },

    }
  }
}
