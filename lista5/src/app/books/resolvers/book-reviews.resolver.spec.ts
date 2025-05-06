import {TestBed} from '@angular/core/testing';
import {ResolveFn} from '@angular/router';

import {bookReviewsResolver} from './book-reviews.resolver';

describe('bookReviewsResolver', () => {
  const executeResolver: ResolveFn<boolean> = (...resolverParameters) =>
      TestBed.runInInjectionContext(() => bookReviewsResolver(...resolverParameters));

  beforeEach(() => {
    TestBed.configureTestingModule({});
  });

  it('should be created', () => {
    expect(executeResolver).toBeTruthy();
  });
});
