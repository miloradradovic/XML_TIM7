import { TestBed } from '@angular/core/testing';

import { AddSluzbenikService } from './add-sluzbenik.service';

describe('AddSluzbenikService', () => {
  let service: AddSluzbenikService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(AddSluzbenikService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
