import {Component, Input, OnChanges, OnInit, SimpleChanges} from '@angular/core';
import {DomSanitizer, SafeHtml} from '@angular/platform-browser';
import {StaticService} from '../../services/static-service/static.service';

@Component({
  selector: 'app-empty',
  templateUrl: './empty.component.html',
  styleUrls: ['./empty.component.css']
})
export class EmptyComponent implements OnInit, OnChanges {

  innerHtml: SafeHtml;

  @Input()
  source: string;
  @Input()
  isTrusted: boolean;

  constructor(
    private staticHtmlService: StaticService,
    private domSanitizer: DomSanitizer
  ) { }

  ngOnInit(): void {
    this.insertStaticView();
  }

  ngOnChanges(changes: SimpleChanges): void {
    if (!this.source) {
      return;
    }
    this.insertStaticView();
  }

  private insertStaticView(): void {
    this.staticHtmlService
      .getStaticHTML(this.source, this.isTrusted)
      .subscribe(response => { this.replaceHtml(response ); });
  }

  private replaceHtml(innerHTML: string): void {
    this.innerHtml = this.domSanitizer.bypassSecurityTrustHtml(innerHTML);
  }

}
