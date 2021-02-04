import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AddOrganVlastiFormComponent } from './add-organ-vlasti-form.component';

describe('AddOrganVlastiFormComponent', () => {
  let component: AddOrganVlastiFormComponent;
  let fixture: ComponentFixture<AddOrganVlastiFormComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ AddOrganVlastiFormComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(AddOrganVlastiFormComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
