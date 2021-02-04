import { ComponentFixture, TestBed } from '@angular/core/testing';

import { DetaljniPrikazResenjaComponent } from './detaljni-prikaz-resenja.component';

describe('DetaljniPrikazResenjaComponent', () => {
  let component: DetaljniPrikazResenjaComponent;
  let fixture: ComponentFixture<DetaljniPrikazResenjaComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ DetaljniPrikazResenjaComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(DetaljniPrikazResenjaComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
