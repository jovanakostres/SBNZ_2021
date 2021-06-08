import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AddKomadOdeceComponent } from './add-komad-odece.component';

describe('AddKomadOdeceComponent', () => {
  let component: AddKomadOdeceComponent;
  let fixture: ComponentFixture<AddKomadOdeceComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ AddKomadOdeceComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(AddKomadOdeceComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
