import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { FieldsComposerComponent } from './fields-composer.component';

describe('FieldsComposerComponent', () => {
  let component: FieldsComposerComponent;
  let fixture: ComponentFixture<FieldsComposerComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ FieldsComposerComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(FieldsComposerComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
