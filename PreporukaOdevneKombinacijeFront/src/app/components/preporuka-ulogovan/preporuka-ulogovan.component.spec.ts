import { ComponentFixture, TestBed } from '@angular/core/testing';

import { PreporukaUlogovanComponent } from './preporuka-ulogovan.component';

describe('PreporukaUlogovanComponent', () => {
  let component: PreporukaUlogovanComponent;
  let fixture: ComponentFixture<PreporukaUlogovanComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ PreporukaUlogovanComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(PreporukaUlogovanComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
