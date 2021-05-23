import { TestBed } from '@angular/core/testing';

import { KomadOdeceLogedinService } from './komad-odece-logedin.service';

describe('KomadOdeceLogedinService', () => {
  let service: KomadOdeceLogedinService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(KomadOdeceLogedinService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
