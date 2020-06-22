import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { CompositePageComponent } from './composite-page.component';

describe('CompositePageComponent', () => {
  let component: CompositePageComponent;
  let fixture: ComponentFixture<CompositePageComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ CompositePageComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(CompositePageComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
