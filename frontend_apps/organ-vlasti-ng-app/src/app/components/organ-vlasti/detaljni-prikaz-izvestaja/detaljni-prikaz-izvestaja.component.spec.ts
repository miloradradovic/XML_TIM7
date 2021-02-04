import { ComponentFixture, TestBed } from '@angular/core/testing';

import { DetaljniPrikazIzvestajaComponent } from './detaljni-prikaz-izvestaja.component';

describe('DetaljniPrikazIzvestajaComponent', () => {
  let component: DetaljniPrikazIzvestajaComponent;
  let fixture: ComponentFixture<DetaljniPrikazIzvestajaComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ DetaljniPrikazIzvestajaComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(DetaljniPrikazIzvestajaComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
