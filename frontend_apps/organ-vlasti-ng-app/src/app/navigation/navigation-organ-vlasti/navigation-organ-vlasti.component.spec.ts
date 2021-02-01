import { ComponentFixture, TestBed } from '@angular/core/testing';

import { NavigationOrganVlastiComponent } from './navigation-organ-vlasti.component';

describe('NavigationOrganVlastiComponent', () => {
  let component: NavigationOrganVlastiComponent;
  let fixture: ComponentFixture<NavigationOrganVlastiComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ NavigationOrganVlastiComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(NavigationOrganVlastiComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
