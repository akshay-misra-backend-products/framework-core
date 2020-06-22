import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { CompositeTableComponent } from './composite-table.component';

describe('CompositeTableComponent', () => {
  let component: CompositeTableComponent;
  let fixture: ComponentFixture<CompositeTableComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ CompositeTableComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(CompositeTableComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
