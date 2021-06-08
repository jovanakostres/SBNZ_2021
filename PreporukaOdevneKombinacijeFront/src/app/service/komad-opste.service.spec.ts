import { TestBed } from '@angular/core/testing';

import { KomadOpsteService } from './komad-opste.service';

describe('KomadOpsteService', () => {
  let service: KomadOpsteService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(KomadOpsteService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
