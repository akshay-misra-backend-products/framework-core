import { TestBed } from '@angular/core/testing';

import { CompositeTableService } from './composite-table.service';

describe('CompositeTableService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: CompositeTableService = TestBed.get(CompositeTableService);
    expect(service).toBeTruthy();
  });
});
