import { ComponentFixture, TestBed } from '@angular/core/testing';

import { GradjaninMainPageComponent } from './gradjanin-main-page.component';

describe('GradjaninMainPageComponent', () => {
  let component: GradjaninMainPageComponent;
  let fixture: ComponentFixture<GradjaninMainPageComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ GradjaninMainPageComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(GradjaninMainPageComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
