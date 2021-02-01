import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AddPoverenikFormComponent } from './add-poverenik-form.component';

describe('AddPoverenikFormComponent', () => {
  let component: AddPoverenikFormComponent;
  let fixture: ComponentFixture<AddPoverenikFormComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ AddPoverenikFormComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(AddPoverenikFormComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
