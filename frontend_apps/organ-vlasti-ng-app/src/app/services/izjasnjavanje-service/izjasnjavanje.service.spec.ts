import { TestBed } from '@angular/core/testing';

import { IzjasnjavanjeService } from './izjasnjavanje.service';

describe('IzjasnjavanjeService', () => {
  let service: IzjasnjavanjeService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(IzjasnjavanjeService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
