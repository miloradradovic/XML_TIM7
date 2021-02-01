import { ComponentFixture, TestBed } from '@angular/core/testing';

import { PoverenikSveZalbeComponent } from './poverenik-sve-zalbe.component';

describe('PoverenikSveZalbeComponent', () => {
  let component: PoverenikSveZalbeComponent;
  let fixture: ComponentFixture<PoverenikSveZalbeComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ PoverenikSveZalbeComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(PoverenikSveZalbeComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
