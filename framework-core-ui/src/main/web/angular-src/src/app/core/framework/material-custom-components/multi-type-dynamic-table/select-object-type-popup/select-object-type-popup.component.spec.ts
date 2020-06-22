import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { SelectObjectTypePopupComponent } from './select-object-type-popup.component';

describe('SelectObjectTypePopupComponent', () => {
  let component: SelectObjectTypePopupComponent;
  let fixture: ComponentFixture<SelectObjectTypePopupComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ SelectObjectTypePopupComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(SelectObjectTypePopupComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
