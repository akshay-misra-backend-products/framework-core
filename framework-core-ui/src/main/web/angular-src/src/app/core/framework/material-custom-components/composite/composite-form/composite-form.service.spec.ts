import { TestBed } from '@angular/core/testing';

import { CompositeFormService } from './composite-form.service';

describe('CompositeFormService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: CompositeFormService = TestBed.get(CompositeFormService);
    expect(service).toBeTruthy();
  });
});
