import { ComponentFixture, TestBed } from '@angular/core/testing';

import { DetaljniPrikazZahtevaComponent } from './detaljni-prikaz-zahteva.component';

describe('DetaljniPrikazZahtevaComponent', () => {
  let component: DetaljniPrikazZahtevaComponent;
  let fixture: ComponentFixture<DetaljniPrikazZahtevaComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ DetaljniPrikazZahtevaComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(DetaljniPrikazZahtevaComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
