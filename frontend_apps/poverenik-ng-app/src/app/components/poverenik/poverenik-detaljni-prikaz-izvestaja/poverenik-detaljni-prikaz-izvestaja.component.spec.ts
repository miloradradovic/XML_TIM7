import { ComponentFixture, TestBed } from '@angular/core/testing';

import { PoverenikDetaljniPrikazIzvestajaComponent } from './poverenik-detaljni-prikaz-izvestaja.component';

describe('PoverenikDetaljniPrikazIzvestajaComponent', () => {
  let component: PoverenikDetaljniPrikazIzvestajaComponent;
  let fixture: ComponentFixture<PoverenikDetaljniPrikazIzvestajaComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ PoverenikDetaljniPrikazIzvestajaComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(PoverenikDetaljniPrikazIzvestajaComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
