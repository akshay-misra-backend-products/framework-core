import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { DynamicDetailsComponent } from './dynamic-details.component';

describe('DynamicDetailsComponent', () => {
  let component: DynamicDetailsComponent;
  let fixture: ComponentFixture<DynamicDetailsComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ DynamicDetailsComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(DynamicDetailsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
