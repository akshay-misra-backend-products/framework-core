import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { NavCollapsableItemComponent } from './nav-collapsible-item.component';

describe('NavCollapsableItemComponent', () => {
  let component: NavCollapsableItemComponent;
  let fixture: ComponentFixture<NavCollapsableItemComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ NavCollapsableItemComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(NavCollapsableItemComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
