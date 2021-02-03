import { ComponentFixture, TestBed } from '@angular/core/testing';

import { KreiranjeIzvestajaComponent } from './kreiranje-izvestaja.component';

describe('KreiranjeIzvestajaComponent', () => {
  let component: KreiranjeIzvestajaComponent;
  let fixture: ComponentFixture<KreiranjeIzvestajaComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ KreiranjeIzvestajaComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(KreiranjeIzvestajaComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
