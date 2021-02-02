import { TestBed } from '@angular/core/testing';

import { AddPoverenikService } from './add-poverenik.service';

describe('AddPoverenikService', () => {
  let service: AddPoverenikService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(AddPoverenikService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
