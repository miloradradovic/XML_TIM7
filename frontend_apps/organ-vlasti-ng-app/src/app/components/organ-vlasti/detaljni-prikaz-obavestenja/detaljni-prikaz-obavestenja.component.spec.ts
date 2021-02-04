import { ComponentFixture, TestBed } from '@angular/core/testing';

import { DetaljniPrikazObavestenjaComponent } from './detaljni-prikaz-obavestenja.component';

describe('DetaljniPrikazObavestenjaComponent', () => {
  let component: DetaljniPrikazObavestenjaComponent;
  let fixture: ComponentFixture<DetaljniPrikazObavestenjaComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ DetaljniPrikazObavestenjaComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(DetaljniPrikazObavestenjaComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
