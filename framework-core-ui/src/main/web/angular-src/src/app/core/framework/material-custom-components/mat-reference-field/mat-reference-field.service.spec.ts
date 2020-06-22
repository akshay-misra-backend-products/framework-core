import { TestBed } from '@angular/core/testing';

import { MatReferenceFieldService } from './mat-reference-field.service';

describe('MatReferenceFieldService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: MatReferenceFieldService = TestBed.get(MatReferenceFieldService);
    expect(service).toBeTruthy();
  });
});
