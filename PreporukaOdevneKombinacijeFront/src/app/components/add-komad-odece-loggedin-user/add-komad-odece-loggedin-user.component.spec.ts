import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AddKomadOdeceLoggedinUserComponent } from './add-komad-odece-loggedin-user.component';

describe('AddKomadOdeceLoggedinUserComponent', () => {
  let component: AddKomadOdeceLoggedinUserComponent;
  let fixture: ComponentFixture<AddKomadOdeceLoggedinUserComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ AddKomadOdeceLoggedinUserComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(AddKomadOdeceLoggedinUserComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
