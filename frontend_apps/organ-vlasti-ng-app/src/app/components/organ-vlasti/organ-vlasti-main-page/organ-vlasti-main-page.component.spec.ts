import { ComponentFixture, TestBed } from '@angular/core/testing';

import { OrganVlastiMainPageComponent } from './organ-vlasti-main-page.component';

describe('OrganVlastiMainPageComponent', () => {
  let component: OrganVlastiMainPageComponent;
  let fixture: ComponentFixture<OrganVlastiMainPageComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ OrganVlastiMainPageComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(OrganVlastiMainPageComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
