import {TestBed} from '@angular/core/testing';

import {BookResolver} from './book.resolver';

describe('BookResolver', () => {
  let service: BookResolver;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(BookResolver);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
