import { TestBed } from '@angular/core/testing';

import { ApplicationRouteService } from './application-route.service';

describe('ApplicationRouteService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: ApplicationRouteService = TestBed.get(ApplicationRouteService);
    expect(service).toBeTruthy();
  });
});
