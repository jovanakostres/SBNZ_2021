import { ComponentFixture, TestBed } from '@angular/core/testing';

import { Aktivnost7DanaComponent } from './aktivnost7-dana.component';

describe('Aktivnost7DanaComponent', () => {
  let component: Aktivnost7DanaComponent;
  let fixture: ComponentFixture<Aktivnost7DanaComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ Aktivnost7DanaComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(Aktivnost7DanaComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
