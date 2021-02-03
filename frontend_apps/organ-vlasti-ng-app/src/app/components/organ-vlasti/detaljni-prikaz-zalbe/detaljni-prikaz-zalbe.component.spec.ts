import { ComponentFixture, TestBed } from '@angular/core/testing';

import { DetaljniPrikazZalbeComponent } from './detaljni-prikaz-zalbe.component';

describe('DetaljniPrikazZalbeComponent', () => {
  let component: DetaljniPrikazZalbeComponent;
  let fixture: ComponentFixture<DetaljniPrikazZalbeComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ DetaljniPrikazZalbeComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(DetaljniPrikazZalbeComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
