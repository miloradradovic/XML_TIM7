import { ComponentFixture, TestBed } from '@angular/core/testing';

import { PoverenikProcitaniIzvestajiComponent } from './poverenik-procitani-izvestaji.component';

describe('PoverenikProcitaniIzvestajiComponent', () => {
  let component: PoverenikProcitaniIzvestajiComponent;
  let fixture: ComponentFixture<PoverenikProcitaniIzvestajiComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ PoverenikProcitaniIzvestajiComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(PoverenikProcitaniIzvestajiComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
