import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { CompositeDetailsComponent } from './composite-details.component';

describe('CompositeDetailsComponent', () => {
  let component: CompositeDetailsComponent;
  let fixture: ComponentFixture<CompositeDetailsComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ CompositeDetailsComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(CompositeDetailsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
