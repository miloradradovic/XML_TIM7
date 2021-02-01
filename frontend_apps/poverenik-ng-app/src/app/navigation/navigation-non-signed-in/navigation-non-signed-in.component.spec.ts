import { ComponentFixture, TestBed } from '@angular/core/testing';

import { NavigationNonSignedInComponent } from './navigation-non-signed-in.component';

describe('NavigationNonSignedInComponent', () => {
  let component: NavigationNonSignedInComponent;
  let fixture: ComponentFixture<NavigationNonSignedInComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ NavigationNonSignedInComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(NavigationNonSignedInComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
