import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ResultsOpsteComponent } from './results-opste.component';

describe('ResultsOpsteComponent', () => {
  let component: ResultsOpsteComponent;
  let fixture: ComponentFixture<ResultsOpsteComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ResultsOpsteComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(ResultsOpsteComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
