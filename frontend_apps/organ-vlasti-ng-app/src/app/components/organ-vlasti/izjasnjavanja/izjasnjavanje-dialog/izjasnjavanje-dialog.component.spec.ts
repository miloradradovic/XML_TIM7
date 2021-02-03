import { ComponentFixture, TestBed } from '@angular/core/testing';

import { IzjasnjavanjeDialogComponent } from './izjasnjavanje-dialog.component';

describe('IzjasnjavanjeDialogComponent', () => {
  let component: IzjasnjavanjeDialogComponent;
  let fixture: ComponentFixture<IzjasnjavanjeDialogComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ IzjasnjavanjeDialogComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(IzjasnjavanjeDialogComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
