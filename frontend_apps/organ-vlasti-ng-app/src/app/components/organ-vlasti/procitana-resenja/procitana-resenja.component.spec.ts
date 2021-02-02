import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ProcitanaResenjaComponent } from './procitana-resenja.component';

describe('ProcitanaResenjaComponent', () => {
  let component: ProcitanaResenjaComponent;
  let fixture: ComponentFixture<ProcitanaResenjaComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ProcitanaResenjaComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(ProcitanaResenjaComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
