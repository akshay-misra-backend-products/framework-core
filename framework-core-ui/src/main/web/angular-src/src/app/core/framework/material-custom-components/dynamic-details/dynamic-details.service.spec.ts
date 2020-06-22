import { TestBed } from '@angular/core/testing';

import { DynamicDetailsService } from './dynamic-details.service';

describe('DynamicDetailsService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: DynamicDetailsService = TestBed.get(DynamicDetailsService);
    expect(service).toBeTruthy();
  });
});
