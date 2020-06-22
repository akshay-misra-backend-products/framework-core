import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { MultiTypeDynamicTableComponent } from './multi-type-dynamic-table.component';

describe('MultiTypeDynamicTableComponent', () => {
  let component: MultiTypeDynamicTableComponent;
  let fixture: ComponentFixture<MultiTypeDynamicTableComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ MultiTypeDynamicTableComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(MultiTypeDynamicTableComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
