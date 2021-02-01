import { ComponentFixture, TestBed } from '@angular/core/testing';

import { PoverenikSvaResenjaComponent } from './poverenik-sva-resenja.component';

describe('PoverenikSvaResenjaComponent', () => {
  let component: PoverenikSvaResenjaComponent;
  let fixture: ComponentFixture<PoverenikSvaResenjaComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ PoverenikSvaResenjaComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(PoverenikSvaResenjaComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
