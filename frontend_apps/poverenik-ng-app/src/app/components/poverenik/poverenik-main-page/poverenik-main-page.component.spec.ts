import { ComponentFixture, TestBed } from '@angular/core/testing';

import { PoverenikMainPageComponent } from './poverenik-main-page.component';

describe('PoverenikMainPageComponent', () => {
  let component: PoverenikMainPageComponent;
  let fixture: ComponentFixture<PoverenikMainPageComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ PoverenikMainPageComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(PoverenikMainPageComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
