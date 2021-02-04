import { ComponentFixture, TestBed } from '@angular/core/testing';

import { DialogOdbijanjeComponent } from './dialog-odbijanje.component';

describe('DialogOdbijanjeComponent', () => {
  let component: DialogOdbijanjeComponent;
  let fixture: ComponentFixture<DialogOdbijanjeComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ DialogOdbijanjeComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(DialogOdbijanjeComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
