import { TestBed } from '@angular/core/testing';

import { ZalbaOdlukaService } from './zalba-odluka.service';

describe('ZalbaOdlukaService', () => {
  let service: ZalbaOdlukaService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(ZalbaOdlukaService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
