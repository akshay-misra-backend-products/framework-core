import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { CompositeFormComponent } from './composite-form.component';

describe('CompositeFormComponent', () => {
  let component: CompositeFormComponent;
  let fixture: ComponentFixture<CompositeFormComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ CompositeFormComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(CompositeFormComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
