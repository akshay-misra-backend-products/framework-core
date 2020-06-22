import { TestBed } from '@angular/core/testing';

import { CompositeDetailsService } from './composite-details.service';

describe('CompositeDetailsService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: CompositeDetailsService = TestBed.get(CompositeDetailsService);
    expect(service).toBeTruthy();
  });
});
