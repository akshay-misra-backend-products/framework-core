import { TestBed } from '@angular/core/testing';

import { MatTableService } from './mat-table.service';

describe('MatTableService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: MatTableService = TestBed.get(MatTableService);
    expect(service).toBeTruthy();
  });
});
