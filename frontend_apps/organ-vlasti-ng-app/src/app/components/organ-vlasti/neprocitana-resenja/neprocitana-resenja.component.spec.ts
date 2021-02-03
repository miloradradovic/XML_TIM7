import { ComponentFixture, TestBed } from '@angular/core/testing';

import { NeprocitanaResenjaComponent } from './neprocitana-resenja.component';

describe('NeprocitanaResenjaComponent', () => {
  let component: NeprocitanaResenjaComponent;
  let fixture: ComponentFixture<NeprocitanaResenjaComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ NeprocitanaResenjaComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(NeprocitanaResenjaComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
