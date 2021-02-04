import { ComponentFixture, TestBed } from '@angular/core/testing';

import { PoverenikNeprocitaniIzvestajiComponent } from './poverenik-neprocitani-izvestaji.component';

describe('PoverenikNeprocitaniIzvestajiComponent', () => {
  let component: PoverenikNeprocitaniIzvestajiComponent;
  let fixture: ComponentFixture<PoverenikNeprocitaniIzvestajiComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ PoverenikNeprocitaniIzvestajiComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(PoverenikNeprocitaniIzvestajiComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
