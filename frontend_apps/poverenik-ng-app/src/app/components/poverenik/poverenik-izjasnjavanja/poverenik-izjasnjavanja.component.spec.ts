import { ComponentFixture, TestBed } from '@angular/core/testing';

import { PoverenikIzjasnjavanjaComponent } from './poverenik-izjasnjavanja.component';

describe('PoverenikIzjasnjavanjaComponent', () => {
  let component: PoverenikIzjasnjavanjaComponent;
  let fixture: ComponentFixture<PoverenikIzjasnjavanjaComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ PoverenikIzjasnjavanjaComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(PoverenikIzjasnjavanjaComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
