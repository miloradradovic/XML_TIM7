import { ComponentFixture, TestBed } from '@angular/core/testing';

import { PoverenikDetaljniPrikazZalbeComponent } from './poverenik-detaljni-prikaz-zalbe.component';

describe('PoverenikDetaljniPrikazZalbeComponent', () => {
  let component: PoverenikDetaljniPrikazZalbeComponent;
  let fixture: ComponentFixture<PoverenikDetaljniPrikazZalbeComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ PoverenikDetaljniPrikazZalbeComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(PoverenikDetaljniPrikazZalbeComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
