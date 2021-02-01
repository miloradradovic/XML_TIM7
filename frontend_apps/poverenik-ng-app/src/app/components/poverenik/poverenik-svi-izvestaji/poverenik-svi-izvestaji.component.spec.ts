import { ComponentFixture, TestBed } from '@angular/core/testing';

import { PoverenikSviIzvestajiComponent } from './poverenik-svi-izvestaji.component';

describe('PoverenikSviIzvestajiComponent', () => {
  let component: PoverenikSviIzvestajiComponent;
  let fixture: ComponentFixture<PoverenikSviIzvestajiComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ PoverenikSviIzvestajiComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(PoverenikSviIzvestajiComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
