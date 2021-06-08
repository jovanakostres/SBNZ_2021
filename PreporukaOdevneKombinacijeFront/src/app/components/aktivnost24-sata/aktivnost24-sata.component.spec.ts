import { ComponentFixture, TestBed } from '@angular/core/testing';

import { Aktivnost24SataComponent } from './aktivnost24-sata.component';

describe('Aktivnost24SataComponent', () => {
  let component: Aktivnost24SataComponent;
  let fixture: ComponentFixture<Aktivnost24SataComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ Aktivnost24SataComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(Aktivnost24SataComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
