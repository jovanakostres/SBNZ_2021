import { ComponentFixture, TestBed } from '@angular/core/testing';

import { HomeLoggedinUserComponent } from './home-loggedin-user.component';

describe('HomeLoggedinUserComponent', () => {
  let component: HomeLoggedinUserComponent;
  let fixture: ComponentFixture<HomeLoggedinUserComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ HomeLoggedinUserComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(HomeLoggedinUserComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
