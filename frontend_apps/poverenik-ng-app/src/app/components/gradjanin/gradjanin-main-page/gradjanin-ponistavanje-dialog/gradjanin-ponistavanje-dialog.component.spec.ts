import { ComponentFixture, TestBed } from '@angular/core/testing';

import { GradjaninPonistavanjeDialogComponent } from './gradjanin-ponistavanje-dialog.component';

describe('GradjaninPonistavanjeDialogComponent', () => {
  let component: GradjaninPonistavanjeDialogComponent;
  let fixture: ComponentFixture<GradjaninPonistavanjeDialogComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ GradjaninPonistavanjeDialogComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(GradjaninPonistavanjeDialogComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
