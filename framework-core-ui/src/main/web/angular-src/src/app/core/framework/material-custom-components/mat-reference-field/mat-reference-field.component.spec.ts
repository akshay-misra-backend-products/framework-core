import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { MatReferenceFieldComponent } from './mat-reference-field.component';

describe('MatReferenceFieldComponent', () => {
  let component: MatReferenceFieldComponent;
  let fixture: ComponentFixture<MatReferenceFieldComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ MatReferenceFieldComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(MatReferenceFieldComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
