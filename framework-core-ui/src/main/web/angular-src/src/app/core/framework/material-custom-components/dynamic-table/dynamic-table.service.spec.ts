import { TestBed } from '@angular/core/testing';

import { DynamicTableService } from './dynamic-table.service';

describe('DynamicTableService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: DynamicTableService = TestBed.get(DynamicTableService);
    expect(service).toBeTruthy();
  });
});
