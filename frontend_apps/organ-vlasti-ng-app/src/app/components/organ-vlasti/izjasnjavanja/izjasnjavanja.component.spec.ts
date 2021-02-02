import { ComponentFixture, TestBed } from '@angular/core/testing';

import { IzjasnjavanjaComponent } from './izjasnjavanja.component';

describe('IzjasnjavanjaComponent', () => {
  let component: IzjasnjavanjaComponent;
  let fixture: ComponentFixture<IzjasnjavanjaComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ IzjasnjavanjaComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(IzjasnjavanjaComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
