import { TestBed } from '@angular/core/testing';

import { CompositePageService } from './composite-page.service';

describe('CompositePageService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: CompositePageService = TestBed.get(CompositePageService);
    expect(service).toBeTruthy();
  });
});
