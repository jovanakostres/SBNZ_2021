import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ResultsPersonalizedComponent } from './results-personalized.component';

describe('ResultsPersonalizedComponent', () => {
  let component: ResultsPersonalizedComponent;
  let fixture: ComponentFixture<ResultsPersonalizedComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ResultsPersonalizedComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(ResultsPersonalizedComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
