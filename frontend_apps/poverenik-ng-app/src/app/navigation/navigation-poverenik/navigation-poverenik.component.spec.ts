import { ComponentFixture, TestBed } from '@angular/core/testing';

import { NavigationPoverenikComponent } from './navigation-poverenik.component';

describe('NavigationPoverenikComponent', () => {
  let component: NavigationPoverenikComponent;
  let fixture: ComponentFixture<NavigationPoverenikComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ NavigationPoverenikComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(NavigationPoverenikComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
