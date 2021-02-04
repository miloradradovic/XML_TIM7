import { ComponentFixture, TestBed } from '@angular/core/testing';

import { PoverenikDetaljniPrikazResenjaComponent } from './poverenik-detaljni-prikaz-resenja.component';

describe('PoverenikDetaljniPrikazResenjaComponent', () => {
  let component: PoverenikDetaljniPrikazResenjaComponent;
  let fixture: ComponentFixture<PoverenikDetaljniPrikazResenjaComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ PoverenikDetaljniPrikazResenjaComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(PoverenikDetaljniPrikazResenjaComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
