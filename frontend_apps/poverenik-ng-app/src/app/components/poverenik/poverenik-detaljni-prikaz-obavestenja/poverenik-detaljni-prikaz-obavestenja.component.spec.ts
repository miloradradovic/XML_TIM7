import { ComponentFixture, TestBed } from '@angular/core/testing';

import { PoverenikDetaljniPrikazObavestenjaComponent } from './poverenik-detaljni-prikaz-obavestenja.component';

describe('PoverenikDetaljniPrikazObavestenjaComponent', () => {
  let component: PoverenikDetaljniPrikazObavestenjaComponent;
  let fixture: ComponentFixture<PoverenikDetaljniPrikazObavestenjaComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ PoverenikDetaljniPrikazObavestenjaComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(PoverenikDetaljniPrikazObavestenjaComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
