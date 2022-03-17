import { TestBed } from '@angular/core/testing';

import { CursoserviceService } from './cursoservice.service';

describe('CursoserviceService', () => {
  let service: CursoserviceService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(CursoserviceService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
