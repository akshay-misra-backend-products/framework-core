import { TestBed } from '@angular/core/testing';

import { MultiTypeDynamicTableService } from './multi-type-dynamic-table.service';

describe('MultiTypeDynamicTableService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: MultiTypeDynamicTableService = TestBed.get(MultiTypeDynamicTableService);
    expect(service).toBeTruthy();
  });
});
