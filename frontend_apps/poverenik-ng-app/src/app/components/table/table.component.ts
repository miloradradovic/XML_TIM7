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
  @Output() DoubleClick = new EventEmitter<number>();
  @Output() Ponisti = new EventEmitter<number>();
  @Output() XHTML = new EventEmitter<number>();
  @Output() PDF = new EventEmitter<number>();
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

  clicked(id): void {
    this.Click.emit(id);
  }

  doubleClicked(id): void {
    this.DoubleClick.emit(id);
  }

  ponisti(id): void {
    this.Ponisti.emit(id);
  }

  xhtml(id): void {
    this.XHTML.emit(id);
  }

  pdf(id): void {
    this.PDF.emit(id);
  }
}
