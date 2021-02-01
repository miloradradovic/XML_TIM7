import { ComponentFixture, TestBed } from '@angular/core/testing';

import { NavigationGradjaninComponent } from './navigation-gradjanin.component';

describe('NavigationGradjaninComponent', () => {
  let component: NavigationGradjaninComponent;
  let fixture: ComponentFixture<NavigationGradjaninComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ NavigationGradjaninComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(NavigationGradjaninComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
