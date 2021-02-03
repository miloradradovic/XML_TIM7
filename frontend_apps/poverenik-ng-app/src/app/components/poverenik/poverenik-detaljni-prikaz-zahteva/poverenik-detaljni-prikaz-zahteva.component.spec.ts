import { ComponentFixture, TestBed } from '@angular/core/testing';

import { PoverenikDetaljniPrikazZahtevaComponent } from './poverenik-detaljni-prikaz-zahteva.component';

describe('PoverenikDetaljniPrikazZahtevaComponent', () => {
  let component: PoverenikDetaljniPrikazZahtevaComponent;
  let fixture: ComponentFixture<PoverenikDetaljniPrikazZahtevaComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ PoverenikDetaljniPrikazZahtevaComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(PoverenikDetaljniPrikazZahtevaComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
