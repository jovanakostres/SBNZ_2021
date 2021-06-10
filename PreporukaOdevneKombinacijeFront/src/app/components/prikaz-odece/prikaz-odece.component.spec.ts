import { ComponentFixture, TestBed } from '@angular/core/testing';

import { PrikazOdeceComponent } from './prikaz-odece.component';

describe('PrikazOdeceComponent', () => {
  let component: PrikazOdeceComponent;
  let fixture: ComponentFixture<PrikazOdeceComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ PrikazOdeceComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(PrikazOdeceComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
