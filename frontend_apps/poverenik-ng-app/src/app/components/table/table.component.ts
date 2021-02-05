import {Component, EventEmitter, Input, OnChanges, OnInit, Output, SimpleChanges} from '@angular/core';

@Component({
  selector: 'app-table',
  templateUrl: './table.component.html',
  styleUrls: ['./table.component.css']
})
export class TableComponent implements OnChanges {

  @Input() dataSource = [];
  @Input() columnsToDisplay = [];
  @Input() columnsToIterate = [];
  @Output() Click = new EventEmitter<number>();
  @Output() ClickZalba = new EventEmitter<string>();
  @Output() DoubleClick = new EventEmitter<number>();
  @Output() DoubleClickZalba = new EventEmitter<string>();
  @Output() Ponisti = new EventEmitter<number>();
  @Output() PonistiZalba = new EventEmitter<string>();
  @Output() XHTML = new EventEmitter<number>();
  @Output() XHTMLZalba = new EventEmitter<string>();
  @Output() PDF = new EventEmitter<number>();
  @Output() PDFZalba = new EventEmitter<string>();
  constructor() {

  }

  ngOnChanges(changes: SimpleChanges): void {
    for (const propName in changes) {
      if (changes.hasOwnProperty(propName)){
        let vary = this.get(propName);
        vary = changes[propName].currentValue;
      }
    }
  }
  get(element: string): string[] {
    switch (element) {
      case 'dataSource':
        return this.dataSource;
      case 'columnsToDisplay':
        return this.columnsToDisplay;
      default:
        return this.columnsToIterate;
    }
  }

  doubleClicked(id, tip: any): void {
    let returnValue = '';
    if (this.columnsToDisplay.includes('Идентификатор жалбе')){
      returnValue = tip + '/' + id;
      this.DoubleClickZalba.emit(returnValue);
    }else{
      this.DoubleClick.emit(id);
    }
  }

  ponisti(id, tip: any): void {
    let returnValue = '';
    if (this.columnsToDisplay.includes('Идентификатор жалбе')){
      returnValue = tip + '/' + id;
      this.PonistiZalba.emit(returnValue);
    }else{
      this.Ponisti.emit(id);
    }
  }

  xhtml(id, tip: any): void {
    let returnValue = '';
    if (this.columnsToDisplay.includes('Идентификатор жалбе')){
      returnValue = tip + '/' + id;
      this.XHTMLZalba.emit(returnValue);
    }else{
      this.XHTML.emit(id);
    }
  }

  pdf(id, tip: any): void {
    let returnValue = '';
    if (this.columnsToDisplay.includes('Идентификатор жалбе')){
      returnValue = tip + '/' + id;
      this.PDFZalba.emit(returnValue);
    }else{
      this.PDF.emit(id);
    }
  }

  clicked(id, tip: any): void {
    let returnValue = '';
    if (this.columnsToDisplay.includes('Идентификатор жалбе')){
      returnValue = tip + '/' + id;
      this.ClickZalba.emit(returnValue);
    }else{
      this.Click.emit(id);
    }
  }
}
