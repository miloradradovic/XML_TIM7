import { TestBed } from '@angular/core/testing';

import { IzjasnjavanjaService } from './izjasnjavanja.service';

describe('IzjasnjavanjaService', () => {
  let service: IzjasnjavanjaService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(IzjasnjavanjaService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
