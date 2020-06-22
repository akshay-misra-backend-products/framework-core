import { TestBed } from '@angular/core/testing';

import { NotificationPanelService } from './notification-panel.service';

describe('NotificationPanelService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: NotificationPanelService = TestBed.get(NotificationPanelService);
    expect(service).toBeTruthy();
  });
});
